package com.types.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.types.controllers.ControlType;
import com.types.controllers.Types;
import com.types.design.Filters;
import com.types.design.Styles;
import com.types.gui.Main;

@SuppressWarnings("serial")
public class EntryPanel extends JFrame implements ActionListener {

	private JLabel[] labels;
	private JTextField[] fields;

	private JButton cancel;
	private JButton send;

	private JPanel panel;
	private int action;

	public EntryPanel(String[] texts, int action) {
		this.action = action;
		renderComponents(texts);
		setLocationAndSize();
		setLayoutManager();
		addComponentsToPanel();
		Styles.setStyleModelPanel(panel);
	}

	private void renderComponents(String[] texts) {
		labels = new JLabel[texts.length];
		fields = new JTextField[texts.length];

		for (int i = 0; i < texts.length; i++) {
			labels[i] = new JLabel(texts[i]);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			fields[i] = new JTextField();
			fields[i].setDocument(new Filters.JTextFieldLimit(25));
			fields[i].setHorizontalAlignment(JTextField.CENTER);
			if (Types.stripAccents(texts[i].toLowerCase()).contains("Índice"))
				Filters.limitNumbers(fields[i]);
			labels[i].setLabelFor(fields[i]);
			Styles.setStyleLabel(labels[i]);
			Styles.setStyleField(fields[i]);
		}

		cancel = new JButton("Cancelar");
		cancel.addActionListener(this);
		send = new JButton("Confirmar");
		send.addActionListener(this);
	}

	private void setLayoutManager() {
		panel = new JPanel(new GridBagLayout());
	}

	private void setLocationAndSize() {
		setFrame();
	}

	private void setFrame() {
		setPreferredSize(new Dimension(Main.SIZE.width / 3, Main.SIZE.height / 3));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);
		getContentPane().setBackground(new Color(0, 155, 119));
		requestFocus();
		setAlwaysOnTop(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void addComponentsToPanel() {
		GridBagConstraints cons = new GridBagConstraints();

		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.weighty = 1;
		cons.insets = new Insets(0, 10, 0, 10);

		for (int i = 0; i < labels.length; i++) {
			panel.add(labels[i], cons);
			cons.gridx += 1;
		}

		if (labels.length == 1) {
			cons.gridx += 1;
		}

		for (int i = 0; i < fields.length; i++) {
			cons.gridy += 1;
			panel.add(fields[i], cons);
		}

		cons.gridx = 0;
		cons.gridy += 1;
		panel.add(cancel, cons);
		cons.gridx += 1;
		panel.add(send, cons);

		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			Styles.setButtonsEnable(ManagementPanel.getButtons(), true);
		} else if (e.getSource() == send) {
			Object[] data = new Object[labels.length];
			for (int i = 0; i < labels.length; i++) {
				data[i] = fields[i].getText();
			}
			if (action == 1) {
				ControlType.insert(data);
			} else if (action == 2) {
				ControlType.remove(data);
			}
			Styles.setButtonsEnable(ManagementPanel.getButtons(), true);
		}
		dispose();
	}

}
