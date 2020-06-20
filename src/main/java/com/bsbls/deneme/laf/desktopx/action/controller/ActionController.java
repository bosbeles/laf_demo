package com.bsbls.deneme.laf.desktopx.action.controller;

import com.bsbls.deneme.laf.desktopx.action.model.ActionWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ActionController {


    private final Map<String, ActionWrapper> actionWrapperMap;
    private Map<String, Action> actionMap = new HashMap<>();

    public ActionController(Map<String, Action> actionMap, Map<String, ActionWrapper> actionWrapperMap) {
        this.actionMap = actionMap;
        this.actionWrapperMap = actionWrapperMap;
    }

    public void doAction(String action) {
        ActionWrapper actionWrapper = actionWrapperMap.get(action);
        if(actionWrapper != null) {
            doAction(actionWrapper);
        }

    }

    public void doAction(ActionWrapper wrapper) {
        Action action = actionMap.get(wrapper.getName());
        if (action != null && action.isEnabled()) {
            wrapper.setLastUsedTime(Instant.now());
            wrapper.setCounter(wrapper.getCounter() + 1);
            action.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }

    }

}
