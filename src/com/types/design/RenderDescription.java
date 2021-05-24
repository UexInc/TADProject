package com.types.design;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.types.panels.ManagementPanel;

public class RenderDescription {

	private JTextPane desc;
	
	private ManagementPanel m;
	private LayoutPanel l;
	
	public RenderDescription(ManagementPanel m, LayoutPanel l) {
		this.m = m;
		this.l = l;
	}
	
	public void renderDescription(int option) {
		switch (option) {
			case 0: this.descriptionArrayList(); break;
			case 1: this.descriptionStack(); break;
			case 2: this.descriptionQueue(); break;
			case 3: this.descriptionNodePositionList();
			case 4: this.descriptionGenericTree();
		}
	}
	
	private void descriptionArrayList() {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Lista Arranjo</h1>"
		);
	}
	
	private void descriptionStack() {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Pilha</h1>"
		);
	}
	
	private void descriptionQueue() {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Fila</h1>"
		);
	}
	
	private void descriptionNodePositionList() {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Lista de Nodos</h1>"
		);
	}
	
	private void descriptionGenericTree() {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Arvore Generica</h1>"
		);
	}
	
	private void setDescription(String content) {
		l.setConstraint(GridBagConstraints.BOTH, GridBagConstraints.CENTER, 0, 0, 3, 0);
		desc = new JTextPane();
		desc.setContentType("text/html");
		desc.setEditable(false);
		desc.setBackground(new Color(244, 230, 255));
		desc.setText(content);
		JScrollPane scroll = new JScrollPane(desc);
		scroll.setBorder(null);
		m.add(scroll, l.cons);
	}
	
}
