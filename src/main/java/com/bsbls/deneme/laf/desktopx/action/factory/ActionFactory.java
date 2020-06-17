package com.bsbls.deneme.laf.desktopx.action.factory;

import com.bsbls.deneme.laf.desktopx.action.model.ActionWrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ActionFactory {


    public static List<ActionWrapper> createActionWrappers() {
        List<ActionWrapper> actionWrapperList = new ArrayList<>();

        add(actionWrapperList, "action.echo", "Echo", "Yankı", "Ping");
        add(actionWrapperList, "action.operator", "Operator to Operator Message", "Operator", "Operatör", "Operatör Mesaj");
        add(actionWrapperList, "action.threshold", "Latency Threshold Message", "Gecikme Eşiği", "Latency Exceeded Message", "Gecikme Aşımı");
        add(actionWrapperList, "action.filterTemplates", "Filter Templates", "Filtre Şablonları");
        add(actionWrapperList, "action.filter", "Filters", "Filtreler", "Filter Management", "Filtre Yönetimi");
        add(actionWrapperList, "action.filterLocal", "Local Filters", "Yerel Filtreler");
        add(actionWrapperList, "action.filterRemote", "Remote Filters", "Karşı Filtreler", "Uzak Filtreler");
        add(actionWrapperList, "action.routing", "Routing", "Yönlendirme");
        add(actionWrapperList, "action.connectivity", "Network Connectivity Matrix", "Ağ Bağlantı Matrisi");
        add(actionWrapperList, "action.directConnection", "Direct Connection List", "Doğrudan Bağlantı Listesi");
        add(actionWrapperList, "action.preferences", "Preferences", "Ayarlar");
        add(actionWrapperList, "action.monitor", "Link Monitor", "Bağlantı Monitörü");
        add(actionWrapperList, "action.record", "Record", "Replay", "Kayıt", "Yeniden Oynatma", "Record&Replay");
        add(actionWrapperList, "action.configuration", "Link Configuration", "Bağlantı Ayarları");
        add(actionWrapperList, "action.closeAll", "Close All Windows", "Tüm Pencereleri Kapat");
        add(actionWrapperList, "action.minimizeAll", "Minimize All Windows", "Tüm Pencereleri Küçült");
        add(actionWrapperList, "action.restoreAll", "Restore All Windows", "Tüm Pencereleri Aç");
        add(actionWrapperList, "action.cascadeAll", "Cascase All Windows", "Tüm Pencereleri Diz");
        add(actionWrapperList, "action.closeOther", "Close Other Windows", "Diğer Pencereleri Kapat");


        return actionWrapperList;
    }

    private static void add(List<ActionWrapper> actionWrapperList, String name, String... keywords) {

        actionWrapperList.add(
                ActionWrapper.builder().
                        name(name).
                        keywords(
                                Arrays.stream(keywords)
                                        .flatMap(k ->
                                                Arrays.asList(k.toUpperCase(Locale.ENGLISH),
                                                        k.toUpperCase(Locale.ENGLISH)).stream()).collect(Collectors.toSet()))
                        .build());

    }

}
