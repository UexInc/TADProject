package com.types.tads;

import java.util.Iterator;

import com.types.exceptions.BoundaryViolationException;
import com.types.exceptions.EmptyTreeException;
import com.types.exceptions.InvalidPositionException;
import com.types.exceptions.NonEmptyTreeException;
import com.types.interfaces.Position;
import com.types.interfaces.PositionList;
import com.types.interfaces.Tree;
import com.types.interfaces.TreePosition;
import com.types.nodes.TreeNode;

// Um classe para a �rvore ligada onde os nodos t�m um n�mero arbitr�rio de filhos.
public class LinkedTree<T> implements Tree<T> {

	protected TreePosition<T> root; // Refer�ncia para a ra�z
	protected int size; // N�mero de Nodos

	// Cria uma �rvore vazia
	public LinkedTree() {
		root = null; // Inicia uma �rvore vazia
		size = 0;
	}

	// Retorna um n�mero de nodos da �rvore
	public int size() {
		return size;
	}

	// Retorna se a �rvore est� vazia
	public boolean isEmpty() {
		return (size == 0);
	}

	// Retorna se um nodo � interno
	public boolean isInternal(Position<T> v) throws InvalidPositionException {
		return !isExternal(v);
	}

	// Retorna se um nodo � externo
	public boolean isExternal(Position<T> v) throws InvalidPositionException {

		TreePosition<T> vv = checkPosition(v);
		return (vv.getChildren() == null) || vv.getChildren().isEmpty();
	}

	// Retorna se um nodo � a ra�z
	public boolean isRoot(Position<T> v) throws InvalidPositionException {
		checkPosition(v);
		return (v == root());
	}

	// Retorna a ra�z da �rvore
	public TreePosition<T> root() throws EmptyTreeException {
		if (root == null)
			throw new EmptyTreeException("The tree is empty");
		return root;
	}

	// Retorna o pai de um nodo
	public TreePosition<T> parent(Position<T> v) throws InvalidPositionException, BoundaryViolationException {
		TreePosition<T> vv = checkPosition(v);
		TreePosition<T> parentPos = vv.getParent();
		if (parentPos == null)
			throw new BoundaryViolationException("No parent");
		return parentPos;
	}

	// Retorna uma cole��o iter�vel dos filhos de um nodo
	public Iterable<Position<T>> children(Position<T> v) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		return vv.getChildren();
	}

	// Retorna uma cole��o iter�vel dos nodos da �rvore.
	public Iterable<Position<T>> positions() {
		PositionList<Position<T>> positions = new NodePositionList<Position<T>>();
		if (size != 0)
			preorderPositions(root(), positions);
		return positions;
	}

	// Retorna um iterator dos elementos armazenados nos nodos
	public Iterator<T> iterator() {
		Iterable<Position<T>> positions = positions();
		PositionList<T> elements = new NodePositionList<T>();
		for (Position<T> pos : positions)
			elements.addLast(pos.element());
		return elements.iterator();
	}

	// Troca o elemento de um nodo
	public T replace(Position<T> v, T o) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		T temp = v.element();
		vv.setElement(o);
		return temp;
	}

	// M�todos de atualiza��o adicionais
	// Adiciona um nodo ra�z para uma �rvore vazia
	public TreePosition<T> addRoot(T e) throws NonEmptyTreeException {
		if (!isEmpty())
			throw new NonEmptyTreeException("Tree already has a root");
		size = 1;
		root = createNode(e, null, null);
		return root;
	}

	// Troca os elementos de dos nodos
	public void swapElements(Position<T> v, Position<T> w) throws InvalidPositionException {
		TreePosition<T> vv = checkPosition(v);
		TreePosition<T> ww = checkPosition(w);
		T temp = w.element();
		ww.setElement(v.element());
		vv.setElement(temp);
	}

	// M�todos auxiliares
	// Se v � um bom nodo da �rvore, cast para TreePosition, caso contr�rio, lan�a
	// exce��o
	protected TreePosition<T> checkPosition(Position<T> v) throws InvalidPositionException {
		if (v == null || !(v instanceof TreePosition))
			throw new InvalidPositionException("The position is invalid");
		return (TreePosition<T>) v;
	}

	// Cria um novo nodo da �rvore
	protected TreePosition<T> createNode(T element, TreePosition<T> parent, PositionList<Position<T>> children) {
		return new TreeNode<T>(element, parent, children);
	}

	// Cria uma lista armazenando os nodos das sub�rvore de um nodo
	// ordenado de acordo com a travessia das sub�rvores
	protected void preorderPositions(Position<T> v, PositionList<Position<T>> pos) throws InvalidPositionException {
		pos.addLast(v);
		if (children(v) != null)
			for (Position<T> w : children(v))
				preorderPositions(w, pos);
	}

	public String toString() {
		return toString(this);
	}

	public <E> String toString(LinkedTree<E> T) {
//		String s = "";
//		for (E i : T) {
//			s += ", " + i;
//		}
//		s = (s.length() == 0 ? s : s.substring(2));
//		return "[" + s + "]";
		return this.parentheticRepresentation(this, root);
	}

	// depth
	public int depth(Tree<T> T, Position<T> v) {
		if (T.isRoot(v))
			return 0;
		else {
			Position<T> w = ((TreeNode<T>) v).getParent();
			return 1 + depth(T, w);
		}
	}

	// height1

	public int Max(int x, int y) {
		return x > y ? x : y;
	}

	public int height1(Tree<T> T) {
		int h = 0;
		for (Position<T> v : positions()) {
			if (isExternal(v))
				h = Max(h, depth(T, v));
		}
		return h;
	}

	// height2
	public int height2(Tree<T> T, Position<T> v) {
		if (isExternal(v))
			return 0;
		else {
			int h = 0;
			for (Position<T> w : children(v))
				h = Max(h, height2(T, w));
			return 1 + h;
		}
	}

	// parentheticRepresentation
	public String parentheticRepresentation(Tree<T> T, Position<T> v) {
		if (v == null)
			return "()";
		String s = v.element().toString();
		String tabs = "";
		int contTabs = depth(T, v);
		if (T.isInternal(v)) {
			Boolean firstTime = true;
			for (Position<T> w : T.children(v)) {
				if (firstTime) {
					for (int i = 0; i <= contTabs; i++) {
						tabs += "\t";
					}
					s += " (\n" + tabs + parentheticRepresentation(T, w);
					firstTime = false;
				} else {
					s += ",\n" + tabs + parentheticRepresentation(T, w);
				}
			}
			if (!firstTime)
				s += "\n" + tabs.substring(0, tabs.length() - 1) + ")";
		}
		return s;
	}

	public void increaseSize() {
		this.size += 1;
	}
}