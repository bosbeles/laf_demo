package com.bsbls.deneme.laf.desktopx.action.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
public class ActionWrapper {

    private String name;
    private String icon;
    private String description;
    private Set<String> keywords;

    private Instant lastUsedTime;
    private int counter;


}
