package com.bsbls.deneme.laf.desktopx.action.controller;

import com.bsbls.deneme.laf.desktopx.action.model.ActionWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class ActionController {


    private Map<String, Action> actionMap = new HashMap<>();

    public ActionController(Map<String, Action> actionMap) {
        this.actionMap = actionMap;
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
