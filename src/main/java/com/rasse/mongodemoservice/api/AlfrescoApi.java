package com.rasse.mongodemoservice.api;

import com.rasse.mongodemoservice.service.CmisService;

import lombok.extern.slf4j.Slf4j;

import org.apache.chemistry.opencmis.client.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class AlfrescoApi {

    private final CmisService cmisService;

    @Autowired
    public AlfrescoApi(CmisService cmisService) {
        this.cmisService = cmisService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/doc")
    public List<Map<String, String>> getAll() {
        log.info("get all docs");
        Folder docsFolder = cmisService.getFolder("Documents");
        return cmisService.getCmisObjects(docsFolder);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/doc/version/{id}")
    public List<Map<String, String>> getAllVersion(@PathVariable String id) {
        log.info("get all version");
        return cmisService.getVersionsCmisObjects(id);
    }

    @GetMapping("/api/doc/{id}")
    public ResponseEntity<Resource> getById(@PathVariable String id) {
        log.info("get owner by id {}", id);
        Document document = cmisService.getDocument(id);
        InputStreamResource resource = new InputStreamResource(document.getContentStream().getStream());
        System.out.println(resource);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getContentStreamFileName() + "\"")
                .contentLength(document.getContentStreamLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/doc")
    public String create(@RequestBody MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        log.info("create doc {}", name);
        Folder rootFolder = cmisService.getFolder("Documents");
        String contentType = file.getContentType();
        Document document = cmisService.createDocument(rootFolder, name, contentType, file.getBytes());
        return document.getId();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/doc")
    public ObjectId update(@RequestParam(value="id")String id, @RequestBody MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        log.info("update doc id {} {}", id, name);
        Folder rootFolder = cmisService.getFolder("Documents");
        String contentType = file.getContentType();
        return cmisService.updateDocument(rootFolder, name, contentType, file.getBytes(), id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/doc/{id}")
    public void delete(@PathVariable String id) {
        log.info("delete doc by id {}", id);
        cmisService.remove(id);
    }

}
