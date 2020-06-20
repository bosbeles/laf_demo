package com.bsbls.deneme.laf.desktopx.view;

import com.bsbls.deneme.laf.desktopx.action.controller.ActionController;
import com.bsbls.deneme.laf.desktopx.action.model.ActionMenu;
import com.bsbls.deneme.laf.desktopx.action.model.ActionSubMenu;
import com.bsbls.deneme.laf.util.Gbc;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.function.Function;

import static com.bsbls.deneme.laf.util.GuiUtil.transparentPanel;

public class TabContentPanel extends JScrollPane {


    private final ActionMenu menu;
    private final ActionController controller;
    private Runnable beforeInit;

    public TabContentPanel(ActionMenu menu, ActionController controller) {
        this.menu = menu;
        this.controller = controller;

        setBorder(BorderFactory.createEmptyBorder());

        initPanel();
    }

    public void setBeforeInit(Runnable beforeInit) {
        this.beforeInit = beforeInit;
    }

    public ActionMenu getMenu() {
        return menu;
    }



    public void updatePanel() {

        if (beforeInit != null) {
            beforeInit.run();
        }

        initPanel();
    }

    private void initPanel() {
        JXPanel content = new JXPanel(new GridBagLayout());


        int i = 0;

        Function<Gbc, Gbc> gbcFunction = gbc -> gbc.fillHorizontal().anchor(GridBagConstraints.WEST);

        ActionSubMenu searchMenu = menu.getSearchMenu();
        if (!searchMenu.getActionList().isEmpty()) {
            content.add(new SubMenuPanel(searchMenu, controller), Gbc.constraints(i++, 0).applyAndBuild(gbcFunction));
        }

        List<ActionSubMenu> subMenus = menu.getSubMenus();
        for (ActionSubMenu subMenu : subMenus) {
            content.add(new SubMenuPanel(subMenu, controller), Gbc.constraints(i++, 0).applyAndBuild(gbcFunction));
        }

        content.add(transparentPanel(), Gbc.constraints(i++, 0).fillBoth().build());

        content.setScrollableTracksViewportHeight(false);
        content.setScrollableTracksViewportWidth(true);

        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        this.setViewportView(content);

        KeyboardFocusManager.getCurrentKeyboardFocusManager()
                .addPropertyChangeListener("focusOwner",
                        new PropertyChangeListener() {

                            @Override
                            public void propertyChange(PropertyChangeEvent evt) {
                                if (!(evt.getNewValue() instanceof JComponent)) {
                                    return;
                                }
                                JComponent focused = (JComponent) evt.getNewValue();
                                if (content.isAncestorOf(focused)) {
                                    focused.scrollRectToVisible(focused.getBounds());
                                }
                            }
                        });

        validate();

        content.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(e);
            }
        });

    }
}
