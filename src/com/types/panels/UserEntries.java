package com.types.panels;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
		field.setDocument(new JTextFieldLimit(limit));
		return field;
	}

	@SuppressWarnings("serial")
	public static class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

}
