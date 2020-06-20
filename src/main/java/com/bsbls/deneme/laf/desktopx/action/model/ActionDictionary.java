package com.bsbls.deneme.laf.desktopx.action.model;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ActionDictionary {

    private Map<String, ActionWrapper> allWrappers;


    private SearchStrategy searchStrategy;


    public ActionDictionary(Map<String, ActionWrapper> allWrappers) {
        this.allWrappers = allWrappers;
        this.searchStrategy = new BruteForceSearchStrageyImpl();
    }

    public List<ActionWrapper> frequent() {

        List<ActionWrapper> frequentlyUsed = allWrappers.values().stream()
                .sorted(Comparator.comparing((ActionWrapper a) -> a.getCounter()).reversed())
                .limit(5).collect(Collectors.toList());

        return frequentlyUsed;
    }

    public List<ActionWrapper> recent() {

        List<ActionWrapper> recentlyUsed = allWrappers.values().stream()
                .sorted(Comparator.comparing((ActionWrapper a) -> a.getLastUsedTime()).reversed())
                .limit(5).collect(Collectors.toList());

        return recentlyUsed;
    }


    public List<ActionWrapper> search(String text) {
        return searchStrategy.search(allWrappers.values(), text);
    }


}
