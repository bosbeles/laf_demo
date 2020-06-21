package com.bsbls.deneme.laf.desktopx.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;

public class DesktopX extends JDesktopPane {


    private List<InternalFrameX> frameList = new ArrayList<>();

    private static Random random = new Random();


    private void createShortcuts() {
        addShortcut("ctrl shift W", "action.window.closeAll", e -> {
            JInternalFrame[] allFrames = this.getAllFrames();
            JInternalFrame selectedFrame = this.getSelectedFrame();
            for (int i = 0; i < allFrames.length; i++) {
                JInternalFrame frame = allFrames[i];
                if (frame.isClosable())
                    getDesktopManager().closeFrame(frame);
            }
        });
        addShortcut("ctrl W", "action.window.closeOthers", e -> {
            JInternalFrame[] allFrames = this.getAllFrames();
            JInternalFrame selectedFrame = this.getSelectedFrame();
            for (int i = 0; i < allFrames.length; i++) {
                JInternalFrame frame = allFrames[i];
                if (frame != selectedFrame && frame.isClosable())
                    getDesktopManager().closeFrame(frame);
            }
        });


        KeyStroke ctrlTab = KeyStroke.getKeyStroke("ctrl TAB");
        KeyStroke ctrlShiftTab = KeyStroke.getKeyStroke("ctrl shift TAB");

        // Remove ctrl-tab from normal focus traversal
        Set<AWTKeyStroke> forwardKeys = new HashSet<AWTKeyStroke>(this.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS));
        forwardKeys.remove(ctrlTab);
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);

        // Remove ctrl-shift-tab from normal focus traversal
        Set<AWTKeyStroke> backwardKeys = new HashSet<AWTKeyStroke>(this.getFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS));
        backwardKeys.remove(ctrlShiftTab);
        this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);

        addShortcut("ctrl TAB", "action.window.next", e -> {
            int index = getNextFrameIndex();
            activeFrame(index);
        });

        addShortcut("ctrl shift TAB", "action.window.previous", e -> {
            int index = getPreviousFrameIndex();
            activeFrame(index);
        });
    }

    private void activeFrame(int index) {
        try {
            InternalFrameX frame = frameList.get(index);
            if (frame.isIcon()) {
                frame.setIcon(false);
            }
            frame.setSelected(true);

        } catch (PropertyVetoException propertyVetoException) {
            propertyVetoException.printStackTrace();
        }
    }

    private void addShortcut(String key, String name, Consumer<ActionEvent> action) {
        getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(key), name);
        getActionMap().put(name, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.accept(e);
            }
        });
    }

    public void makeVisible(String actionName) {
        frameList.stream()
                .filter(f -> f.getTitle().equals(actionName))
                .forEach(this::makeVisible);

    }

    public DesktopX() {
        createShortcuts();

        this.setDesktopManager(new DefaultDesktopManager() {
            @Override
            public void iconifyFrame(JInternalFrame f) {
                super.iconifyFrame(f);

                JInternalFrame.JDesktopIcon icon = f.getDesktopIcon();
                Dimension prefSize = icon.getPreferredSize();
                icon.setBounds(f.getX(), f.getY(), prefSize.width * 2, prefSize.height);
            }
        });

    }

    public InternalFrameX createFrame(String actionName) {
        InternalFrameX internalFrame = createInternalFrame(this, actionName);
        frameList.add(internalFrame);
        internalFrame.setVisible(true);
        return internalFrame;
    }

    private int getSelectedFrameIndex() {
        return frameList.indexOf(getSelectedFrame());
    }

    private int getNextFrameIndex() {
        int next = getSelectedFrameIndex();
        for (int i = 0; i < frameList.size(); i++) {
            next = (next + 1) % frameList.size();
            if (frameList.get(next).isVisible()) {
                break;
            }
        }
        return next;
    }

    private int getPreviousFrameIndex() {
        int previous = getSelectedFrameIndex();
        for (int i = 0; i < frameList.size(); i++) {
            previous = (previous + frameList.size() - 1) % frameList.size();
            if (frameList.get(previous).isVisible()) {
                break;
            }
        }
        return previous;
    }

    private static InternalFrameX createInternalFrame(JDesktopPane desktopPane, String name) {

        InternalFrameX iFrame = new InternalFrameX(name, true, true, true, true);
        iFrame.setMaximizable(random.nextBoolean());
        iFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        iFrame.setLayout(new FlowLayout());
        iFrame.add(new JTextField(10));
        iFrame.add(new JButton("Deneme"));
        iFrame.add(new JCheckBox("Deneme"));
        iFrame.add(new JComboBox<String>(new String[]{"A", "B", "C"}));
        iFrame.add(new JList<>());
        String data[][] = {{"101", "Amit", "670000"},
                {"102", "Jai", "780000"},
                {"102", "Jai", "780000"},
                {"102", "Jai", "780000"},
                {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable table = new JTable(data, column);
        iFrame.add(table);

        iFrame.setSize(200, 150);
        iFrame.setLocation((int) (Math.random() * 500), (int) (Math.random() * 500));
        desktopPane.add(iFrame);
        return iFrame;

    }

    private void makeVisible(JInternalFrame fr) {
        if (fr.getDesktopPane() == null) {
            add(fr);
        }
        fr.moveToFront();
        fr.setVisible(true);
        try {
            fr.setSelected(true);
            if (fr.isIcon()) {
                fr.setIcon(false);
            }
            fr.setSelected(true);
        } catch (PropertyVetoException ex) {

        }
        fr.requestFocusInWindow();
    }
}
