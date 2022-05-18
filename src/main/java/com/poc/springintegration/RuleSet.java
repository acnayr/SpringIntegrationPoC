package com.poc.springintegration;

import java.util.List;

public class RuleSet {

    private List<Rule> rules;

    public RuleSet(RuleSetBuilder builder) {
        this.rules = builder.getRules();
    }

    public List<Rule> getRules() {
        return this.rules;
    }
}
