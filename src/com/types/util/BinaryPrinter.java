package com.types.util;

import com.types.interfaces.BTPosition;

public class BinaryPrinter {
	
	private static <T> void traverseNodes(StringBuilder sb, String padding, String pointer, BTPosition<T> node, 
			  boolean hasRightSibling) {
		
			if (node != null) {
				sb.append("\n");
				sb.append(padding);
				sb.append(pointer);
				sb.append(node.element() == null ? "" : node.element());					

				StringBuilder paddingBuilder = new StringBuilder(padding);
				if (hasRightSibling) {
					paddingBuilder.append("│  ");
			} else {
				paddingBuilder.append("   ");
			}

			String paddingForBoth = paddingBuilder.toString();
			String pointerRight = "└──";
			String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

		    traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
			traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
		}
	}
	
	
	
	public static <T> String traversePreOrder(BTPosition<T> root) {
		if (root == null) {
			return null;
		}
	    if (root.element() == null) {
	        return null;
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append(root.element().toString());

	    String pointerRight = "└──";
	    String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

	    traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
	    traverseNodes(sb, "", pointerRight, root.getRight(), false);

	    return sb.toString();
	}
}
