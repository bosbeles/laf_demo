package com.bsbls.deneme.laf;

import com.bsbls.deneme.laf.desktopx.action.controller.ActionController;
import com.bsbls.deneme.laf.desktopx.action.factory.ActionFactory;
import com.bsbls.deneme.laf.desktopx.action.model.ActionDictionary;
import com.bsbls.deneme.laf.desktopx.action.model.ActionMenu;
import com.bsbls.deneme.laf.desktopx.action.model.ActionWrapper;
import com.bsbls.deneme.laf.desktopx.view.DesktopX;
import com.bsbls.deneme.laf.desktopx.view.SearchPanel;
import com.bsbls.deneme.laf.desktopx.view.TabContentPanel;
import com.bsbls.deneme.laf.test.GuiTester;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        GuiTester.test(Main::createGui, "FlatLight", Main::resize);
    }

    private static void resize(JFrame jFrame) {
        jFrame.setBounds(0, 0, 800, 600);
        jFrame.setLocationRelativeTo(null);
    }

    static Component lastFocusOwner;

    private static JComponent createGui(JFrame frame) {
        DesktopX desktop = new DesktopX();

        List<ActionWrapper> actionWrappers = ActionFactory.createActionWrappers();
        Map<String, ActionWrapper> map = actionWrappers.stream().collect(Collectors.toMap(ActionWrapper::getName, a -> a));
        ActionDictionary dictionary = new ActionDictionary(map);

        Map<String, Action> actionMap = map.keySet().stream().map(actionName -> new AbstractAction(actionName) {
            @Override
            public void actionPerformed(ActionEvent e) {
                desktop.makeVisible(actionName);
            }
        }).collect(Collectors.toMap(a -> (String) a.getValue(Action.NAME), a -> a));

        ActionController controller = new ActionController(actionMap);

        map.keySet().forEach(actionName -> desktop.createFrame(actionName));

        SearchPanel searchPanel = addSearchPanel(frame, dictionary);

        searchPanel.createTabPanel("Data Link Processor", createDataLinkProcessorMenus());
        searchPanel.createTabPanel("Jreap Processor", createJreapProcessorMenus());
        searchPanel.createTabPanel("Settings", createSettingMenus());


        KeyStroke ctrl_f = KeyStroke.getKeyStroke("ctrl F");

        //desktop.getInputMap().put(ctrl_f, "none");
        desktop.getInputMap(1).put(ctrl_f, "search");
        desktop.getInputMap(2).put(ctrl_f, "search");


        desktop.getInputMap().put(ctrl_f, "search");
        desktop.getActionMap().put("search", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPanel.setVisible(true);
                lastFocusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
                searchPanel.getSearchBox().requestFocusInWindow();

            }
        });

        return desktop;

    }

    private static JComponent createSettingMenus() {

        return new JPanel();
    }

    private static JComponent createJreapProcessorMenus() {

        ActionMenu jreMenu = new ActionMenu("Jreap Processor");

        jreMenu.add("Connection Management", "Link Configuration", "Echo Message")
                .add("Network", "Routing", "Network Connectivity", "Direct Connection", "Connectivity Feedback", "Latency Management", "Secondary Track Number List")
                .add("Filters", "Filter Templates", "Filter Management")
                .add("Others", "Record & Replay", "Link Capabilities");


        TabContentPanel contentPanel = new TabContentPanel(jreMenu);

        return contentPanel;

    }

    private static JComponent createDataLinkProcessorMenus() {
        ActionMenu dlpMenu = new ActionMenu("Data Link Processor");


        dlpMenu.add("Title 1", "Item 1.1", "Item 1.2", "Item 1.3")
                .add("Title 2", "Item 2.1")
                .add("Title 3", "Item 3.1", "Item 3.2");

        TabContentPanel contentPanel = new TabContentPanel(dlpMenu);

        return contentPanel;

    }

    private static SearchPanel addSearchPanel(JFrame frame, ActionDictionary dictionary) {
        JMenuBar menuBar = frame.getJMenuBar();
        menuBar.add(Box.createHorizontalGlue());

        SearchPanel searchPanel = new SearchPanel(dictionary);
        frame.getContentPane().add(searchPanel, BorderLayout.EAST);
        searchPanel.setVisible(false);

        JToggleButton showToggleButton = new JToggleButton();
        showToggleButton.addActionListener(e -> {
            boolean selected = showToggleButton.isSelected();
            if (selected) {

                searchPanel.setVisible(true);
            } else {
                searchPanel.setVisible(false);
            }

        });
        searchPanel.onEsc(s-> {
            if(!showToggleButton.isSelected()) {
                s.setVisible(false);
                if(lastFocusOwner != null) {
                    lastFocusOwner.requestFocusInWindow();
                }
            }

            // showToggleButton.setSelected(false);
        });
        showToggleButton.setFocusable(false);
        showToggleButton.setIcon(new FlatSVGIcon("show.svg"));
        menuBar.add(showToggleButton);

        return searchPanel;
    }

}
