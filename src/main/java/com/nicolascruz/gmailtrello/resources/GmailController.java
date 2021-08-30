package com.nicolascruz.gmailtrello.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

@RestController
@RequestMapping("/login")
public class GmailController {
	
	private static final String APPLICATION_NAME = "GmailTrello";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static HttpTransport httpTransport;
	private static Gmail client;
	private static String userId = "me";
	
	GoogleAuthorizationCodeFlow flow;
	Credential credential;
	
	@Value("${gmail.client.clientId}")
	private String clientId;

	@Value("${gmail.client.clientSecret}")
	private String clientSecret;

	@Value("${gmail.client.redirectUri}")
	private String redirectUri;
	
	@GetMapping("/gmail")
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {
		return new RedirectView(authorize());
	}
	
	@GetMapping("/gmailCallBack")
	public ResponseEntity<List<String>> oauth2Callback(
			@RequestParam(value = "code") String code,
			@RequestParam(value = "scope") String scope) {
		
		List<String> resultado = new ArrayList<>();
		
		try {
			TokenResponse response = flow.newTokenRequest(code).setRedirectUri(redirectUri).execute();
			credential = flow.createAndStoreCredential(response, "userID");
		
			client = new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
					.setApplicationName(APPLICATION_NAME).build();

			String query = "vuvuzela";
			ListMessagesResponse msgResponse = client.users().messages().list(userId).setQ(query).execute();
			
			for(Message m : msgResponse.getMessages()) {
				Message message = client.users().messages().get(userId, m.getId()).execute();
				resultado.add("Title: " + message.getPayload().getHeaders().get(3).getValue() + " Body: " + message.getSnippet());
			}
			
		} catch (Exception e) {

			System.out.println("exception cached ");
			e.printStackTrace();
		} 
		
		return ResponseEntity.ok(resultado);
		

	}
	
	private String authorize() throws Exception {
		AuthorizationCodeRequestUrl authorizationUrl;
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			GoogleClientSecrets clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					Collections.singleton(GmailScopes.GMAIL_READONLY)).build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);
		return authorizationUrl.build();
	}

}
