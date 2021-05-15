package com.types.interfaces;

public interface IndexList<T> {
	// Retorna o número de elementos na lista
	public int size();
	// Retorna se a lista está vazia
	public boolean isEmpty();
	// Insere um elemento, e, no índice, i, deslocando todos os elmentos depois dele
	public void add(int i, T e) throws IndexOutOfBoundsException;
	// Retorna um elemento no índice i, sem removê-lo
	public T get(int i) throws IndexOutOfBoundsException;
	// Remove e retorna o elemento do índice i, deslocando os elementos depois dele
	public T remove(int i) throws IndexOutOfBoundsException;
	// Troca o elemento de i com e, retornando o elemento que estava em i
	public T set(int i, T e) throws IndexOutOfBoundsException;
}