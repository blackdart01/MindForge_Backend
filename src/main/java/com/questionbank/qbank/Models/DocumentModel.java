package com.questionbank.qbank.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "dms-col")
public class DocumentModel {
    @Id
    @JsonProperty("id")
    private String id;
    @NotNull
    @NotBlank
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type; // e.g., "jpg", "pdf", "docx"
    @NotNull
    @NotBlank
    @JsonProperty("uri")
    private String uri;
    @NotNull
    @NotBlank
    @JsonProperty("tags")
    private List<String> tags;
    @NotNull
    @NotBlank
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("description")
    private String description;
    @JsonProperty("uploadDate")
    private Date uploadDate;
}
