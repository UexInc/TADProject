package com.types.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.types.main.Main;
import com.types.panels.Menu;
import com.types.panels.managments.ArrayIndexListPanel;
import com.types.panels.managments.ArrayQueuePanel;
import com.types.panels.managments.ArrayStackPanel;

public class Options implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String option = ((JComponent) e.getSource()).getName();
		
		if (option.equals(String.valueOf(0))) {
			Main.menu.setVisible(false);
			Main.mainFrame.add(new ArrayIndexListPanel());
		}
		
		else if (option.equals(String.valueOf(1))) {
			Main.menu.setVisible(false);
			Main.mainFrame.add(new ArrayStackPanel());
		}
		
		else if (option.equals(String.valueOf(2))) {
			Main.menu.setVisible(false);
			Main.mainFrame.add(new ArrayQueuePanel());
		}
		
		else if (option.equals(String.valueOf(Menu.texts.length + 2))) {
			Main.mainFrame.dispose();
		}
	}

}
