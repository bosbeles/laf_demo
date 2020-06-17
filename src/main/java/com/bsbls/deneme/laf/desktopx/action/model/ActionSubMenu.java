package com.bsbls.deneme.laf.desktopx.action.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ActionSubMenu {


    private String name;

    private List<String> actionList = new ArrayList<>();


    public ActionSubMenu(String name) {
        this.name = name;
    }

    public ActionSubMenu add(String... actions) {
        actionList.addAll(Arrays.asList(actions));
        return this;
    }

}