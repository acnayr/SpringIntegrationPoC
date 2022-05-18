package com.poc.springintegration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleProcess {

    private RuleSet ruleSet;
    private boolean skipPriorities;
}
