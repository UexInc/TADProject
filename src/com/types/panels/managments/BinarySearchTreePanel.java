package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.panels.UserEntries;
import com.types.tads.BinarySearchTree;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class BinarySearchTreePanel extends StandartPanel {

	private static final long serialVersionUID = 7079469686451647009L;
	
	private BinarySearchTree<Object, Object> ABB = new BinarySearchTree<Object, Object>();
	
	private JTextField keyUser, valueUser;
	private JComboBox<Object> keys;
	
	public BinarySearchTreePanel() {
		init();
		renderComponents();
	}
	
	public void renderComponents() {
		tables = new Tables(9);
		instanceButton();
		buttonEvents(ABB);
		Descriptions.descriptionABB(this);
		generateLateral(true);
		
		keyUser = UserEntries.createField("Chave:", 20, JTextField.CENTER, Object.class);
		valueUser = UserEntries.createField("Valor:", 20, JTextField.CENTER, Object.class);
		keys = UserEntries.createComboBox("Chaves:", generateList());
	}

	protected Entry insertEntry() {
		return new Entry(new JComponent[] { keyUser, valueUser });
	}

	protected Entry removeEntry() {
		keys.setModel(new DefaultComboBoxModel<Object>(generateList()));
		return new Entry(new JComponent[] { keys });
	}

	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tables.getInsertModel()
							.addRow(new Object[] { 
									"put(" + keyUser.getText() + ", " + 
											valueUser.getText() + ")", 
											ABB.put(keyUser.getText(), valueUser.getText()) });
				} catch(Exception ex) {
					
				} finally {
					insertEntry.dispose();
				}
			}
		});
	}

	protected void removeEvent() {
		removeEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tables.getRemoveModel().addRow(new Object[] {
							"remove(" + keys.getSelectedItem() + ")", 
							ABB.remove(keys.getSelectedItem())
					});
				} catch (Exception ex) {

				} finally {
					removeEntry.dispose();
				}
			}
		});
	}
	
	private String[] generateList() {
		String[] list = new String[ABB.size()];
		int c = 0;
		for (Object key : ABB.keySet()) {
			list[c] = key.toString();
			c++;
		}
		return list;
	}

}