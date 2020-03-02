package com.rasse.mongodemoservice.api;

import com.rasse.mongodemoservice.domen.Owner;
import com.rasse.mongodemoservice.service.OwnerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class OnwerApi {

    private final OwnerService ownerService;

    @Autowired
    public OnwerApi(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/owner")
    public List<Owner> getAll() {
        log.info("get all owners");
        return ownerService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/owner/{id}")
    public Owner getById(@PathVariable String id) {
        log.info("get owner by id {}", id);
        return ownerService.getById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/api/owner")
    public Owner update(@RequestBody Owner owner) {
        log.info("update owner {} by id {}", owner, owner.getId());
        return ownerService.update(owner);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/owner")
    public Owner create(@RequestBody Owner owner) {
        log.info("create owner {}", owner);
        return ownerService.insert(owner);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/api/owner/{id}")
    public void delete(@PathVariable String id) {
        log.info("delete owner by id {}", id);
        ownerService.deleteById(id);
    }

}
