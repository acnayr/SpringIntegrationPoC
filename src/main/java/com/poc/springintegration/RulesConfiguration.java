package com.poc.springintegration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.springintegration.model.RuleDef;
import com.poc.springintegration.model.RuleFlows;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesConfiguration {

    private Map<String, RuleProcess> flows;

    public RulesConfiguration() {
        this.flows = new HashMap<>();
    }

    public Map<String, RuleProcess> getFlows() { return this.flows; }

    /**
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public RulesConfiguration loadConfiguration() throws Exception {
        InputStream rulesIs = RulesConfiguration.class.getClassLoader().getResourceAsStream("rules.json");
        ObjectMapper mapper = new ObjectMapper();
        List<RuleFlows> flows = mapper.readValue(rulesIs, new TypeReference<List<RuleFlows>>() {});

        searchAndCreateRules(flows);

        return this;
    }

    private void searchAndCreateRules(List<RuleFlows> flows) throws Exception {

        for (RuleFlows ruleFlows : flows) {
            RuleSetBuilder ruleSetBuilder = RuleSetBuilder.builder();
            boolean skipPriorities = ruleFlows.getFlowConf().isSkipPriorities();
            List<RuleDef> ruleDefs = ruleFlows.getFlowConf().getRules();

            for (RuleDef def : ruleDefs) {

                final String name = def.getName();
                final String clazzStr = def.getClassRef();
                final Integer priority = def.getPriority();
                final boolean disabled = def.isDisabled();
                final boolean finalized = def.isFinalized();

                Class clazz = Class.forName(clazzStr);
                Constructor c = clazz.getConstructor();
                Object ruleObj = c.newInstance();

                final RuleDefinition annotationRule = ruleObj.getClass().getAnnotation(RuleDefinition.class);
                Annotation newAnnotation = getNewRuleDefinition(name, priority, disabled, finalized, annotationRule.annotationType());

                setUpRuleDefinition(clazz, newAnnotation);
                ruleSetBuilder.registerRule((Rule) ruleObj);

            }
            RuleSet rules = ruleSetBuilder.build();
            RuleProcess ruleProcess = new RuleProcess(rules, skipPriorities);
            this.flows.put(ruleFlows.getFlowName(), ruleProcess);
        }
    }

    /**
     *
     * @param clazz
     * @param newAnnotation
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchFieldException
     */
    private void setUpRuleDefinition(Class<? extends Rule> clazz, Annotation newAnnotation) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Method method = Class.class.getDeclaredMethod("annotationData", null);
        method.setAccessible(true);
        Object annotationData = method.invoke(clazz);

        Field field = annotationData.getClass().getDeclaredField("annotations");
        field.setAccessible(true);

        Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) field.get(annotationData);
        annotations.put(RuleDefinition.class, newAnnotation);
    }

    /**
     *
     * @param name
     * @param priority
     * @param disabled
     * @param finalized
     * @param annotationType
     * @return
     */
    private RuleDefinition getNewRuleDefinition(String name, Integer priority, boolean disabled, boolean finalized,
                                                Class<? extends Annotation> annotationType) {
        return new RuleDefinition() {

            @Override
            public int priority() {
                return priority;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public boolean disabled() {
                return disabled;
            }

            @Override
            public boolean finalized() {
                return finalized;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return annotationType;
            }
        };
    }
}
