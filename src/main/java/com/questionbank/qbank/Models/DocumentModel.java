package com.questionbank.qbank.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Document(collection = "dms-col")
@Component
public class DocumentModel {
    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type; // e.g., "jpg", "pdf", "docx"
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("description")
    private String description;
    @JsonProperty("uploadDate")
    private Date uploadDate;
    @JsonProperty("testExpiry")
    private Date testExpiry;
    @JsonProperty("testStatus")
    private String testStatus;
    @JsonProperty("testProgressDuration")
    private Integer testProgressDuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Date getTestExpiry() {
        return testExpiry;
    }

    public void setTestExpiry(Date testExpiry) {
        this.testExpiry = testExpiry;
    }

    public String getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(String testStatus) {
        this.testStatus = testStatus;
    }

    public DocumentModel(String id, String name, String type, String uri, List<String> tags, Integer duration, String description, Date uploadDate, Date testExpiry, String testStatus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.uri = uri;
        this.tags = tags;
        this.duration = duration;
        this.description = description;
        this.uploadDate = uploadDate;
        this.testExpiry = testExpiry;
        this.testStatus = testStatus;
    }

    @Override
    public String toString() {
        return "DocumentModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", tags=" + tags +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", uploadDate=" + uploadDate +
                ", testExpiry=" + testExpiry +
                ", testStatus='" + testStatus + '\'' +
                '}';
    }

    public DocumentModel() {
    }

    public Integer getTestProgressDuration() {
        return testProgressDuration;
    }

    public void setTestProgressDuration(Integer testProgressDuration) {
        this.testProgressDuration = testProgressDuration;
    }
}
