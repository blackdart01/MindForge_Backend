package com.questionbank.qbank.Repository;

import com.questionbank.qbank.Models.DocumentModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DMSRepository extends MongoRepository<DocumentModel, String> {

}
