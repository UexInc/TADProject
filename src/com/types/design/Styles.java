package com.types.design;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.types.gui.Main;

public final class Styles {
	public static Color darkRed = new Color(125, 28, 43);
	public static Color text_lightRed = new Color(246, 213, 218);

	public static void setStyleTable(JTable table) {
		table.getTableHeader().setDefaultRenderer(new Filters.HeaderRenderer());
		table.getTableHeader().setBackground(new Color(251, 234, 236));
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(new Filters.HTMLRenderer());
		}

		table.setBackground(darkRed);
		table.setForeground(text_lightRed);
		table.setBorder(BorderFactory.createLineBorder(null));
		table.setGridColor(new Color(166, 38, 57));
	}

	public static void setStyleScrollPanel(JScrollPane scroll) {
		scroll.getViewport().setBackground(new Color(251, 234, 236));
	}

	public static void setStyleButton(JButton button) {
		button.setFont(new Font("Arial", Font.BOLD, Main.SIZE.height / 50)); // fonte
		button.setBackground(new Color(91, 94, 166)); // cor do botão
		button.setForeground(new Color(255, 255, 255)); // cor do texto
	}

	public static void setStyleButtonLateral(JButton button) {
		button.setFont(new Font("Impact", Font.PLAIN, Main.SIZE.height / 50)); // fonte
		button.setBackground(new Color(155, 35, 53)); // cor do botão
		button.setForeground(new Color(246, 213, 218)); // cor do texto
		button.setBorder(null);
	}

	public static void setButtonsEnable(JButton[] buttons, boolean en) {
		for (JButton button : buttons) {
			button.setEnabled(en);
		}
	}

	public static void setStyleLabel(JLabel label) {

	}

	public static void setStyleField(JTextField jTextField) {
		// TODO Auto-generated method stub

	}

	public static String rowRender(String r) {
		String font = "<style>"
				+ "p{"
				+ "font-family:\"Impact\";"
				+ "font-weight:normal;"
				+ "}"
				+ "</style>";
		return "<html>" + font + "<p>" + r + "</p></html>";
	}

}
