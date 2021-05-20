package com.types.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.types.controllers.Options;
import com.types.gui.Main;
import com.types.interfaces.PanelConfig;

public class MenuPanel extends JPanel implements PanelConfig {

	private static final long serialVersionUID = -2293778474706031002L;
	private final GridBagLayout layout = new GridBagLayout();
	private final Frame frame;

	public final static String[] textButtons = { "TAD-Lista Arranjo", "TAD-Pilha", "TAD-Fila", "TAD-Lista de Nodos",
			"TAD-árvore Genérica", "TAD-árvore Binária", "TAD-Fila de Prioridade", "TAD-Mapa", "TAD-Dicionário",
			"TAD-Mapa Ordenado - ABB", "TAD-Mapa Ordenado - AVL", "TAD-Grafos", };
	
	/* Criação do Menu */
	public MenuPanel(Frame frame) {
		this.frame = frame;
		this.config();
		this.putComponents();
		this.frame.add(this);
	}

	/* Menu geral de opções */
	private void putComponents() {
		
		GridBagConstraints cons = this.getConstraint(0, 0, 2);
		cons.insets = new Insets(0, 0, 5 * textButtons.length, 0);
		this.add(this.createTitle(), cons);
		
		for (int i = 0; i < textButtons.length / 2; i++) {
			JButton b = createButton(textButtons[i]);
			this.setAction(i, b, this);
			this.add(b, getConstraint(0, 5 * i + 1, 1));
		}
		
		for (int i = 0; i < textButtons.length / 2; i++) {
			JButton b = createButton(textButtons[textButtons.length / 2 + i]);
			this.setAction(textButtons.length / 2 + i, b, this);
			this.add(b, getConstraint(1, 5 * i + 1, 1));
		}
		
		cons = this.getConstraint(0, 5 * textButtons.length, 1);
		cons.insets = new Insets(5 * textButtons.length, 0, 0, 0);
		this.add(createButton("Sobre"), cons);
		
		cons = this.getConstraint(1, 5 * textButtons.length, 1);
		cons.insets = new Insets(5 * textButtons.length, 0, 0, 0);
		JButton b = createButton("Sair");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Options(null, textButtons.length + 1, Main.getFrames());
			}
		});
		this.add(b, cons);
	}
	
	private JButton createButton(String text) {
		JButton b = new JButton(text);
		b.setFont(new Font("Arial", Font.BOLD, 16));
		b.setPreferredSize(new Dimension(Main.SIZE.width / (textButtons.length / 4), 
				Main.SIZE.height / textButtons.length));
		b.setBackground(new Color(91, 94, 166));
		b.setForeground(new Color(255, 255, 255));
		return b;
	}
	
	
	private JLabel createTitle() {
		JLabel title = new JLabel("Tipos Abstratos de Dados");
		int fontSize = (Main.SIZE.width - Main.SIZE.height) / 10;
		title.setFont(new Font("Arial", Font.BOLD, fontSize));
		title.setForeground(new Color(181, 101, 167));
		return title;
	}

	private GridBagConstraints getConstraint(int x, int y, int width) {
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.VERTICAL;
		cons.gridx = x;
		cons.gridy = y;
		cons.gridwidth = width;
		cons.insets = new Insets(5, 5, 5, 5);
		return cons;
	}
	
	private void setAction(final int index, final JButton button, final JPanel panelTo) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Options(frame, index, panelTo);
			}
		});
	}
	
	public String[] getTextButtons() {
		return textButtons;
	}
	
	public void panelOn() {
		this.setVisible(true);
	}
	
	public void panelOff() {
		this.setVisible(false);
	}
	
	@Override
	public void config() {
		this.setBackground(Color.black);
		this.setLayout(layout);
		this.setVisible(true);
	}
}
