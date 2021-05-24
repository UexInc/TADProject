package com.types.controllers;

import javax.swing.table.DefaultTableModel;

import com.types.design.Styles;
import com.types.interfaces.Position;
import com.types.nodes.TreeNode;
import com.types.panels.EntryPanel;
import com.types.panels.ManagementPanel;
import com.types.panels.MenuPanel;
import com.types.tads.ArrayIndexList;
import com.types.tads.ArrayQueue;
import com.types.tads.ArrayStack;
import com.types.tads.LinkedTree;
import com.types.tads.NodePositionList;
import com.types.util.AuxTAD;

public class ControlType {

	private static DefaultTableModel[] model;
	private static byte option;

	public ControlType(DefaultTableModel[] model, byte option) {
		ControlType.model = model;
		ControlType.option = option;
	}

	// Visualizar na tabela de inserções e inserindo no TAD respectivo
	@SuppressWarnings("unchecked")
	public static void insert(Object[] data) {
		data = filterObjects(data);
		switch (option) {
		case 0: Types.execute(((ArrayIndexList<Object>) Types.type), "add", 
				new Class[] { int.class, java.lang.Object.class }, data, model[0]); break;
		case 1: Types.execute(((ArrayStack<Object>) Types.type), "push", 
				new Class[] { java.lang.Object.class }, data, model[0]); break;
		case 2: Types.execute(((ArrayQueue<Object>) Types.type), "enqueue", 
				new Class[] { java.lang.Object.class }, data, model[0]); break;
		case 3: selectExecute(data); break;
		case 4: selectExecute(data); break;
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
		switch (option) 
		{
		case 0: Types.execute(((ArrayIndexList<Object>) Types.type), "remove", 
				new Class[] { int.class }, data, model[1]); break;
		case 1:	Types.execute(((ArrayStack<Object>) Types.type), "pop", 
				new Class[] { }, data, model[1]); break;
		case 2: Types.execute(((ArrayQueue<Object>) Types.type), "dequeue", 
				new Class[] { }, data, model[1]); break;
		case 3: Types.execute(((NodePositionList<Object>) Types.type), "remove", 
				new Class[] { Position.class }, 
				new Object[] { AuxTAD.getPos(data[0]) }, model[1]); break;
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
	
	@SuppressWarnings("unchecked")
	private static void selectExecute(Object[] data) {
		if (data[0] == "No ínicio") {
			try { Types.execute(((NodePositionList<Object>) Types.type), "addFirst", 
					new Class[] { java.lang.Object.class }, 
					new Object[] { data[2] }, model[0]);
			} catch(Exception e) { }
		} else if (data[0] == "No final") {
			try { Types.execute(((NodePositionList<Object>) Types.type), "addLast", 
					new Class[] { java.lang.Object.class }, 
					new Object[] { data[2] }, model[0]);
			} catch(Exception e) { }
		} else if (data[0] == "Antes de (Especificação)") {
			try { Types.execute(((NodePositionList<Object>) Types.type), "addBefore", 
					new Class[] { Position.class, java.lang.Object.class }, 
					new Object[] { AuxTAD.getPos(data[1]), data[2] }, model[0]);
			} catch(Exception e) { }
		} else if (data[0] == "Depois de (Especificação)") {
			try { Types.execute(((NodePositionList<Object>) Types.type), "addAfter", 
					new Class[] { Position.class, java.lang.Object.class }, 
					new Object[] { AuxTAD.getPos(data[1]), data[2] }, model[0]);
			} catch(Exception e) { }
		} else {
			Position<Object> posFind = AuxTAD.getPos(((LinkedTree<Object>) Types.type), data[0]);
			if (posFind != null) {				
				AuxTAD.createChild((TreeNode<Object>) posFind, data[1]);
				((LinkedTree<Object>) Types.type).increaseSize();
			} else {
				((LinkedTree<Object>) Types.type).addRoot(data[1]);
				((LinkedTree<Object>) Types.type).root().
				setChildren(new NodePositionList<Position<Object>>());
			}
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
			new EntryPanel(Types.getTexts(action), option, action);
		} else if (action == 2) {
			if (!specialCases()) {				
				new EntryPanel(Types.getTexts(action), option, action);
			}
		}
	}
	
	private boolean specialCases() {
		switch (option) {
			case 1: case 2: case 4:
				remove(new Object[] {});
				Styles.setButtonsEnable(ManagementPanel.getButtons(), true); 
				return true;
		}
		return false;
	}
}
