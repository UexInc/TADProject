package com.types.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import com.types.main.Main;
import com.types.panels.managments.AVLTreeMapPanel;
import com.types.panels.managments.ArrayIndexListPanel;
import com.types.panels.managments.ArrayQueuePanel;
import com.types.panels.managments.ArrayStackPanel;
import com.types.panels.managments.BinarySearchTreePanel;
import com.types.panels.managments.HashTableMapPanel;
import com.types.panels.managments.HashTableMultiMapPanel;
import com.types.panels.managments.LinkedBinaryTreePanel;
import com.types.panels.managments.LinkedTreePanel;
import com.types.panels.managments.NodePositionListPanel;
import com.types.panels.managments.SortedListPriorityQueuePanel;

public class Options implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String option = (String)((JComponent) e.getSource()).getName();
		
		switch (option) {
			case "TAD-Lista Arranjo": disableMenu(); Main.mainFrame.add(new ArrayIndexListPanel()); break;
			case "TAD-Pilha": disableMenu(); Main.mainFrame.add(new ArrayStackPanel()); break;
			case "TAD-Fila": disableMenu(); Main.mainFrame.add(new ArrayQueuePanel()); break;
			case "TAD-Lista de Nodos": disableMenu(); Main.mainFrame.add(new NodePositionListPanel()); break;
			case "TAD-�rvore Gen�rica": disableMenu(); Main.mainFrame.add(new LinkedTreePanel()); break;
			case "TAD-�rvore Bin�ria": disableMenu(); Main.mainFrame.add(new LinkedBinaryTreePanel()); break;
			case "TAD-Fila de Prioridade": disableMenu(); Main.mainFrame.add(new SortedListPriorityQueuePanel()); break;
			case "TAD-Mapa": disableMenu(); Main.mainFrame.add(new HashTableMapPanel()); break;
			case "TAD-Dicion�rio": disableMenu(); Main.mainFrame.add(new HashTableMultiMapPanel()); break;
			case "TAD-Mapa Ordenado � ABB": disableMenu(); Main.mainFrame.add(new BinarySearchTreePanel()); break;
			case "TAD-Mapa Ordenado � AVL": disableMenu(); Main.mainFrame.add(new AVLTreeMapPanel()); break;
			case "TAD-Grafos": disableMenu(); Main.mainFrame.add(new HashTableMapPanel()); break;
			case "Sobre": disableMenu(); Main.mainFrame.add(new HashTableMapPanel()); break;
			case "Sair": Main.mainFrame.dispose(); break;
		}
	}
	
	private void disableMenu() {
		Main.menu.setVisible(false);
	}

}
