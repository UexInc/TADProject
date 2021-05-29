package com.types.util;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.types.panels.Menu;

public class Tables {

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
}