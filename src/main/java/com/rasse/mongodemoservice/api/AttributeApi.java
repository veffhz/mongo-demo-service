package com.rasse.mongodemoservice.api;

import com.rasse.mongodemoservice.domen.Attribute;
import com.rasse.mongodemoservice.domen.CustomDocument;
import com.rasse.mongodemoservice.service.AttributeService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AttributeApi {

    private final AttributeService attributeService;

    @Autowired
    public AttributeApi(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/attribute")
    public List<Attribute> getAll() {
        log.info("get all attributes");
        return attributeService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/attribute/{id}")
    public Attribute getById(@PathVariable String id) {
        log.info("get attribute by id {}", id);
        return attributeService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/attribute/search")
    public List<Attribute> getByAttributeParam(
            @RequestParam(value="key") String key, @RequestParam(value="value") String value
            ) {
        log.info("get attribute by key {} value {}", key, value);
        return attributeService.findBy(key, value);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/attribute")
    public Attribute update(@RequestBody Attribute attribute) {
        log.info("update attribute {} by id {}", attribute, attribute.getId());
        return attributeService.update(attribute);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/attribute")
    public Attribute create(@RequestBody Attribute attribute) {
        log.info("create attribute {}", attribute);
        return attributeService.insert(attribute);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/attribute/{id}")
    public void delete(@PathVariable String id) {
        log.info("delete attribute by id {}", id);
        attributeService.deleteById(id);
    }

}
