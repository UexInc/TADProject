package com.types.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.types.controllers.ControlType;
import com.types.controllers.Types;
import com.types.design.LayoutPanel;
import com.types.design.RenderDescription;
import com.types.design.Styles;
import com.types.gui.Tables;
import com.types.interfaces.PanelConfig;

public class ManagementPanel extends JPanel implements PanelConfig, ActionListener {

	private static final long serialVersionUID = -257510445160788291L;

	private LayoutPanel layout;

	private static JButton insertButton;
	private static JButton remotionButton;
	private static JButton viewButton;
	private static JButton backButton;
	private static JButton[] buttons;

	private Tables tables;
	private RenderDescription descriptions;
	private ControlType control;

	private final int option;
	private final MenuPanel menu;

	public ManagementPanel(MenuPanel menu, final int option) {
		new Types(option);
		
		this.option = option;
		this.menu = menu;
		
		this.layout = new LayoutPanel();
		this.config();
		
		this.tables = new Tables(option);
		this.descriptions = new RenderDescription(this, layout);
		this.control = new ControlType(new DefaultTableModel[] { 
				tables.getInsertModel(), tables.getRemoveModel(), tables.getViewModel() 
		}, option);
		
		insertButton = new JButton("Inserir");
		remotionButton = new JButton("Remover");
		viewButton = new JButton("Visualizar");
		backButton = new JButton("Voltar");
		buttons = new JButton[] { insertButton, remotionButton, viewButton, backButton };
		
		this.buttonEvents();
		
		selectedPanel(this.option);
	}

	private void selectedPanel(int option) {
		if (option < MenuPanel.textButtons.length) {
			descriptions.renderDescription(option);
			lateralButtons();
		} else {
			setVisible(false);
			menu.panelOn();
		}
	}

	private void buttonEvents() {
		insertButton.addActionListener(this);
		remotionButton.addActionListener(this);
		viewButton.addActionListener(this);
		backButton.addActionListener(this);
	}
	
	private void lateralButtons() {
		JPanel lateral = new JPanel(layout);
		if (option != MenuPanel.textButtons.length - 1) {
			createLateralComponent(insertButton, lateral);
			createLateralComponent(tables.getInsertTable(), lateral);
			createLateralComponent(remotionButton, lateral);
			createLateralComponent(tables.getRemoveTable(), lateral);
			createLateralComponent(viewButton, lateral);
			createLateralComponent(tables.getViewTable(), lateral);
		}
		createLateralComponent(backButton, lateral);
		layout.setConstraint(GridBagConstraints.BOTH, GridBagConstraints.EAST, 1, 0, 1, 1);
		add(lateral, layout.cons);
	}

	private void createLateralComponent(Component c, JPanel lateral) {
		layout.setConstraint(GridBagConstraints.BOTH, GridBagConstraints.CENTER, 
				GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, 1, 0);
		layout.cons.gridwidth = GridBagConstraints.REMAINDER;
		if (c instanceof JButton) {
			Styles.setStyleButton((JButton) c);
			lateral.add(c, layout.cons);
		} else if (c instanceof JTable) {
			layout.cons.weighty = 1;
			JScrollPane scroll = new JScrollPane(c);
			Styles.setStyleTable((JTable) c);
			lateral.add(scroll, layout.cons);
		}
	}

	public void config() {
		this.setBackground(Color.orange);
		this.setLayout(layout);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == insertButton) {
			Styles.setButtonsEnable(buttons, false);
			control.renderBy(1);
		} else if (e.getSource() == remotionButton) {
			Styles.setButtonsEnable(buttons, false);
			control.renderBy(2);
		} else if (e.getSource() == viewButton)
			ControlType.view();
		else if (e.getSource() == backButton) {
			setVisible(false);
			menu.setVisible(true);
		}
	}

	public static JButton[] getButtons() {
		return buttons;
	}
	
}
