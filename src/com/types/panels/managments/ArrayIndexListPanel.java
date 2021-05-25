package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.design.Filters;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.ArrayIndexList;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class ArrayIndexListPanel extends StandartPanel {

	// ID da janela
	private static final long serialVersionUID = 1515352884479601080L;

	// TAD respectivo
	ArrayIndexList<Object> arrayList = new ArrayIndexList<Object>();

	// Campos
	private JTextField posUser;
	private JTextField valueUser;

	// Contrutor
	public ArrayIndexListPanel() {
		init();
		renderComponents();
	}

	// Renderização padrão
	public void renderComponents() {
		tables = new Tables(0);
		instanceButton();
		buttonEvents(arrayList);
		Descriptions.descriptionArrayList(this);
		generateLateral(true);
	}

	// Adicionado no TAD
	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					arrayList.add(Integer.parseInt(posUser.getText()), valueUser.getText());
					tables.getInsertModel()
							.addRow(new Object[] { 
									"add(" + posUser.getText() + ", " + 
											valueUser.getText() + ")", null });
				} catch(Exception ex) {
					showError("Erro na inserção (Indice inválido)");
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
					tables.getRemoveModel()
						.addRow(new Object[] { "remove(" + posUser.getText() + ")", 
								arrayList.remove(Integer.parseInt(posUser.getText())) });
				} catch(Exception ex) {
					showError("Erro na remoção (Indice inválido)");
				} finally {
					removeEntry.dispose();
				}
			}
		});
	}

	// Gerar entrada de inserção
	public Entry insertEntry() {
		posUser = new JTextField();
		posUser.setName("Indice:");
		posUser.setDocument(new Filters.JTextFieldLimit(25));
		posUser.setHorizontalAlignment(JTextField.CENTER);
		Filters.limitNumbers(posUser);

		valueUser = new JTextField();
		valueUser.setName("Elemento:");
		valueUser.setDocument(new Filters.JTextFieldLimit(25));
		valueUser.setHorizontalAlignment(JTextField.CENTER);

		return new Entry(new JComponent[] { posUser, valueUser });
	}

	// Gerar entrada de remoção
	public Entry removeEntry() {
		posUser = new JTextField();
		posUser.setName("Indice:");
		posUser.setDocument(new Filters.JTextFieldLimit(25));
		posUser.setHorizontalAlignment(JTextField.CENTER);
		Filters.limitNumbers(posUser);

		return new Entry(new JComponent[] { posUser });
	}

}
