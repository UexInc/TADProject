package com.types.interfaces;

import java.util.Iterator;

import com.types.exceptions.BoundaryViolationException;
import com.types.exceptions.InvalidPositionException;

public interface PositionList<T> extends Iterable<T> {
	// Retorna o n�mero de elementos desta lista.
	public int size();

	// Retorna quando a lista est� vazia.
	public boolean isEmpty();

	// Retorna o primeiro nodo da lista.
	public Position<T> first();

	// Retorna o �ltimo nodo da lista.
	public Position<T> last();

	// Retorna o nodo que segue um dado nodo da lista.
	public Position<T> next(Position<T> p) throws InvalidPositionException, BoundaryViolationException;

	// Retorna o nodo que antecede um dado nodo da lista.
	public Position<T> prev(Position<T> p) throws InvalidPositionException, BoundaryViolationException;

	// Insere um elemento no in�cio da lista, retornando uma posi��o nova.
	public void addFirst(T e);

	// Insere um elemento na �ltima posi��o, retornando uma posi��o nova.
	public void addLast(T e);

	// Insere um elemento ap�s um dado elemento da lista.
	public void addAfter(Position<T> p, T e) throws InvalidPositionException;

	// Insere um elemento antes de um dado elemento da lista.
	public void addBefore(Position<T> p, T e) throws InvalidPositionException;

	// Remove um nodo da lista, retornando o elemento l� armazenado.
	public T remove(Position<T> p) throws InvalidPositionException;

	// Substitui o elemento armazenado em um determinado nodo, retornando o elemento que estava l� armazenado.
	public T set(Position<T> p, T e) throws InvalidPositionException;

	// Retorna um iterador sobre todos os elementos da lista.
	public Iterator<T> iterator();

}