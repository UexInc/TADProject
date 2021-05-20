package com.types.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.types.panels.MenuPanel;

public class Main extends JFrame {

	// ID da janela
	private static final long serialVersionUID = 7820860139321930055L;

	public static Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public MenuPanel menu;

	// Construtor
	public Main(String title) throws HeadlessException {
		this.render();
		this.setShowFrame();
	}

	private void render() {
		menu = new MenuPanel(this);
	}

	private void setShowFrame() {
		this.setPreferredSize(SIZE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(0, 155, 119));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	// Rodando a janela
	public static void main(String[] args) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e1) {
		}
		new Main("Tipos Abstratos de Dados");
	}
}