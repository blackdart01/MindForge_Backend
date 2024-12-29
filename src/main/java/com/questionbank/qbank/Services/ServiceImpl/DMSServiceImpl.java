package com.questionbank.qbank.Services.ServiceImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionbank.qbank.Models.DocumentModel;
import com.questionbank.qbank.Repository.DMSRepository;
import com.questionbank.qbank.Services.DMSService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DMSServiceImpl implements DMSService {

    @Autowired
    DMSRepository dmsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public DocumentModel getDocumentById(String id) {
        return dmsRepository.findById(id).orElse(null);
    }
    public List<DocumentModel> getAllDocuments() {
        Query query = new Query();
        query.addCriteria(Criteria.where("collection").is("dms-col"));
        List<DocumentModel> x= mongoTemplate.findAll(DocumentModel.class, "dms-col");
        return x;
//        return dmsRepository.findAll();
    }

    public DocumentModel saveDocuments(JsonNode jsonNode){
        DocumentModel documentModel = new DocumentModel();
        if(jsonNode!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                documentModel = objectMapper.treeToValue(jsonNode, DocumentModel.class);
//                String n = documentModel.getUri();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
//        if(ObjectUtils.isEmpty(documentModel.getUri()) || ObjectUtils.isEmpty(documentModel.getName()) || ObjectUtils.isEmpty(documentModel.getTags()))
//            documentModel = null;
        if(documentModel!=null)
            documentModel = mongoTemplate.save(documentModel, "dms-col");
        return documentModel;
    }
}
