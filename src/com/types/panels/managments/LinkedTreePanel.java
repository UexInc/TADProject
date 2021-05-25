package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.design.Filters;
import com.types.interfaces.Position;
import com.types.interfaces.PositionList;
import com.types.nodes.TreeNode;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.LinkedTree;
import com.types.tads.NodePositionList;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class LinkedTreePanel extends StandartPanel {

	// ID da janela
	private static final long serialVersionUID = -6010080224840864656L;

	// TAD respectivo
	private LinkedTree<Object> tree = new LinkedTree<Object>();

	// Campos
	private JComboBox<String> whereUser;
	private JTextField valueUser;

	// Construtor
	public LinkedTreePanel() {
		init();
		renderComponents();
	}

	// Renderização padrão
	public void renderComponents() {
		tables = new Tables(4);
		instanceButton();
		remotionButton = null;
		buttonEvents(tree);
		Descriptions.descriptionGenericTree(this);
		generateLateral(true);

		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

		whereUser = new JComboBox<String>(generateList());
		whereUser.setName("Nó raiz:");
		whereUser.setRenderer(listRenderer);
		whereUser.setAutoscrolls(true);
		whereUser.setLightWeightPopupEnabled(true);
		whereUser.setMaximumRowCount(4);

		valueUser = new JTextField();
		valueUser.setName("Nó folha:");
		valueUser.setDocument(new Filters.JTextFieldLimit(25));
		valueUser.setHorizontalAlignment(JTextField.CENTER);
	}

	// Gerar entrada de inserção
	protected Entry insertEntry() {
		whereUser.setModel(new DefaultComboBoxModel<String>(generateList()));
		return new Entry(new JComponent[] { whereUser, valueUser });
	}

	// Adicionado no TAD
	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (whereUser.getSelectedItem() == null) {
						tables.getInsertModel().addRow(new Object[] { "addRoot(" + valueUser.getText() + ")",
								tree.addRoot(valueUser.getText()).element().toString() });
						tree.root().setChildren(new NodePositionList<Position<Object>>());
					} else {
						tables.getInsertModel().addRow(new Object[] { "createLeaf(" + whereUser.getSelectedItem() + ", " + valueUser.getText() + ")",
								createLeaf(getPos(whereUser.getSelectedItem()), valueUser.getText()).element().toString() });
					}
				} catch (Exception ex) {

				} finally {
					insertEntry.dispose();
				}
			}
		});
	}

	protected Entry removeEntry() {
		return null;
	}

	protected void removeEvent() {

	}

	// Pegar posição com base no elemento
	private TreeNode<Object> getPos(Object element) {
		for (Position<Object> child : tree.positions()) {
			if (child.element().toString().equalsIgnoreCase(element.toString()))
				return (TreeNode<Object>) child;
		}
		return null;
	}

	// Criar folha com base na posição
	private TreeNode<Object> createLeaf(TreeNode<Object> p, String n) {
		PositionList<Position<Object>> filhos;
		TreeNode<Object> aux;

		// Obtém os Filhos de p
		filhos = p.getChildren();

		// Cria um novo filho
		aux = new TreeNode<Object>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<Object>>());
		filhos.addLast(aux);
		tree.increaseSize();
		return aux;
	}

	// Gerar a lista de folhas para seleção
	private String[] generateList() {
		String[] list = new String[tree.size()];
		int c = 0;
		for (Position<Object> pos : tree.positions()) {
			list[c] = pos.element().toString();
			c++;
		}
		return list;
	}

}
