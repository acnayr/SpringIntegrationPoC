package com.poc.springintegration;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RuleProcessor<I> {

    private I input;

    private Map<String, RuleProcess> flows;

    /**
     *
     * @param configuration
     * @param input
     */
    public RuleProcessor(RulesConfiguration configuration, I input) {
        this.flows = configuration.getFlows();
        this.input = input;
    }


    public void fire(String flow) {
        RuleProcess ruleProcess = flows.get(flow);
        List<Rule> ruleFlow = ruleProcess.getRuleSet().getRules();
        boolean skipPriorities = ruleProcess.isSkipPriorities();

        if (!skipPriorities) {
            Collections.sort(ruleFlow, new Comparator<Rule>() {
                @Override
                public int compare(Rule o1, Rule o2) {
                    Integer o1Priority = getPriority(o1);
                    Integer o2Priority = getPriority(o2);
                    return o1Priority.compareTo(o2Priority);
                }
            });
        }

        for (Rule rule : ruleFlow) {

            String rulename = getRuleName(rule);
            System.out.println("Ejecutando regla: " + rulename);

            if (!isDisabled(rule)) {

                if (rule.condicion(input)) {
                    rule.accionPrincipal(input);

                    if (isFinalize(rule)) {
                        break;
                    }

                } else {
                    rule.acccionAlterna(input);
                }
            } else {
                System.out.println("Saltando regla: " + rulename);
            }
        }
    }

    private String getRuleName(Rule rule) {
        String ruleName = "no name";
        if (rule.getClass().isAnnotationPresent(RuleDefinition.class)) {
            Annotation a = rule.getClass().getAnnotation(RuleDefinition.class);
            RuleDefinition annotation = (RuleDefinition) a;
            ruleName = annotation.name();
        }
        return ruleName;
    }

    private int getPriority(Rule rule) {
        int priority = 99;
        if (rule.getClass().isAnnotationPresent(RuleDefinition.class)) {
            Annotation a = rule.getClass().getAnnotation(RuleDefinition.class);
            RuleDefinition annotation = (RuleDefinition) a;
            priority = annotation.priority();
        }
        return priority;
    }

    private boolean isDisabled(Rule rule) {
        boolean disabled = false;
        if (rule.getClass().isAnnotationPresent(RuleDefinition.class)) {
            Annotation a = rule.getClass().getAnnotation(RuleDefinition.class);
            RuleDefinition annotation = (RuleDefinition) a;
            disabled = annotation.disabled();
        }
        return disabled;
    }


    private boolean isFinalize(Rule rule) {
        boolean finalize = false;
        if (rule.getClass().isAnnotationPresent(RuleDefinition.class)) {
            Annotation a = rule.getClass().getAnnotation(RuleDefinition.class);
            RuleDefinition annotation = (RuleDefinition) a;
            finalize = annotation.finalized();
        }
        return finalize;
    }

}
