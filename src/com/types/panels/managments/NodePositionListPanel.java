package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.design.Filters;
import com.types.interfaces.Position;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.NodePositionList;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class NodePositionListPanel extends StandartPanel {

	// ID da janela
	private static final long serialVersionUID = 5046594777285996932L;

	// TAD respectivo
	private NodePositionList<Object> nodesList = new NodePositionList<Object>();

	// Campos
	private JComboBox<String> whereUser, listNodes;
	private JTextField valueUser;

	// Construtor
	public NodePositionListPanel() {
		init();
		renderComponents();
	}

	// Renderização padrão
	public void renderComponents() {
		tables = new Tables(3);
		instanceButton();
		buttonEvents(nodesList);
		Descriptions.descriptionNodePositionList(this);
		generateLateral(true);
		
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

		listNodes = new JComboBox<String>(generateList());
		listNodes.setName("Do nó:");
		listNodes.setRenderer(listRenderer);
		listNodes.setAutoscrolls(true);
		listNodes.setLightWeightPopupEnabled(true);
		listNodes.setMaximumRowCount(4);
		
		String[] options = { "No ínicio", "No final", 
				"Antes (" + listNodes.getName().substring(0, listNodes.getName().length() - 1) + ")", 
				"Depois (" + listNodes.getName().substring(0, listNodes.getName().length() - 1)  + ")" };
		whereUser = new JComboBox<String>(options);
		whereUser.setName("Colocar:");
		whereUser.setRenderer(listRenderer);
		whereUser.setAutoscrolls(true);
		whereUser.setLightWeightPopupEnabled(true);
		whereUser.setMaximumRowCount(4);
		
		valueUser = new JTextField();
		valueUser.setName("Valor:");
		valueUser.setDocument(new Filters.JTextFieldLimit(25));
		valueUser.setHorizontalAlignment(JTextField.CENTER);
	}

	// Gerar entrada de inserção
	protected Entry insertEntry() {
		listNodes.setModel(new DefaultComboBoxModel<String>(generateList()));
		return new Entry(new JComponent[] { whereUser, listNodes, valueUser });
	}

	protected Entry removeEntry() {
		listNodes.setModel(new DefaultComboBoxModel<String>(generateList()));
		return new Entry(new JComponent[] { listNodes });
	}

	// Adicionado no TAD
	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectAndInsert();
				} catch (Exception ex) {
					
				} finally {
					insertEntry.dispose();
				}
			}
		});
	}

	// Removendo no TAD
	protected void removeEvent() {
		removeEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectAndRemove();
				} catch(Exception ex) {
					
				} finally {
					removeEntry.dispose();
				}
			}
		});
	}
	
	// Seleciona e insere com base na opção
	private void selectAndInsert() {
		int index = whereUser.getSelectedIndex();
		switch (index) {
			case 0: 
				nodesList.addFirst(valueUser.getText());
				tables.getInsertModel().addRow(new Object[] {
						"addFirst(" + valueUser.getText() + ")", null
				});
				break;
			case 1: 
				nodesList.addLast(valueUser.getText());
				tables.getInsertModel().addRow(new Object[] {
						"addLast(" + valueUser.getText() + ")", null
				});
				break;
			case 2: 
				nodesList.addBefore(getPos(listNodes.getSelectedItem()), valueUser.getText());
				tables.getInsertModel().addRow(new Object[] {
						"addBefore(" + listNodes.getSelectedItem() + ", " + valueUser.getText() + ")", null
				});
				break;
			case 3: 
				nodesList.addAfter(getPos(listNodes.getSelectedItem()), valueUser.getText());
				tables.getInsertModel().addRow(new Object[] {
						"addAfter(" + listNodes.getSelectedItem() + ", " + valueUser.getText() + ")", null
				});
				break;
		}
	}
	
	// Seleciona e remove com base na opção
	private void selectAndRemove() {
		tables.getRemoveModel().addRow(new Object[] {
				"remove(" + listNodes.getSelectedItem() + ")", 
				nodesList.remove(getPos(listNodes.getSelectedItem()))
		});
	}
	
	// Pegar posição com base no elemento
	private Position<Object> getPos(Object element) {
		Position<Object> pos = nodesList.first();
		for (@SuppressWarnings("unused") Object node : nodesList) {
			if (pos.element().toString().equalsIgnoreCase(element.toString())) {
				return pos;
			} else {
				pos = nodesList.next(pos);
			}
		}
		return null;
	}

	// Gerar a lista de nodos para seleção
	private String[] generateList() {
		String[] list = new String[nodesList.size()];
		int i = 0;
		for (Object text : nodesList) {
			list[i] = text.toString();
			i += 1;
		}
		return list;
	}
}
