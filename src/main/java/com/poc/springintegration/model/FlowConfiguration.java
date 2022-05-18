package com.poc.springintegration.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class FlowConfiguration implements Serializable {
    private boolean skipPriorities;
    private List<RuleDef> rules;
}
