package com.rasse.mongodemoservice.service;

import org.apache.chemistry.opencmis.client.api.*;

import java.util.List;
import java.util.Map;

public interface CmisService {
    Folder getRootFolder();
    Folder getFolder(String path);
    Document getDocument(String name);
    Document createDocument(Folder folder, String name, String contentType, byte[] content);
    ObjectId updateDocument(Folder folder, String name, String contentType, byte[] content, String id);
    void remove(String name);
    List<Document> getDocuments(Folder folder);
    List<Map<String, String>> getCmisObjects(Folder folder);
    List<Map<String, String>> getVersionsCmisObjects(String id);
}
