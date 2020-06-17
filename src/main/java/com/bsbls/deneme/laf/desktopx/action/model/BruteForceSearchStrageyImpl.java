package com.bsbls.deneme.laf.desktopx.action.model;

import java.util.*;
import java.util.stream.Collectors;

public class BruteForceSearchStrageyImpl implements SearchStrategy {
    private static final Locale TR = Locale.forLanguageTag("tr-TR");

    @Override
    public List<ActionWrapper> search(Collection<ActionWrapper> list, String keyword) {
        if (keyword.trim().isEmpty()) return Collections.emptyList();
        String[] keywords = keyword.split("\\P{L}+");
        Set<String> keywordSet = Arrays.stream(keywords).flatMap(k -> {
            String keywordInUpper = k.toUpperCase(TR);
            String keywordInUpperEnglish = k.toUpperCase(Locale.ENGLISH);
            return Arrays.asList(keywordInUpper, keywordInUpperEnglish).stream();

        }).collect(Collectors.toSet());

        List<ActionWrapper> candidates = list.stream().filter(a -> {
            for (String k : keywordSet) {
                for (String tag : a.getKeywords()) {
                    if (tag.contains(k)) {
                        return true;
                    }
                }
            }
            return false;
        }).collect(Collectors.toList());
        candidates.sort((a1, a2) -> {
            int score1 = 0, score2 = 0;
            for (String k : keywordSet) {
                for (String tag : a1.getKeywords()) {
                    if (tag.contains(k)) {
                        score1++;
                    }
                }
                for (String tag : a2.getKeywords()) {
                    if (tag.contains(k)) {
                        score2++;
                    }
                }
            }
            return Integer.compare(score2, score1);
        });

        return candidates.stream().limit(4).collect(Collectors.toList());


    }
}
