package com.bsbls.deneme.laf.desktopx.action.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ActionMenu {

    private String title;

    private ActionSubMenu searchMenu = new ActionSubMenu("Search Results");

    private List<ActionSubMenu> subMenus = new ArrayList<>();


    public ActionMenu(String title) {
        this.title = title;
    }



    public ActionMenu add(ActionSubMenu subMenu) {
        subMenus.add(subMenu);
        return this;
    }

    public ActionMenu add(String title, String... actions) {
        return add(new ActionSubMenu(title).add(actions));
    }
}