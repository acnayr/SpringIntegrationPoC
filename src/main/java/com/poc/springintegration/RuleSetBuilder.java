package com.poc.springintegration;

import java.util.ArrayList;
import java.util.List;

public class RuleSetBuilder {

    private List<Rule> rules;

    private RuleSetBuilder() {
        this.rules = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public static RuleSetBuilder builder() {
        return new RuleSetBuilder();
    }

    /**
     *
     * @param rule
     * @return
     */
    public RuleSetBuilder registerRule(Rule rule) {
        this.rules.add(rule);
        return this;
    }

    /**
     *
     * @return
     */
    public List<Rule> getRules() {
        return this.rules;
    }

    /**
     *
     * @return
     */
    public RuleSet build() {
        return new RuleSet(this);
    }
}
