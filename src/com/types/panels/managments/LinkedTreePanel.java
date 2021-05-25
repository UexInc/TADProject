package com.types.panels.managments;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.types.interfaces.Position;
import com.types.interfaces.PositionList;
import com.types.nodes.TreeNode;
import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.panels.UserEntries;
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
	private JComboBox<Object> whereUser;
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
		
		whereUser = UserEntries.createComboBox("Nó raiz", generateList());
		valueUser = UserEntries.createField("Nó folha:", 20, JTextField.CENTER, Object.class);
	}

	// Gerar entrada de inserção
	protected Entry insertEntry() {
		whereUser.setModel(new DefaultComboBoxModel<Object>(generateList()));
		return new Entry(new JComponent[] { whereUser, valueUser });
	}

	// Adicionado no TAD
	protected void insertEvent() {
		insertEntry.getSend().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!tree.isEmpty()) {
						tables.getInsertModel().addRow(new Object[] { "createLeaf(" + whereUser.getSelectedItem() + ", " + valueUser.getText() + ")",
								createLeaf(getPos(whereUser.getSelectedItem()), valueUser.getText()).element().toString() });
					} else {
						tables.getInsertModel().addRow(new Object[] { "addRoot(" + valueUser.getText() + ")",
								tree.addRoot(valueUser.getText()).element().toString() });
						tree.root().setChildren(new NodePositionList<Position<Object>>());
					}
				} catch (Exception ex) {

				} finally {
					insertEntry.dispose();
				}
			}
		});
	}

	protected Entry removeEntry() { return null; }
	protected void removeEvent() { }

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
