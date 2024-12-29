package com.questionbank.qbank.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questionbank.qbank.Models.DocumentModel;
import com.questionbank.qbank.Services.DMSService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/short")
@CrossOrigin(origins = {"http://localhost:5173", "https://mindforge-lms.netlify.app/"})
public class URLShortnerController {

    private static final Map<String, String> urlMap = new HashMap<>();

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam("url") String originalUrl) {
        if (originalUrl == null || originalUrl.isEmpty()) {
            return ResponseEntity.badRequest().body("Missing URL");
        }

        String shortCode = generateShortCode();
        urlMap.put(shortCode, originalUrl);

        String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{shortCode}")
                .buildAndExpand(shortCode)
                .toUriString();

        return ResponseEntity.created(java.net.URI.create(shortUrl)).body(shortUrl);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = urlMap.get(shortCode);

        if (originalUrl == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
    }

    // Helper method to generate a short code (you can improve this for better uniqueness)
    private String generateShortCode() {
        // Example: Generate a random 6-character alphanumeric code
        return java.util.UUID.randomUUID().toString().substring(0, 6);
    }

    //    @RequestMapping("/report/{url}")
//    void getFile(HttpServletResponse response, String url) throws IOException {
//
//        String fileName = "report.pdf";
//        String path = "/path/to/" + fileName;
//
//        File file = new File(path);
//        FileInputStream inputStream = new FileInputStream(file);
//
//        response.setContentType("application/pdf");
//        response.setContentLength((int) file.length());
//        response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "\"");
//
//        FileCopyUtils.copy(inputStream, response.getOutputStream());
//
//    }
    @GetMapping("/report")
    public ResponseEntity<byte[]> getFile(@RequestParam("url") String url) throws IOException {
        try {
            URL website = new URL((url));
            URLConnection connection = website.openConnection();
            byte[] pdfData = connection.getInputStream().readAllBytes();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Handle exceptions (e.g., invalid URL, network issues)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
