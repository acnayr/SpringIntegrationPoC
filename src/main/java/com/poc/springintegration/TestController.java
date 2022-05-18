package com.poc.springintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private AccessGroupComponent component;

    @Autowired
    public TestController(AccessGroupComponent component) {
        this.component = component;
    }

    @PostMapping (path = "/validate", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity execute(@RequestBody Person person) {
        component.executeRules(person);
        return ResponseEntity.ok(null);
    }
}
