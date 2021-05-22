package com.types.nodes;

import com.types.exceptions.InvalidPositionException;
import com.types.interfaces.Position;

public class DNode<T> implements Position<T> {

	private DNode<T> prev, next; // Refer�ncia para os nodos anterior e posterior
	private T element; // Elemento armazenado nesta posi��o

	// Construtor
	public DNode(DNode<T> newPrev, DNode<T> newNext, T elem) {
		prev = newPrev;
		next = newNext;
		element = elem;
	}

	// M�todo da interface Position
	public T element() throws InvalidPositionException {
		if ((prev == null) && (next == null))
			throw new InvalidPositionException("Position is not in a list!");
		return element;
	}

	// M�todos de acesso
	public DNode<T> getNext() {
		return next;
	}
	public DNode<T> getPrev() {
		return prev;
	}

	// M�todos de atualiza��o
	public void setNext(DNode<T> newNext) {
		next = newNext;
	}
	public void setPrev(DNode<T> newPrev) {
		prev = newPrev;
	}
	public void setElement(T newElement) {
		element = newElement;
	}

}
