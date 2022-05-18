package com.poc.springintegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessGroupComponent {

    private RulesConfiguration configuration;

    @Autowired
    public AccessGroupComponent(RulesConfiguration configuration) {
        this.configuration = configuration;
    }

    public void executeRules(Person payload) {
        RuleProcessor<Person> processor = new RuleProcessor<>(configuration, payload);
        processor.fire(payload.getDominio());
    }
}
