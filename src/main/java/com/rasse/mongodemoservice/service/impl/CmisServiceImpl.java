package com.rasse.mongodemoservice.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import com.rasse.mongodemoservice.service.CmisService;

import org.apache.chemistry.opencmis.client.api.*;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.UnfileObject;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * CMIS Service to handle operations within the session.
 *
 */
@Service
public class CmisServiceImpl implements CmisService {

    private static final String FOLDER = "Documents";

    private final String alfrescoUrl;
    private final String alfrescoUser;
    private final String alfrescoPass;

    // CMIS living session
    private Session session;

    @Autowired
    public CmisServiceImpl(@Value("${alfresco.repository.url}")String alfrescoUrl,
                           @Value("${alfresco.repository.user}") String alfrescoUser,
                           @Value("${alfresco.repository.pass}") String alfrescoPass) {
        this.alfrescoUrl = alfrescoUrl;
        this.alfrescoUser = alfrescoUser;
        this.alfrescoPass = alfrescoPass;
    }

    @PostConstruct
    public void init() {
        String alfrescoBrowserUrl = alfrescoUrl + "/api/-default-/public/cmis/versions/1.1/browser";

        Map<String, String> parameter = new HashMap<>();

        parameter.put(SessionParameter.USER, alfrescoUser);
        parameter.put(SessionParameter.PASSWORD, alfrescoPass);

        parameter.put(SessionParameter.BROWSER_URL, alfrescoBrowserUrl);
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());

        SessionFactory factory = SessionFactoryImpl.newInstance();
        session = factory.getRepositories(parameter).get(0).createSession();

        if (!session.existsPath(String.format("/%s", FOLDER))) {
            Map<String, Object> properties = new HashMap<>();
            properties.put(PropertyIds.NAME, FOLDER);
            properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");

            session.getRootFolder().createFolder(properties);
        }
    }

    @Override
    public Folder getRootFolder() {
        return session.getRootFolder();
    }

    @Override
    public Folder getFolder() {
        CmisObject folder = session.getObjectByPath(String.format("/%s", FOLDER));
        if (folder instanceof Folder) {
            return (Folder) folder;
        } else {
            throw new IllegalArgumentException("Object is not a folder!");
        }
    }

    @Override
    public Document getDocument(String name) {
        CmisObject document = session.getObject(name);
        if (document instanceof Document) {
            return (Document) document;
        } else {
            throw new IllegalArgumentException("Object is not a document!");
        }
    }

    @Override
    public Document createDocument(Folder folder, String name, String contentType, byte[] content) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
        properties.put(PropertyIds.NAME, name);

        InputStream stream = new ByteArrayInputStream(content);
        ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length),
                contentType, stream);

        return folder.createDocument(properties, contentStream, VersioningState.MAJOR);
    }

    @Override
    public ObjectId updateDocument(Folder folder, String name, String contentType, byte[] content, String id) {
        Document document = getDocument(id);

        ObjectId pwcId = document.checkOut();
        Document pwc = (Document) session.getObject(pwcId);

        Map<String, Object> properties = new HashMap<>();
        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
        properties.put(PropertyIds.NAME, name);

        InputStream stream = new ByteArrayInputStream(content);
        ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length),
                contentType, stream);

        return pwc.checkIn(true, properties, contentStream, "Added new version");
    }

    @Override
    public void remove(String name) {
        CmisObject object = session.getObject(name);

        if (object instanceof Folder) {
            ((Folder) object).deleteTree(true, UnfileObject.DELETE, true);
        } else {
            object.delete(true);
        }
    }

    @Override
    public List<Document> getDocuments(Folder folder) {
        return StreamSupport.stream(folder.getChildren().spliterator(), false)
                .map(document -> (Document) document)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, String>> getCmisObjects(Folder folder) {
        List<Map<String, String>> result = new ArrayList<>();
        cmisObjects(folder).forEach(obj -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", obj.getId());
            map.put("name", obj.getName());
            map.put("createdBy", obj.getCreatedBy());
            map.put("description", obj.getDescription());
            result.add(map);
        });
        return result;
    }

    @Override
    public List<Map<String, String>> getVersionsCmisObjects(String id) {
        List<Map<String, String>> result = new ArrayList<>();
        cmisAllVersionObjects(id).forEach(obj -> {
            Map<String, String> map = new HashMap<>();
            map.put("id", obj.getId());
            map.put("name", obj.getName());
            map.put("createdBy", obj.getCreatedBy());
            map.put("description", obj.getDescription());
            result.add(map);
        });
        return result;
    }

    private List<CmisObject> cmisObjects(Folder folder) {
        return StreamSupport.stream(folder.getChildren().spliterator(), false)
                .collect(Collectors.toList());
    }

    private List<Document> cmisAllVersionObjects(String id) {
        return getDocument(id).getAllVersions();
    }

}
