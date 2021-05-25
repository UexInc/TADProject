package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.design.Filters;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.ArrayQueue;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class ArrayQueuePanel extends StandartPanel {

	// ID na janela
	private static final long serialVersionUID = 7000372001620939944L;

	// TAD respectivo
	private ArrayQueue<Object> queue = new ArrayQueue<Object>();

	// Campos
	private JTextField valueUser;
	
	// Construtor
	public ArrayQueuePanel() {
		init();
		renderComponents();
	}

	// Renderização padrão
	public void renderComponents() {
		tables = new Tables(2);
		instanceButton();
		buttonEvents(queue);
		Descriptions.descriptionQueue(this);
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
					queue.enqueue(valueUser.getText());
					tables.getInsertModel().addRow(new Object[] { "enqueue(" + valueUser.getText() + ")", null });
				} catch (Exception ex) {

				} finally {
					insertEntry.dispose();
				}
			}
		});
	}

	protected void removeEvent() {
		try {
			tables.getRemoveModel().addRow(new Object[] { "dequeue()", queue.dequeue() });
		} catch (Exception ex) {
			showError("Erro na remoção (Fila vazia)");
		}
	}

	protected Entry removeEntry() {
		return null;
	}
}
