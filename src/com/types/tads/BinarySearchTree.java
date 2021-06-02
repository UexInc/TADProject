package com.types.tads;

import java.util.Comparator;

import com.types.exceptions.DefaultComparator;
import com.types.exceptions.InvalidEntryException;
import com.types.exceptions.InvalidKeyException;
import com.types.exceptions.InvalidPositionException;
import com.types.interfaces.BTPosition;
import com.types.interfaces.Entry;
import com.types.interfaces.Map;
import com.types.interfaces.Position;
import com.types.interfaces.PositionList;
import com.types.util.BinaryPrinter;

//Implementacao de um dicionario com uma arvore binaria de pesquisa

public class BinarySearchTree<K, V> extends LinkedBinaryTree<Entry<K, V>> implements Map<K, V> {

	protected Comparator<K> C; // comparador
	protected Position<Entry<K, V>> actionPos; // pai do nodo inserido ou removido
	protected int numEntries = 0; // numero de elementos

	// Cria uma BinarySearchTreeMap com um comparador padrao.
	public BinarySearchTree() {
		C = new DefaultComparator<K>();
		addRoot(null);
	}
	
	// Cria uma BinarySearchTreeMap com um comparador padrao.
	public BinarySearchTree(Comparator<K> c) {
		C = c;
		addRoot(null);
	}

	// Classe aninhada para as entradas conscientes de localizacao da arvore binaria
	// de pesquisa.
	protected static class BSTEntry<K, V> implements Entry<K, V> {
		protected K key;
		protected V value;
		protected Position<Entry<K, V>> pos;

		BSTEntry() {
		} /* construtor padrao */

		BSTEntry(K k, V v, Position<Entry<K, V>> p) {
			key = k;
			value = v;
			pos = p;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public Position<Entry<K, V>> position() {
			return pos;
		}
		
		public String toString() {
			return "(" + getKey() + ", " + getValue() + ")";
		}
	}

	// Retorna a chave do elemento de um dado nodo da �rvore.
	protected K key(Position<Entry<K, V>> position) {
		return position.element().getKey();
	}

	// Retorna o valor do elemento de um dado nodo da �rvore.
	protected V value(Position<Entry<K, V>> position) {
		return position.element().getValue();
	}

	// Retorna o elemento de um dado nodo da �rvore.
	protected Entry<K, V> entry(Position<Entry<K, V>> position) {
		return position.element();
	}

	// Substitui um elemento por um novo elemento (e inicializa a localiza��o do
	// elementos)
	protected V replaceEntry(Position<Entry<K, V>> pos, Entry<K, V> ent) {
		((BSTEntry<K, V>) ent).pos = pos;
		return replace(pos, ent).getValue();
	}

	// Verifica se uma determinada chave � v�lida.
	protected void checkKey(K key) throws InvalidKeyException {
		if (key == null) // um teste simples
			throw new InvalidKeyException("chave nula");
	}

	// Verifica se um determinado elemento � v�lido.
	protected void checkEntry(Entry<K, V> ent) throws InvalidEntryException {
		if (ent == null || !(ent instanceof BSTEntry))
			throw new InvalidEntryException("elemento inv�lido");
	}

	// M�todo auxiliar para inserir um elemento em um nodo externo
	protected Entry<K, V> insertAtExternal(Position<Entry<K, V>> v, Entry<K, V> e) {
		expandExternal(v, null, null);
		replace(v, e);
		numEntries++;
		return e;
	}

	// M�todo auxiliar para remover um nodo externo e seu pai
	protected void removeExternal(Position<Entry<K, V>> v) {
		removeAboveExternal(v);
		numEntries--;
	}

	// M�todo auxiliar usado para pesquisar, inserir e remover.
	protected Position<Entry<K, V>> treeSearch(K key, Position<Entry<K, V>> pos) {
		if (isExternal(pos))
			return pos; // chave n�o encontrada; retorna o nodo externo
		else {
			K curKey = key(pos);
			int comp = C.compare(key, curKey);
			if (comp < 0)
				return treeSearch(key, left(pos)); // pesquisa na sub�rvore � esquerda
			else if (comp > 0)
				return treeSearch(key, right(pos)); // pesquisa na sub�rvore � direita
			return pos; // retorna o nodo interno onde a chave foi encontrada
		}
	}

	// m�todos do TAD
	public int size() {
		return numEntries;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public V get(K key) throws InvalidKeyException {
		checkKey(key); // pode lan�ar uma exce��o InvalidKeyException
		Position<Entry<K, V>> curPos = treeSearch(key, root());
		actionPos = curPos; // nodo onde a pesquisa finalizou
		if (isInternal(curPos))
			return value(curPos);
		return null;
	}

	public V put(K k, V x) throws InvalidKeyException {
		checkKey(k); // pode lan�ar uma exce��o InvalidKeyException
		Position<Entry<K, V>> insPos = treeSearch(k, root());
		BSTEntry<K, V> e = new BSTEntry<K, V>(k, x, insPos);
		actionPos = insPos; // nodo onde a entrada est� sendo inserida
		if (isExternal(insPos)) { // necessita de um nodo novo, a chave � nova
			insertAtExternal(insPos, e);
			return null;
		}
		return replaceEntry(insPos, e); // a chave j� existe
	}

	public V remove(K k) throws InvalidEntryException {
		checkKey(k); // pode lan�ar uma InvalidKeyException
		Position<Entry<K, V>> remPos = treeSearch(k, root());
		if (isExternal(remPos))
			return null; // chave n�o encontrada
		Entry<K, V> toReturn = entry(remPos); // entrada existente
		if (isExternal(left(remPos)))
			remPos = left(remPos); // caso f�cil pela esquerda
		else if (isExternal(right(remPos)))
			remPos = right(remPos); // caso f�cil pela direita
		else { // a entrada est� em nodo com filho interno
			Position<Entry<K, V>> swapPos = remPos; // encontra o nodo para a entrada que est� sendo movida
			remPos = right(swapPos);
			do
				remPos = left(remPos);
			while (isInternal(remPos));
			replaceEntry(swapPos, (Entry<K, V>) parent(remPos).element());
		}
		actionPos = sibling(remPos); // irm�o da folha que est� sendo removida
		removeExternal(remPos);
		return toReturn.getValue();
	}

	// Expande um nodo externo num nodo interno com dois nodos filhos externos
	public void expandExternal(Position<Entry<K, V>> v, Entry<K, V> l, Entry<K, V> r) throws InvalidPositionException {
		if (!isExternal(v))
			throw new InvalidPositionException("Node is not external");
		insertLeft(v, l);
		insertRight(v, r);
	}

	// Remove um nodo externo v e troca seu pai com o irm�o de v
	public void removeAboveExternal(Position<Entry<K, V>> v) throws InvalidPositionException {
		if (!isExternal(v))
			throw new InvalidPositionException("Node is not external");
		if (isRoot(v))
			remove(v);
		else {
			Position<Entry<K, V>> u = parent(v);
			remove(v);
			remove(u);
		}
	}

	// Retorna uma cole��o iterable contendo todas as chaves da �rvore
	public Iterable<K> keySet() {
		PositionList<K> keys = new NodePositionList<K>();
		Iterable<Position<Entry<K, V>>> positer = positionsInorder();
		for (Position<Entry<K, V>> cur : positer)
			if (isInternal(cur))
				keys.addLast(key(cur));
		return keys;
	}

	// Retorna uma cole��o iterable contendo todos os valores da �rvore
	public Iterable<V> values() {
		PositionList<V> vals = new NodePositionList<V>();
		Iterable<Position<Entry<K, V>>> positer = positionsInorder();
		for (Position<Entry<K, V>> cur : positer)
			if (isInternal(cur))
				vals.addLast(value(cur));
		return vals;
	}

	// Retorna uma cole��o iter�vel com todas as entradas da �rvore
	public Iterable<Entry<K, V>> entrySet() {
		PositionList<Entry<K, V>> entries = new NodePositionList<Entry<K, V>>();
		Iterable<Position<Entry<K, V>>> positer = positionsInorder();
		for (Position<Entry<K, V>> cur : positer)
			if (isInternal(cur))
				entries.addLast(cur.element());
		return entries;
	}

	// Exibe a express�o parentizada da �rvore
	public String printExpression(Position<Entry<K, V>> v) {
		String s = "";
		if (isInternal(v))
			s += "(";
		if (hasLeft(v))
			s += printExpression(left(v));
		if (v.element() != null)
			s += v.element().getKey().toString();
		if (hasRight(v))
			s += printExpression(right(v));
		if (isInternal(v))
			s += ")";
		return s;
	}

	protected Position<Entry<K, V>> restructure(Position<Entry<K, V>> x) {
		BTPosition<Entry<K, V>> a, b, c, t0, t1, t2, t3;
		Position<Entry<K, V>> y = parent(x); // assume que x tem um pai
		Position<Entry<K, V>> z = parent(y); // assume que y tem um pai

		BTPosition<Entry<K, V>> xx = (BTPosition<Entry<K, V>>) x;
		BTPosition<Entry<K, V>> yy = (BTPosition<Entry<K, V>>) y;
		BTPosition<Entry<K, V>> zz = (BTPosition<Entry<K, V>>) z;

		boolean xLeft = (x == left(y));
		boolean yLeft = (y == left(z));

		if (xLeft && yLeft) { // forma (b) de mapeamento
			a = xx;
			b = yy;
			c = zz;
			t0 = a.getLeft();
			t1 = a.getRight();
			t2 = b.getRight();
			t3 = c.getRight();
		} else if (!xLeft && yLeft) { // forma (d) de mapeamento
			a = yy;
			b = xx;
			c = zz;
			t0 = a.getLeft();
			t1 = b.getLeft();
			t2 = b.getRight();
			t3 = c.getRight();
		} else if (xLeft && !yLeft) { // forma (c) de mapeamento
			a = zz;
			b = xx;
			c = yy;
			t0 = a.getLeft();
			t1 = b.getLeft();
			t2 = b.getRight();
			t3 = c.getRight();
		} else { // // forma (a) de mapeamento
			a = zz;
			b = yy;
			c = xx;
			t0 = a.getLeft();
			t1 = b.getLeft();
			t2 = c.getLeft();
			t3 = c.getRight();
		}

		// Passo 2:
		// Substitua a sub�rvore enraizada em z por uma nova sub�rvore enraizada em b.
		if (isRoot(z)) {
			root = b;
			b.setParent(null);
		} else {
			BTPosition<Entry<K, V>> zParent = (BTPosition<Entry<K, V>>) parent(z);
			if (z == left(zParent)) { // z � filho esquerdo
				b.setParent(zParent);
				zParent.setLeft(b);
			} else { // z � filho direito
				b.setParent(zParent);
				zParent.setRight(b);
			}
		}

		// Passo 3:
		// Fa�a a o filho esquerdo de b e T0 e T1 as sub�rvores esquerda e direita de a,
		// respectivamente.
		b.setLeft(a);
		a.setParent(b);
		a.setLeft(t0);
		t0.setParent(a);
		a.setRight(t1);
		t1.setParent(a);

		// Passo 4:
		// Fa�a c o filho direito de b e T2 e T3 as sub�rvores esquerda e direita de c,
		// respectivamente.
		b.setRight(c);
		c.setParent(b);
		c.setLeft(t2);
		t2.setParent(c);
		c.setRight(t3);
		t3.setParent(c);

		// Redefine a entradas conscientes de localiza��o
		((BSTEntry<K, V>) a.element()).pos = a;
		((BSTEntry<K, V>) b.element()).pos = b;
		((BSTEntry<K, V>) c.element()).pos = c;
		return b; // a nova raiz desta sub�rvore
	}
	
	public String toString() {
		return BinaryPrinter.traversePreOrder(root);
	}
}
