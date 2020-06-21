package com.bsbls.deneme.laf.desktopx.view;

import javax.swing.*;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;

public class InternalFrameX extends JInternalFrame {

    public InternalFrameX(String title, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable) {
        super(title, resizable, closable, maximizable, iconifiable);

        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    InternalFrameX frame = InternalFrameX.this;
                    if (!frame.isMaximizable() && frame.isIconifiable() && !frame.isIcon) {
                        try {
                            frame.setIcon(true);
                        } catch (PropertyVetoException e2) {
                        }
                    }
                }
            }
        };

        this.addPropertyChangeListener("UI", e -> {
            Object newValue = e.getNewValue();
            registerListener(listener, newValue);
        });

        InternalFrameUI ui = this.getUI();
        registerListener(listener, ui);

    }

    private void registerListener(MouseListener listener, Object value) {
        if (value instanceof BasicInternalFrameUI) {
            ((BasicInternalFrameUI) value).getNorthPane().addMouseListener(listener);
        }
    }
}
