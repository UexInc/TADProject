package com.types.design;

import java.awt.Color;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public final class Filters {

	public static void limitNumbers(JTextField field) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
			Pattern regEx = Pattern.compile("\\d*");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				Matcher matcher = regEx.matcher(text);
				if (!matcher.matches()) {
					return;
				}
				super.replace(fb, offset, length, text, attrs);
			}
		});
	}

	@SuppressWarnings("serial")
	public static class JTextFieldLimit extends PlainDocument {
		private int limit;

		public JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		JTextFieldLimit(int limit, boolean upper) {
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

	@SuppressWarnings("serial")
	public static class HTMLRenderer extends JTextPane implements TableCellRenderer {

		public HTMLRenderer() {
			StyledDocument doc = getStyledDocument();
			SimpleAttributeSet center = new SimpleAttributeSet();
			StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
			StyleConstants.setFontFamily(center, "Arial");
			StyleConstants.setFontSize(center, 13);
			StyleConstants.setBold(center, true);
			doc.setParagraphAttributes(0, doc.getLength(), center, false);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (table == null) {
				return this;
			}
			
			Color background = table.getBackground();
			if (background == null || background instanceof javax.swing.plaf.UIResource) {
				Color alternateColor = UIManager.getColor("Table.alternateRowColor");
				if (alternateColor != null && row % 2 != 0) {
					background = alternateColor;
				}
			}
			super.setForeground(Color.white);
			super.setBackground(background);
			
			if (isSelected) {
				setBackground(Styles.colorTheme_tooDark);
			}

			setText(value == null ? "" : value.toString());

			setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));
			int preferredHeight = getPreferredSize().height;

			if (table.getRowHeight(row) != preferredHeight) {
				table.setRowHeight(row, preferredHeight);
			}

			return this;
		}

	}

	@SuppressWarnings("serial")
	public static class HeaderRenderer extends JLabel implements TableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {

			setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			setHorizontalAlignment(CENTER);
			setText(value.toString());

			return this;
		}
	}

}
