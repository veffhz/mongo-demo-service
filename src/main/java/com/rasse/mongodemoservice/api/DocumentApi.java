package com.rasse.mongodemoservice.api;

import com.rasse.mongodemoservice.domen.CustomDocument;
import com.rasse.mongodemoservice.service.DocumentService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DocumentApi {

    private final DocumentService documentService;

    @Autowired
    public DocumentApi(DocumentService documentService) {
        this.documentService = documentService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/document")
    public List<CustomDocument> getAll() {
        log.info("get all documents");
        return documentService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/document/{id}")
    public CustomDocument getById(@PathVariable String id) {
        log.info("get document by id {}", id);
        return documentService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/document/attribute/{attribute}")
    public List<CustomDocument> getByAttribute(@PathVariable String attribute) {
        log.info("get documents by attribute {}", attribute);
        return documentService.getByAttribute(attribute);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/document/attribute")
    public List<CustomDocument> getByAttributeContains(
            @RequestParam(value="attribute") String attribute
    ) {
        log.info("get documents by attribute contains {}", attribute);
        return documentService.getByAttributeContains(attribute);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/document/attribute/exist")
    public List<CustomDocument> getByAttributeExist() {
        log.info("get documents by attribute exist");
        return documentService.getByAttributeExist();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/document")
    public CustomDocument update(@RequestBody CustomDocument document) {
        log.info("update document {} by id {}", document, document.getId());
        return documentService.update(document);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/document")
    public CustomDocument create(@RequestBody CustomDocument document) {
        log.info("create document {}", document);
        return documentService.insert(document);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/document/{id}")
    public void delete(@PathVariable String id) {
        log.info("delete document by id {}", id);
        documentService.deleteById(id);
    }

}
