package com.bsbls.deneme.laf.desktopx.view;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JPanel {

	/**
	 * Create the panel.
	 */
	public MenuButton(String text) {
		setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("<html>"+ text + "</html>", new ImageIcon(getClass().getClassLoader().getResource("search.png")));
		btnNewButton.setVerticalTextPosition(AbstractButton.BOTTOM);
		btnNewButton.setHorizontalTextPosition(AbstractButton.CENTER);
		btnNewButton.setPreferredSize(new Dimension(140, 90));
		add(btnNewButton);

	}

}