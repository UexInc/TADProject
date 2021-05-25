package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.design.Filters;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.ArrayStack;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class ArrayStackPanel extends StandartPanel {

	// ID da janela
	private static final long serialVersionUID = -189390063017820972L;

	// TAD respectivo
	private ArrayStack<Object> stack = new ArrayStack<Object>();

	// Campos
	private JTextField valueUser;

	// Contrutor
	public ArrayStackPanel() {
		init();
		renderComponents();
	}

	// Renderização padrão
	public void renderComponents() {
		tables = new Tables(1);
		instanceButton();
		buttonEvents(stack);
		Descriptions.descriptionStack(this);
		generateLateral(true);
	}

	protected Entry insertEntry() {
		valueUser = new JTextField();
		valueUser.setName("Elemento:");
		valueUser.setDocument(new Filters.JTextFieldLimit(25));
		valueUser.setHorizontalAlignment(JTextField.CENTER);

		return new Entry(new JComponent[] { valueUser });
	}

	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stack.push(valueUser.getText());
					tables.getInsertModel().addRow(
							new Object[] { "push(" + valueUser.getText()  + ")", null });
				} catch (Exception ex) {
					
				} finally {
					insertEntry.dispose();
				}
			}
		});
	}


	protected void removeEvent() {
		try {
			tables.getRemoveModel().addRow(new Object[] { "pop()", stack.pop() });
		} catch (Exception ex) {
			showError("Erro na remoção (Pilha vazia)");
		}
	}

	protected Entry removeEntry() { return null; }
}
