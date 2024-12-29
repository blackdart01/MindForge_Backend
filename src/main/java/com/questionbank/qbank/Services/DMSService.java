package com.questionbank.qbank.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.questionbank.qbank.Models.DocumentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.util.List;

@Service
public interface DMSService {

    public DocumentModel getDocumentById(String id);
    public List<DocumentModel> getAllDocuments();
    public DocumentModel saveDocuments(JsonNode jsonNode);
    public DocumentModel updateTestStatus(String id, String status);
    public DocumentModel updateTestProgress(String id, String progress);
}
