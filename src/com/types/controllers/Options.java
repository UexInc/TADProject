package com.types.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.types.main.Main;
import com.types.panels.managments.ArrayIndexListPanel;
import com.types.panels.managments.ArrayQueuePanel;
import com.types.panels.managments.ArrayStackPanel;
import com.types.panels.managments.LinkedBinaryTreePanel;
import com.types.panels.managments.LinkedTreePanel;
import com.types.panels.managments.NodePositionListPanel;

public class Options implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		int option = Integer.parseInt(((JComponent) e.getSource()).getName());
		
		switch (option) {
			case 0: disableMenu(); Main.mainFrame.add(new ArrayIndexListPanel()); break;
			case 1: disableMenu(); Main.mainFrame.add(new ArrayStackPanel()); break;
			case 2: disableMenu(); Main.mainFrame.add(new ArrayQueuePanel()); break;
			case 3: disableMenu(); Main.mainFrame.add(new NodePositionListPanel()); break;
			case 4: disableMenu(); Main.mainFrame.add(new LinkedTreePanel()); break;
			case 5: disableMenu(); Main.mainFrame.add(new LinkedBinaryTreePanel()); break;
			case 14: Main.mainFrame.dispose(); break;
		}
	}
	
	private void disableMenu() {
		Main.menu.setVisible(false);
	}

}
