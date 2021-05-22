package com.types.tads;

import com.types.interfaces.IndexList;

public class ArrayIndexList<T> implements IndexList<T> {

	private T[] A; // arranjo que armazena os elementos da lista

	private int capacity = 16; // tamanho inicial do arranjo A

	private int size = 0; // n�mero de elementos armazenados na lista

	// Cria a lista indexada com capacidade de 16 elementos
	@SuppressWarnings("unchecked")
	public ArrayIndexList() {
		A = (T[]) new Object[capacity]; // o comiplador pode gerar alerta aqui, mas est� tudo ok.
	}

	// Retorna o n�mero de elementos da lista
	public int size() {
		return size;
	}

	// Retorna se a lista est� vazia
	public boolean isEmpty() {
		return size() == 0;
	}

	// Retorna o elemento armazenado num dado �ndice
	public T get(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		return A[r];
	}

	// Troca o elemento armazenado no �ndice
	public T set(int r, T e) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		T temp = A[r];
		A[r] = e;
		return temp;
	}

	// Insere um elemento num dado �ndice
	public void add(int r, T e) throws IndexOutOfBoundsException {
		checkIndex(r, size() + 1);
		if (size == capacity) { // an overflow
			capacity *= 2;
			@SuppressWarnings("unchecked")
			T[] B = (T[]) new Object[capacity];
			for (int i = 0; i < size; i++)
				B[i] = A[i];
			A = B;
		}
		for (int i = size - 1; i >= r; i--) // desloca os elementos para cima
			A[i + 1] = A[i];
		A[r] = e;
		size++;
	}

	// Remove o elemento armazenado num dado �ndice
	public T remove(int r) throws IndexOutOfBoundsException {
		checkIndex(r, size());
		T temp = A[r];
		for (int i = r; i < size - 1; i++) // desloca os elemento para baixo
			A[i] = A[i + 1];
		size--;
		return temp;
	}

	// Verifica se o �ndice pertence ao intervalo [0, n - 1]
	protected void checkIndex(int r, int n) throws IndexOutOfBoundsException {
		if (r < 0 || r >= n)
			throw new IndexOutOfBoundsException("Illegal index: " + r);
	}

	public String toString() {
		String toReturn = "(";
		if (size() == 0)
			return "()";
		for (int i = 0; i < size(); i++)
			toReturn += A[i].toString() + ", ";
		return toReturn.substring(0, toReturn.length() - 2) + ")";
	}

	@Override
	public int indexOf(T e) throws IndexOutOfBoundsException {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == e) {
				return i;
			}
		}
		return -1;
	}
}
