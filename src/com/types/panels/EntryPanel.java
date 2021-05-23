package com.types.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.types.controllers.ControlType;
import com.types.controllers.Types;
import com.types.design.Filters;
import com.types.design.Styles;
import com.types.gui.Main;
import com.types.tads.NodePositionList;

@SuppressWarnings("serial")
public class EntryPanel extends JFrame implements ActionListener {

	private JLabel[] labels;
	private JComponent[] fields;

	private JButton cancel;
	private JButton send;

	private JPanel panel;
	private GridBagConstraints cons;
	private int action;

	public EntryPanel(String[] texts, byte option, int action) {
		this.action = action;
		this.cons = new GridBagConstraints();
		this.cons.fill = GridBagConstraints.HORIZONTAL;
		this.cons.weightx = 1;
		this.cons.weighty = 1;
		this.cons.gridy = cons.gridx = 0;
		this.cons.insets = new Insets(0, 10, 0, 10);
		setLayoutManager();
		renderComponents(texts, option);
		setLocationAndSize();
		addComponentsToPanel();
		Styles.setStyleModelPanel(panel);
	}

	// Renderiza os components no painel de interação com o usuário.
	private void renderComponents(String[] texts, byte option) {
		labels = new JLabel[texts.length];
		fields = new JTextField[texts.length];

		if (option == 0 || option == 1 || option == 2) renderSimpleEntry(texts);
		else if (option == 3) renderEntryNodePositionList(texts, action);
		
		cancel = new JButton("Cancelar");
		cancel.addActionListener(this);
		send = new JButton("Confirmar");
		send.addActionListener(this);
	}
	
	private void renderEntryNodePositionList(String[] texts, int action) {
		String[] options = { "No ínicio", 
				"No final", 
				"Antes de (Especificação)", 
				"Depois de (Especificação)" };
		
		@SuppressWarnings("unchecked")
		NodePositionList<Object> nodes = ((NodePositionList<Object>) Types.type);
		String[] nodes_list = new String[nodes.size()];
		int c = 0;
		for (Object node : nodes) {
			nodes_list[c] = node.toString();
			c++;
		}
		
		for (int i = 0; i < texts.length; i++) {
			labels[i] = new JLabel(texts[i].substring(0, texts[i].length() - 2));
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			
			if (texts[i].charAt(texts[i].length() - 1) == 'M') {
				JComboBox<String> combo = new JComboBox<String>(i == 0 && action != 2 ? options : nodes_list);
				DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
			    listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
			    combo.setRenderer(listRenderer);
			    combo.setAutoscrolls(true);
			    combo.setLightWeightPopupEnabled(true);
				cons.gridx = 1;
				panel.add(combo, cons);
				cons.gridy += 1;
			} else {
				fields[i] = new JTextField();
				((JTextField) fields[i]).setDocument(new Filters.JTextFieldLimit(25));
				((JTextField) fields[i]).setHorizontalAlignment(JTextField.CENTER);
				Styles.setStyleField((JTextField) fields[i]);
			}
			
			Styles.setStyleLabel(labels[i]);
			labels[i].setLabelFor(fields[i]);
		}
	}
	
	private void renderSimpleEntry(String[] texts) {
		for (int i = 0; i < texts.length; i++) {
			labels[i] = new JLabel(texts[i].substring(0, texts[i].length() - 2));
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			
			fields[i] = new JTextField();
			((JTextField) fields[i]).setDocument(new Filters.JTextFieldLimit(25));
			((JTextField) fields[i]).setHorizontalAlignment(JTextField.CENTER);
			if (texts[i].charAt(texts[i].length() - 1) == '0')
				Filters.limitNumbers((JTextField) fields[i]);
			
			labels[i].setLabelFor(fields[i]);
			Styles.setStyleLabel(labels[i]);
			Styles.setStyleField((JTextField) fields[i]);
		}
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
		cons.gridx = cons.gridy = 0;
		for (int i = 0; i < labels.length; i++) {
			panel.add(labels[i], cons);
			cons.gridy += 1;
		}
		cons.gridy = 0; 
		cons.gridx = 1;

		for (int i = 0; i < fields.length; i++) {
			if (fields[i] != null) {				
				panel.add(fields[i], cons);
			}
			cons.gridy += 1;
		}
		
		cons.gridx = 0;
		panel.add(cancel, cons);
		cons.gridx = 1;
		panel.add(send, cons);

		add(panel);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			Styles.setButtonsEnable(ManagementPanel.getButtons(), true);
		} else if (e.getSource() == send) {
			Object[] data = new Object[labels.length];
			Component[] components = panel.getComponents();
			
			int i = 0;
			for (Component component : components) {
				if (component instanceof JComboBox<?>) {
					data[i] = ((JComboBox<String>) component).getSelectedItem();
					i++;
				} else if (component instanceof JTextField) {
					data[i] = ((JTextComponent) component).getText();
					i++;
				}
			}
			
			if (action == 1) ControlType.insert(data);
			else if (action == 2) ControlType.remove(data);
			
			Styles.setButtonsEnable(ManagementPanel.getButtons(), true);
		}
		dispose();
	}

}
