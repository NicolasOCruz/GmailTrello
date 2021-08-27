package com.nicolascruz.gmailtrello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nicolascruz.gmailtrello.domain.EmailVO;

@Repository
public interface EmailRepository extends MongoRepository<EmailVO, String> {

}
