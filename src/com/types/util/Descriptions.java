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
			"<style> h1 { text-align: center; } </style><h1>TAD - Dicionario</h1>", 
			panel
		);
	}
	
	public static void descriptionABB(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Mapa Ordenado - ABB</h1>", 
			panel
		);
	}
	

	public static void descriptionAVL(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Mapa Ordenado - AVL</h1>", 
			panel
		);
	}
	
	public static void descriptionGraph(JPanel panel) {
		setDescription(
			"<style> h1 { text-align: center; } </style><h1>TAD - Grafo</h1>", 
			panel
		);
	}
	
	public static void descriptionAbout(JPanel panel) {
		setDescription(
			"<!DOCTYPE html>\r\n" + 
			"<html lang=\"pt-br\">\r\n" + 
			"\r\n" + 
			"<head>\r\n" + 
			"    <meta charset=\"UTF-8\">\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
			"    <title>Sobre</title>\r\n" + 
			"    <style type=\"text/css\">\r\n" + 
			"        h1 {\r\n" + 
			"            font-size: xx-large;\r\n" + 
			"            text-align: center;\r\n" + 
			"        }\r\n" + 
			"        h2, p {\r\n" + 
			"             font-size: x-large;   \r\n" + 
			"        }\r\n" + 
			"        #authors {\r\n" + 
			"            position: fixed;\r\n" + 
			"            bottom: 0px;\r\n" + 
			"            right: 0px;\r\n" + 
			"        }\r\n" + 
			"    </style>\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body>\r\n" + 
			"    <h1>Sobre</h1>\r\n" + 
			"    <p>Esta aplicação tem o objetivo de mostrar os\r\n" + 
			"        <strong>Tipos Abstratos de Dados (TAD)</strong>\r\n" + 
			"        mais comuns e demonstrar algumas de suas operações básicas como:\r\n" + 
			"        <strong>Inserção, Remoção e Visualização.</strong>\r\n" + 
			"    </p>\r\n" + 
			"    <br>\r\n" + 
			"    <section id=\"authors\">\r\n" + 
			"        <h2>Equipe:</h2>\r\n" + 
			"        <p>- Gabriel de Melo Marcondes (RA: 1903416)</p>\r\n" + 
			"        <p>- Raphael Vinícius Oliveira da Silva (RA: 1902604)</p>\r\n" + 
			"        <p>- Geisa Pereira Souza (RA: 1903417)</p>\r\n" + 
			"        <p>- Willian Donha dos Santos Pestana (RA: 1902650)</p>\r\n" + 
			"        <p>- Ricardo de Oliveira Trovato (RA: 1903223)</p>\r\n" + 
			"    </section>\r\n" + 
			"</body>\r\n" + 
			"\r\n" + 
			"</html>\r\n" + 
			"", 
			panel
		);
	}
	
	private static void setDescription(String content, JPanel panel) {
		layout.setConstraints(0, 0, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		layout.weightx = 3;
		layout.weighty = 1;
		desc = new JTextPane();
		desc.setEditable(false);
		desc.setBackground(new Color(244, 230, 255));
		desc.setContentType("text/html");
		desc.setText(content);
		JScrollPane scroll = new JScrollPane(desc);
		scroll.setBorder(null);
		scroll.setPreferredSize(desc.getPreferredSize());
		panel.add(scroll, layout);
	}
	
}
