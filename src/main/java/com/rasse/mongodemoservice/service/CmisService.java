package com.rasse.mongodemoservice.service;

import org.apache.chemistry.opencmis.client.api.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CmisService {
    Folder getRootFolder();
    long count(Folder folder);
    Folder getFolder();
    Document getDocument(String name);
    Document createDocument(Folder folder, String name, MultipartFile content);
    ObjectId updateDocument(Folder folder, String name, MultipartFile content, String id);
    void remove(String name);
    List<Document> getDocuments(Folder folder);
    List<Map<String, String>> getDocumentsByName(String name);
    List<Map<String, String>> getCmisObjects(Folder folder);
    List<Map<String, String>> getVersionsCmisObjects(String id);
}
