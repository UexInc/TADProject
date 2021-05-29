package com.types.util;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.types.main.Layout;

public final class Descriptions {

	private static JTextPane desc;

	private static Layout layout = new Layout();
	
	public static void descriptionArrayList(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Lista Arranjo</h1>",
			panel
		);
	}
	
	public static void descriptionStack(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Pilha</h1>",
			panel
		);
	}
	
	public static void descriptionQueue(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Fila</h1>",
			panel
		);
	}
	
	public static void descriptionNodePositionList(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Lista de Nodos</h1>",
			panel
		);
	}
	
	public static void descriptionGenericTree(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Arvore Generica</h1>", 
			panel
		);
	}
	

	public static void descriptionBinaryTree(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Arvore Binaria</h1>", 
			panel
		);
	}
	

	public static void descriptionPriorityQueue(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Fila de Prioridade</h1>", 
			panel
		);
	}
	

	public static void descriptionMap(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Mapa</h1>", 
			panel
		);
	}
	
	public static void descriptionDictionary(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Dicionário</h1>", 
			panel
		);
	}
	
	public static void descriptionABB(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Mapa Ordenado – ABB</h1>", 
			panel
		);
	}
	

	public static void descriptionAVL(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Mapa Ordenado – AVL</h1>", 
			panel
		);
	}
	
	private static void setDescription(String content, JPanel panel) {
		layout.setConstraints(0, 0, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		layout.weightx = 3;
		desc = new JTextPane();
		desc.setContentType("text/html");
		desc.setEditable(false);
		desc.setBackground(new Color(244, 230, 255));
		desc.setText(content);
		JScrollPane scroll = new JScrollPane(desc);
		scroll.setBorder(null);
		panel.add(scroll, layout);
	}
	
}
