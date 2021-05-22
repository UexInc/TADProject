package com.types.interfaces;

public interface TreePosition<T> extends Position<T> {

	// Define o elemento a ser armazenado nesta posi��o
	public void setElement(T o);

	// Retorna o elemento armazenado nesta posi��o
	public T getElement();

	// Retorna os filhos desta posi��o
	public PositionList<Position<T>> getChildren();

	// Define os filhos desta posi��o
	public void setChildren(PositionList<Position<T>> c);

	// Retorna o pai desta posi��o
	public TreePosition<T> getParent();

	// Define o pai desta posi��o
	public void setParent(TreePosition<T> v);

}
