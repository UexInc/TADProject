package com.types.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.types.controllers.Options;
import com.types.design.Styles;
import com.types.interfaces.IRender;
import com.types.main.Layout;
import com.types.main.Main;

public class Menu extends JPanel implements IRender {

	// ID da janela
	private static final long serialVersionUID = -2293778474706031002L;
	
	// Layout para renderiza��o
	public Layout layout = new Layout();
	
	// Texto das op��es
	public static final String[] texts = {
		"TAD-Lista Arranjo",
		"TAD-Pilha",
		"TAD-Fila",
		"TAD-Lista de Nodos",
		"TAD-�rvore Gen�rica",
		"TAD-�rvore Bin�ria",
		"TAD-Fila de Prioridade",
		"TAD-Mapa",
		"TAD-Dicion�rio",
		"TAD-Mapa Ordenado � ABB",
		"TAD-Mapa Ordenado � AVL",
		"TAD-Grafos"
	};
	
	// Construtor
	public Menu() {
		init();
		renderComponents();
	}
	
	// Renderiza��o padr�o
	public void renderComponents() {
		createTitle();
		generateOptions();
	}
	
	// Inicializando janela
	public void init() {
		setBackground(Color.orange);
		setLayout(new GridBagLayout());
		setVisible(true);
	}
	
	// Titulo do menu
	private void createTitle() {
		JLabel title = new JLabel("Tipos Abstratos de Dados");
		int fontSize = (Main.SIZE.width - Main.SIZE.height) / 10;
		title.setFont(new Font("Arial", Font.BOLD, fontSize));
		title.setForeground(new Color(181, 101, 167));
		layout.setConstraints(0, 0, 2, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		layout.insets = new Insets(0, 0, 5 * texts.length, 0);
		add(title, layout);
	}
	
	// Gera��o dos bot�es
	private void generateOptions() {
		
		// Op��es dos TADs
		for (int i = 0; i < texts.length / 2; i++) {			
			JButton button = new JButton(texts[i]);
			button.setName(String.valueOf(i));
			button.addActionListener(new Options());
			Styles.setButtonMenu(button);
			layout.setConstraints(0, 5 * i + 1, 1, 1, 
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
			layout.insets = new Insets(10, 10, 10, 10);
			add(button, layout);
		}

		for (int i = 0; i < texts.length / 2; i++) {			
			JButton button = new JButton(texts[texts.length / 2 + i]);
			button.setName(texts[texts.length / 2 + i]);
			Styles.setButtonMenu(button);
			layout.setConstraints(1, 5 * i + 1, 1, 1,
					GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
			layout.insets = new Insets(10, 10, 10, 10);
			add(button, layout);
		}
		
		// Op��o sobre o projeto
		JButton button = new JButton("Sobre");
		button.setName(button.getText());
		layout.setConstraints(0, 5 * texts.length, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		Styles.setButtonMenu(button);
		layout.insets = new Insets(5 * texts.length, 0, 0, 0);
		add(button, layout);
		
		// Op��o de sair
		button = new JButton("Sair");
		button.setName(String.valueOf(texts.length + 2));
		layout.setConstraints(1, 5 * texts.length, 1, 1, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
		Styles.setButtonMenu(button);
		layout.insets = new Insets(5 * texts.length, 0, 0, 0);
		button.addActionListener(new Options());
		add(button, layout);
	}
	
}
