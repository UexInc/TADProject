package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.panels.UserEntries;
import com.types.tads.ArrayStack;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class ArrayStackPanel extends StandartPanel {

	// ID da janela
	private static final long serialVersionUID = -189390063017820972L;

	// TAD respectivo
	private ArrayStack<Object> stack;

	// Campos
	private JTextField valueUser;

	// Contrutor
	public ArrayStackPanel() {
		init();
		renderComponents();
	}

	// Renderiza��o padr�o
	public void renderComponents() {
		stack = new ArrayStack<Object>();
		tables = new Tables(1);
		instanceButton();
		buttonEvents(stack);
		Descriptions.descriptionStack(this);
		generateLateral(true);
		
		valueUser = UserEntries.createField("Empilhar:", 20, JTextField.CENTER, Object.class);
	}

	// Gerar entrada de inser��o
	protected Entry insertEntry() {
		return new Entry(new JComponent[] { valueUser });
	}

	// Adicionado no TAD
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

	// Removendo no TAD
	protected void removeEvent() {
		try {
			tables.getRemoveModel().addRow(new Object[] { "pop()", stack.pop() });
		} catch (Exception ex) {
			showError("Erro na remo��o (Pilha vazia)");
		}
	}

	// Gerar entrada de remo��o
	protected Entry removeEntry() { return null; }
}
