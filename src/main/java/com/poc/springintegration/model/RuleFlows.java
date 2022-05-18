package com.poc.springintegration.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class RuleFlows implements Serializable {

    private String flowName;
    private FlowConfiguration flowConf;
}
