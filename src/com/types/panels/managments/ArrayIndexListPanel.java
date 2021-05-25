package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.panels.UserEntries;
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

	// Renderiza��o padr�o
	public void renderComponents() {
		tables = new Tables(0);
		instanceButton();
		buttonEvents(arrayList);
		Descriptions.descriptionArrayList(this);
		generateLateral(true);
		
		posUser = UserEntries.createField("Indice:", 20, JTextField.CENTER, Integer.class);
		valueUser = UserEntries.createField("Elemento:", 20, JTextField.CENTER, Object.class);
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
					showError("Erro na inser��o (Indice inv�lido)");
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
					showError("Erro na remo��o (Indice inv�lido)");
				} finally {
					removeEntry.dispose();
				}
			}
		});
	}

	// Gerar entrada de inser��o
	public Entry insertEntry() {
		return new Entry(new JComponent[] { posUser, valueUser });
	}

	// Gerar entrada de remo��o
	public Entry removeEntry() {
		return new Entry(new JComponent[] { posUser });
	}

}
