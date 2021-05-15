package com.types.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.types.gui.Main;

public final class Styles {

	public static void setStyleTable(JTable table) {
		table.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 50));
		table.setBackground(new Color(0, 0, 0));
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new Filters.HTMLRenderer());
		}
		table.setForeground(new Color(255, 255, 255));
	}
	
	public static void setStyleButton(JButton button) {
		button.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 50));
		button.setBackground(new Color(91, 94, 166));
		button.setForeground(new Color(255, 255, 255));
	}
	
	public static void setButtonsEnable(JButton[] buttons, boolean en) {
		for (JButton button : buttons) {
			button.setEnabled(en);
		}
	}

	public static void setStyleLabel(JLabel jLabel) {
		// TODO Auto-generated method stub
		
	}

	public static void setStyleField(JTextField jTextField) {
		// TODO Auto-generated method stub
		
	}

	public static String rowRender(String r) {
		return r;
	}
	
}
