package com.types.interfaces;

public interface BTPosition<T> extends Position<T>{

	// Retorna o elemento armazenado nesta posi��o
	public T element();

	// Define o elemento armazenado nesta posi��o
	public void setElement(T o);

	// Retorna o filho da esquerda desta posi��o
	public BTPosition<T> getLeft();

	// Define o filho da esquerda desta posi��o
	public void setLeft(BTPosition<T> v);

	// Retorna o filho da direita desta posi��o
	public BTPosition<T> getRight();

	// Define o filho da direita desta posi��o
	public void setRight(BTPosition<T> v);

	// Retorna o pai desta posi��o
	public BTPosition<T> getParent();

	// Define o pai desta posi��o
	public void setParent(BTPosition<T> v);
}
