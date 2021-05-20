package com.types.controllers;

import javax.swing.table.DefaultTableModel;

import com.types.design.Styles;
import com.types.panels.EntryPanel;
import com.types.panels.ManagementPanel;
import com.types.panels.MenuPanel;
import com.types.tads.ArrayIndexList;
import com.types.tads.ArrayStack;

public class ControlType {

	private static DefaultTableModel[] model;
	private static int option;

	public ControlType(DefaultTableModel[] model, int option) {
		ControlType.model = model;
		ControlType.option = option;
	}

	// Visualizar na tabela de inserções e inserindo no TAD respectivo
	@SuppressWarnings("unchecked")
	public static void insert(Object[] data) {
		data = filterObjects(data);
		switch (option) {
		case 0: Types.execute(((ArrayIndexList<Object>) Types.type), "add", new Class[] { int.class, java.lang.Object.class }, data, model[0]); break;
		case 1: Types.execute(((ArrayStack<Object>) Types.type), "push", new Class[] { java.lang.Object.class }, data, model[0]); break;
		case 2: break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
		case 7: break;
		case 8: break;
		case 9: break;
		case 10: break;
		case 11: break;
		}
	}

	@SuppressWarnings("unchecked")
	public static void remove(Object[] data) {
		data = filterObjects(data);
		switch (option) {
		case 0: Types.execute(((ArrayIndexList<Object>) Types.type), "remove", new Class[] { int.class }, data, model[1]); break;
		case 1:	Types.execute(((ArrayStack<Object>) Types.type), "pop", new Class[] { }, data, model[1]); break;
		case 2: break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
		case 7: break;
		case 8: break;
		case 9: break;
		case 10: break;
		case 11: break;
		}
	}

	public static void view() {
		if (option < MenuPanel.textButtons.length) {
			model[2].addRow(new Object[] {Types.type.toString() });
		}
	}

	private static Object[] filterObjects(Object[] data) {
		Object[] filter = data;
		for (int i = 0; i < data.length; i++) {
			try {
				filter[i] = Integer.parseInt(data[i].toString());
				break;
			} catch (Exception e) {
			}
			try {
				filter[i] = Double.parseDouble(data[i].toString());
				break;
			} catch (Exception e) {
			}
			try {
				if (data[i].toString().toLowerCase().strip() == "true"
						|| data[i].toString().toLowerCase().strip() == "false")
					filter[i] = Boolean.parseBoolean(data[i].toString());
				break;
			} catch (Exception e) {
			}
			try {
				if (data[i].toString().length() == 1)
					data[i] = data[i].toString().charAt(0);
				break;
			} catch (Exception e) {
			}
			try {
				filter[i] = data[i].toString();
				break;
			} catch (Exception e) {
			}
		}
		return filter;
	}

	public void renderBy(int action) {
		if (action == 1) {
			new EntryPanel(Types.getTexts(action), action);
		} else if (action == 2) {
			if (!specialCases()) {				
				new EntryPanel(Types.getTexts(action), action);
			}
		}
	}
	
	private boolean specialCases() {
		switch (option) {
			case 1: remove(new Object[] {});
				Styles.setButtonsEnable(ManagementPanel.getButtons(), true); return true;
		}
		return false;
	}
}
