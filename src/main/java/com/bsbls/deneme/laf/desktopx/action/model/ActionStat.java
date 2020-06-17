package com.bsbls.deneme.laf.desktopx.action.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ActionStat {

    private String name;
    private Instant lastUsedTime;
    private int counter;
}
