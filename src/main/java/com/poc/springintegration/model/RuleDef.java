package com.poc.springintegration.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.KebabCaseStrategy.class)
public class RuleDef implements Serializable {

    private String name;
    private String classRef;
    private int priority;
    private boolean disabled;
    private boolean finalized;
}
