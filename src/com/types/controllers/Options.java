package com.types.controllers;

import java.awt.Frame;

import com.types.panels.MenuPanel;
import com.types.panels.ManagementPanel;

public class Options {

	public Options(Frame renderOn, int option, Object obj) {
		switch (option) {
		case 13:
			Frame[] f = (Frame[]) obj;
			for (Frame frame : f) {
				frame.dispose();
			}
			break;
		default:
			MenuPanel menu = (MenuPanel) obj;
			menu.panelOff();
			renderOn.add(new ManagementPanel(menu, option));
			break;
		}
	}

}
