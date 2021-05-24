package com.types.util;

import com.types.controllers.Types;
import com.types.interfaces.Position;
import com.types.interfaces.PositionList;
import com.types.nodes.TreeNode;
import com.types.tads.LinkedTree;
import com.types.tads.NodePositionList;

public final class AuxTAD {

	public static TreeNode<Object> createChild(TreeNode<Object> p, Object n) {
		PositionList<Position<Object>> filhos;
		TreeNode<Object> aux;

		// Obtém os Filhos de p
		filhos = p.getChildren();

		// Cria um novo filho
		aux = new TreeNode<Object>();
		aux.setElement(n);
		aux.setParent(p);
		aux.setChildren(new NodePositionList<Position<Object>>());
		filhos.addLast(aux);
		return aux;
	}
	
	public static Position<Object> getPos(LinkedTree<Object> p, Object n) {
		for (Position<Object> pos : p.positions()) {
			if (pos.element().toString().equalsIgnoreCase(n.toString())) return pos;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Position<Object> getPos(Object element) {
		NodePositionList<Object> nodes = ((NodePositionList<Object>) Types.type);
		Position<Object> pos = nodes.first();
		for (@SuppressWarnings("unused") Object actual : nodes) {
			if (pos.element().toString().equalsIgnoreCase(element.toString())) {
				return pos;
			} else {
				pos = nodes.next(pos);
			}
		}
		return null;
	}

}
