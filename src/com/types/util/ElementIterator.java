package com.types.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.types.interfaces.Position;
import com.types.interfaces.PositionList;

/* Uma classe iterator Simples para listas. Os elementos da clise s�o retornados por
* este iterator. Nenhuma c�pia da lista � realizada, de forma que qualquer mudan�a
* na lista � refletida por este iterator. */
public class ElementIterator<T> implements Iterator<T> {

	protected PositionList<T> list;
	protected Position<T> cursor;

	// Cria um elemento iterator
	public ElementIterator(PositionList<T> L) {
		list = L;
		cursor = (list.isEmpty()) ? null : list.first();
	}

	// Retorna se o iterator tem ou n�o um pr�ximo objeto.
	public boolean hasNext() {
		return (cursor != null);
	}

	// Retorna o pr�ximo objeto do iterator.
	public T next() throws NoSuchElementException {
		if (cursor == null)
			throw new NoSuchElementException("No next element");
		T toReturn = cursor.element();
		cursor = (cursor == list.last()) ? null : list.next(cursor);
		return toReturn;
	}

	// Dispara um {@link UnsupportedOperationException} para todos os casos, porque
	// a remo��o n�o � uma opera��o suportada por este iterator.
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("remove");
	}

}