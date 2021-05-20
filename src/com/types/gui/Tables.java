package com.types.gui;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.types.design.Filters;
import com.types.panels.MenuPanel;

public class Tables {

	private JTable insertTable;
	private JTable removeTable;
	private JTable viewTable;

	private DefaultTableModel insertModel;
	private DefaultTableModel removeModel;
	private DefaultTableModel viewModel;

	public Tables(int option) {
		insertModel = createModelTable(new String[] { "Entrada", "Saída" });
		removeModel = createModelTable(new String[] { "Entrada", "Saída" });
		viewModel = createModelTable(
				new String[] { MenuPanel.textButtons[option].substring(4, MenuPanel.textButtons[option].length()) });

		insertTable = new JTable(insertModel);
		insertTable.setDefaultRenderer(Object.class, new Filters.HTMLRenderer());
		insertTable.setRowHeight(insertTable.getFont().getSize() * 2);

		removeTable = new JTable(removeModel);
		removeTable.setDefaultRenderer(Object.class, new Filters.HTMLRenderer());
		removeTable.setRowHeight(removeTable.getFont().getSize() * 2);

		viewTable = new JTable(viewModel);
		viewTable.setDefaultRenderer(Object.class, new Filters.HTMLRenderer());
		viewTable.setRowHeight(viewTable.getFont().getSize() * 2);

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
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
		}
	}

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
}
