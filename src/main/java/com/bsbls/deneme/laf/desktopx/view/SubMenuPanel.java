package com.bsbls.deneme.laf.desktopx.view;

import com.bsbls.deneme.laf.desktopx.action.model.ActionSubMenu;
import com.bsbls.deneme.laf.util.Gbc;
import com.bsbls.deneme.laf.util.WrapLayout;

import javax.swing.*;
import java.awt.*;

public class SubMenuPanel extends JPanel {

    private ActionSubMenu subMenu;

    public SubMenuPanel(ActionSubMenu subMenu) {
        this.subMenu = subMenu;
        setOpaque(false);
        initPanel();
    }

    private void initPanel() {
        setLayout(new GridBagLayout());
        JLabel label = new JLabel(subMenu.getName());
        label.setFont(label.getFont().deriveFont(label.getFont().getStyle() | Font.BOLD, 14f));
        add(label, Gbc.constraints(0, 0).insets(10, 10, 0, 10).build());

        JPanel panel = new JPanel(new WrapLayout(FlowLayout.LEFT, 10, 10));
        panel.setOpaque(false);

        for (String action : subMenu.getActionList()) {
            MenuButton menuButton = new MenuButton(action);
            panel.add(menuButton);
        }

        add(panel, Gbc.constraints(1, 0).insets(0, 0, 0, 0).fillBoth().build());

    }
}