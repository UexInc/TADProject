package com.types.interfaces;

import com.types.exceptions.EmptyQueueException;

public interface Queue<T> {
	
	/* Retorna o n�mero de elementos na fila.
	* @return n�mero de elementos na fila. */
	public int size();
	
	/* Retorna se a fila est� vazia.
	* @return true se a fila estiver vazia, false em caso contr�rio. */
	public boolean isEmpty();

	/* Inspeciona o elemento � frente da fila.
	* @return o elemento � frente da fila.
	* @exception EmptyQueueException se a fila estiver vazia. */
	public T front() throws EmptyQueueException;

	/* Insere elemento no final da fila.
	* @param element, o novo elemento a ser inserido. */
	public void enqueue(T element);

	/* Remove o elemento � frente da fila.
	* @return elemento removido.
	* @exception EmptyQueueException se a fila estiver vazia. */
	public T dequeue() throws EmptyQueueException;
	
}
