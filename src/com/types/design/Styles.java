package com.types.design;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.types.gui.Main;

public final class Styles {
	// Cores TEMA - fundo
	public static Color colorTheme = new Color(81, 0, 148);
	public static Color colorTheme_dark = new Color(59, 0, 107);
	public static Color colorTheme_tooDark = new Color(38, 0, 69);
	public static Color colorTheme_light = new Color(115, 0, 209);
	public static Color colorTheme_tooLight = new Color(221, 179, 255);
	// Cores TEMA - texto
	public static Color colorTheme_textLight = new Color(221, 179, 255);
	// Outras cores

	public static void setStyleTable(JTable table) {
		table.getTableHeader().setDefaultRenderer(new Filters.HeaderRenderer());
		table.getTableHeader().setBackground(colorTheme_tooLight);
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new Filters.HTMLRenderer());
		}
		table.setBackground(colorTheme);
		table.setForeground(colorTheme_textLight);
		table.setBorder(BorderFactory.createLineBorder(null));
		table.setGridColor(colorTheme_tooDark);
	}

	public static void setStyleScrollPanel(JScrollPane scroll) {
		scroll.getViewport().setBackground(colorTheme_tooLight);
	}

	public static void setStyleModelPanel(JPanel panel) {
		panel.setBackground(colorTheme);
	}

	public static void setStyleButton(JButton button) {
		button.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 50)); // fonte
		button.setBackground(new Color(91, 94, 166)); // cor do botão
		button.setForeground(new Color(255, 255, 255)); // cor do texto
	}

	public static void setStyleButtonLateral(JButton button) {
		button.setFont(new Font("Impact", Font.PLAIN, Main.SIZE.height / 50)); // fonte
		button.setBackground(colorTheme_dark); // cor do botão
		button.setForeground(colorTheme_textLight); // cor do texto
		button.setBorder(null);
	}

	public static void setButtonsEnable(JButton[] buttons, boolean en) {
		for (JButton button : buttons) {
			button.setEnabled(en);
		}
	}

	public static void setStyleLabel(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 60)); // fonte
		label.setForeground(colorTheme_textLight);
	}

	public static void setStyleField(JTextField field) {
		field.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 60)); // fonte
		field.setBorder(null);
		field.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	public static String rowRender(String r) {
		String font = "<style>" + "p{" + "font-family:\"Impact\";" + "font-weight:normal;" + "}" + "</style>";
		return "<html>" + font + "<p>" + r + "</p></html>";
	}

}
