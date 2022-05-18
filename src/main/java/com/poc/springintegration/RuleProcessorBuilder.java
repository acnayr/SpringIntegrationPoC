package com.poc.springintegration;

public class RuleProcessorBuilder<I> {

    private RuleSet rules;

    private I input;

    private boolean skipPriorities;

    private RuleProcessorBuilder(I input) {
        this.input = input;
    }

    public static <I> RuleProcessorBuilder<I> builder(I input) {
        return new RuleProcessorBuilder<>(input);
    }

    public RuleProcessorBuilder rules(RuleSet rules) {
        this.rules = rules;
        return this;
    }

    public RuleProcessorBuilder skipPriorities(boolean skipPriorities) {
        this.skipPriorities = skipPriorities;
        return this;
    }

    public RuleSet getRules() {
        return rules;
    }

    public I getInput() {
        return input;
    }

    public boolean isSkipPriorities() {
        return skipPriorities;
    }

    @Deprecated
    public RuleProcessor<I> build() {
        return new RuleProcessor<>(null, null);
    }
}
