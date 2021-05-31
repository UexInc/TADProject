package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.panels.UserEntries;
import com.types.tads.ArrayQueue;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class ArrayQueuePanel extends StandartPanel {

	// ID na janela
	private static final long serialVersionUID = 7000372001620939944L;

	// TAD respectivo
	private ArrayQueue<Object> queue;

	// Campos
	private JTextField valueUser;
	
	// Construtor
	public ArrayQueuePanel() {
		init();
		renderComponents();
	}

	// Renderiza��o padr�o
	public void renderComponents() {
		queue = new ArrayQueue<Object>();
		tables = new Tables(2);
		instanceButton();
		buttonEvents(queue);
		Descriptions.descriptionQueue(this);
		generateLateral(true);
		
		valueUser = UserEntries.createField("Adicionar:", 20, JTextField.CENTER, Object.class);
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
					queue.enqueue(valueUser.getText());
					tables.getInsertModel().addRow(new Object[] { "enqueue(" + valueUser.getText() + ")", null });
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
			tables.getRemoveModel().addRow(new Object[] { "dequeue()", queue.dequeue() });
		} catch (Exception ex) {
			showError("Erro na remo��o (Fila vazia)");
		}
	}

	protected Entry removeEntry() {
		return null;
	}
}
