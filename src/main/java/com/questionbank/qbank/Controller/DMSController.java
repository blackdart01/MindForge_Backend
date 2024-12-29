package com.questionbank.qbank.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionbank.qbank.Models.DocumentModel;
import com.questionbank.qbank.Services.DMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = {"http://localhost:5173", "https://mindforge-lms.netlify.app/"})
public class DMSController {

    @Autowired
    DMSService dmsService;

    @GetMapping("/get/{id}")
    public ResponseEntity<JsonNode> getDocumentById(@PathVariable String id){
        DocumentModel documentModel = dmsService.getDocumentById(id);
        if(documentModel!=null)
            return new ResponseEntity<>(convertToObject(documentModel), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<DocumentModel>> getAllDocument(){
        List<DocumentModel> documentModel = dmsService.getAllDocuments();
        if(documentModel!=null)
            return new ResponseEntity<>((documentModel), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/save")
    public ResponseEntity<DocumentModel> saveDocument(@RequestBody JsonNode jsonNode){
        DocumentModel documentModelNew = dmsService.saveDocuments(jsonNode);
        if(documentModelNew!=null)
            return new ResponseEntity<>((documentModelNew), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public <T> JsonNode convertToObject(T response){
        JsonNode r = null;
        if(response!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                r = objectMapper.readTree(response.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return r;
    }

}
