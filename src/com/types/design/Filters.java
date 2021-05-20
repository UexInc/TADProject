package com.types.design;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

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
	public static class HTMLRenderer extends JPanel implements TableCellRenderer {

		private JLabel label;
		private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
		private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
		protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

		public HTMLRenderer() {
			label = new DefaultTableCellRenderer();
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			add(label);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (table == null) {
				return this;
			}

			Color fg = null;
			Color bg = null;

			JTable.DropLocation dropLocation = table.getDropLocation();
			if (dropLocation != null && !dropLocation.isInsertRow() && !dropLocation.isInsertColumn()
					&& dropLocation.getRow() == row && dropLocation.getColumn() == column) {

				fg = UIManager.getColor("Table.dropCellForeground");
				bg = UIManager.getColor("Table.dropCellBackground");

				isSelected = true;
			}

			if (isSelected) {
				super.setForeground(fg == null ? table.getSelectionForeground() : fg);
				super.setBackground(bg == null ? table.getSelectionBackground() : bg);
			} else {
				Color background = table.getBackground();
				if (background == null || background instanceof javax.swing.plaf.UIResource) {
					Color alternateColor = UIManager.getColor("Table.alternateRowColor");
					if (alternateColor != null && row % 2 != 0) {
						background = alternateColor;
					}
				}
				super.setForeground(table.getForeground());
				super.setBackground(background);
			}

			setFont(table.getFont());

			if (hasFocus) {
				Border border = null;
				if (isSelected) {
					border = UIManager.getBorder("Table.focusSelectedCellHighlightBorder");
				}
				if (border == null) {
					border = UIManager.getBorder("Table.focusCellHighlightBorder");
				}
				setBorder(border);

				if (!isSelected && table.isCellEditable(row, column)) {
					Color col;
					col = UIManager.getColor("Table.focusCellForeground");
					if (col != null) {
						super.setForeground(col);
					}
					col = UIManager.getColor("Table.focusCellBackground");
					if (col != null) {
						super.setBackground(col);
					}
				}
			} else {
				setBorder(getNoFocusBorder());
			}

//			label.setPreferredSize(getPreferredSize());
			label.setText(value == null ? "" : Styles.rowRender(value.toString()));
			return this;
		}

		protected Border getNoFocusBorder() {
			Border border = UIManager.getBorder("Table.cellNoFocusBorder");
			if (System.getSecurityManager() != null) {
				if (border != null)
					return border;
				return SAFE_NO_FOCUS_BORDER;
			} else if (border != null) {
				if (noFocusBorder == null || noFocusBorder == DEFAULT_NO_FOCUS_BORDER) {
					return border;
				}
			}
			return noFocusBorder;
		}

	}

}
