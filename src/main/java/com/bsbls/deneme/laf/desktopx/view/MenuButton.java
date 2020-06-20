package com.bsbls.deneme.laf.desktopx.view;

import com.bsbls.deneme.laf.desktopx.action.controller.ActionController;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class MenuButton extends JPanel {

    /**
     * Create the panel.
     */
    public MenuButton(String text, ActionController controller) {

        setLayout(new BorderLayout(0, 0));


        String textInLocale = text;
        if(ResourceBundle.getBundle("actions").containsKey(text)) {
            textInLocale = ResourceBundle.getBundle("actions").getString(text);
        }
        JButton btnNewButton = new JButton("<html>" + textInLocale + "</html>", new ImageIcon(getClass().getClassLoader().getResource("search.png")));
        btnNewButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        btnNewButton.setHorizontalTextPosition(AbstractButton.CENTER);
        btnNewButton.setPreferredSize(new Dimension(140, 90));

        btnNewButton.addActionListener(e -> {
			controller.doAction(text);
        });

        add(btnNewButton);

    }

}