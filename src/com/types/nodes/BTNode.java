package com.types.nodes;

import com.types.interfaces.BTPosition;

// Classe que implementa um nodo de �rvore bin�ria armazenando refer�ncias
// para um elemento, o nodo pai, o nodo da direita e o nodo da esquerda.
public class BTNode<T> implements BTPosition<T> {

	private T element; // elemento armazenado neste nodo
	private BTPosition<T> left, right, parent; // nodos adjacentes

	// Construtor principal
	public BTNode(T element, BTPosition<T> parent, BTPosition<T> left, BTPosition<T> right) {
		setElement(element);
		setParent(parent);
		setLeft(left);
		setRight(right);
	}

	public BTNode() { }

	// Retorna o elemento armazenado nesta posi��o
	public T element() {
		return element;
	}

	// Define o elemento armazenado nesta posi��o
	public void setElement(T o) {
		element = o;
	}

	// Retorna o filho da esquerda desta posi��o
	public BTPosition<T> getLeft() {
		return left;
	}

	// Define o filho da esquerda desta posi��o
	public void setLeft(BTPosition<T> v) {
		left = v;
	}

	// Retorna o filho da direita desta posi��o
	public BTPosition<T> getRight() {
		return right;
	}

	// Define o filho da direita desta posi��o
	public void setRight(BTPosition<T> v) {
		right = v;
	}

	// Retorna o pai desta posi��o
	public BTPosition<T> getParent() {
		return parent;
	}

	// Define o pai desta posi��o
	public void setParent(BTPosition<T> v) {
		parent = v;
	}
}
