package com.types.panels;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public final class UserEntries {

	public static JComboBox<Object> createComboBox(String labelName, Object[] list) {
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

		JComboBox<Object> combo = new JComboBox<Object>(list);
		combo.setName(labelName);
		combo.setRenderer(listRenderer);
		combo.setAutoscrolls(true);
		combo.setMaximumRowCount(4);
		return combo;
	}
	
	public static JTextField createField(String labelName, int limit, int align, Class<?> filter) {
		JTextField field = new JTextField();
		field.setName(labelName);
		field.setHorizontalAlignment(align);
		return field;
	}
	
}