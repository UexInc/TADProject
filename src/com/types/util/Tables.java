package com.types.util;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.types.panels.Menu;

public class Tables implements TableCellRenderer {

	private JTable insertTable;
	private JTable removeTable;
	private JTable viewTable;

	private DefaultTableModel insertModel;
	private DefaultTableModel removeModel;
	private DefaultTableModel viewModel;

	public Tables(int option) {
		insertModel = createModelTable(new String[] { "Entrada", "Sa�da" });
		removeModel = createModelTable(new String[] { "Entrada", "Sa�da" });
		viewModel = createModelTable(new String[] { Menu.texts[option].substring(4, Menu.texts[option].length()) });

		insertTable = new JTable(insertModel);

		if (Menu.texts[option] == "TAD-�rvore Gen�rica")
			removeTable = null;
		else
			removeTable = new JTable(removeModel);
		
		viewTable = new JTable(viewModel);

		setResizeTable(new JTable[] { this.insertTable, this.removeTable, this.viewTable });
	}

	@SuppressWarnings("serial")
	private DefaultTableModel createModelTable(Object[] columns) {
		return new DefaultTableModel(columns, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private void setResizeTable(JTable[] tables) {
		for (JTable table : tables) {
			if (table != null) {
				table.getTableHeader().setReorderingAllowed(false);
				table.getTableHeader().setResizingAllowed(false);
				for (int i = 0; i < table.getColumnCount(); i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(this);
				}
			}
		}
	}

	/* Getters & Setters */
	public JTable getInsertTable() {
		return insertTable;
	}

	public JTable getRemoveTable() {
		return removeTable;
	}

	public JTable getViewTable() {
		return viewTable;
	}

	public DefaultTableModel getInsertModel() {
		return insertModel;
	}

	public DefaultTableModel getRemoveModel() {
		return removeModel;
	}

	public DefaultTableModel getViewModel() {
		return viewModel;
	}
	/* */

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JTextArea text = new JTextArea();
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		text.setText(value == null ? "" : value.toString());
		text.setSize(table.getColumnModel().getColumn(column).getWidth(), table.getRowHeight(row));
		
		int preferredHeight = text.getPreferredSize().height;

		if (table.getRowHeight(row) != preferredHeight) {
			table.setRowHeight(row, preferredHeight);
		}
		
		return text;
	}
}
