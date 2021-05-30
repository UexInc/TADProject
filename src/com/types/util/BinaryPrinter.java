package com.types.util;

import com.types.interfaces.BTPosition;

public class BinaryPrinter {
	
	private static final int COUNT = 10;
	
	private static <T> String print2DUtil(BTPosition<T> root, int space) 
	{ 
	    // Base case 
	    if (root == null) 
	        return ""; 
	  
	    // Increase distance between levels 
	    space += COUNT; 
	    String s = "";
	  
	    // Process right child first 
	    s += print2DUtil(root.getRight(), space); 
	  
	    // Print current node after space 
	    // count 
	    s += "\n";
	    for (int i = COUNT; i < space; i++) 
	        s += " ";
	    s += root.element() + "\n";
	  
	    // Process left child 
	    s += print2DUtil(root.getLeft(), space); 
	    return s;
	}
	
	private static <T> void traverseNodes(StringBuilder sb, String padding, String pointer, BTPosition<T> node, 
			  boolean hasRightSibling) {
			if (node != null) {
				sb.append("\n");
				sb.append(padding);
				sb.append(pointer);
				sb.append(node.element().toString());

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
	        return "";
	    }

	    StringBuilder sb = new StringBuilder();
	    sb.append(root.element().toString());

	    String pointerRight = "└──";
	    String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

	    traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
	    traverseNodes(sb, "", pointerRight, root.getRight(), false);

	    return sb.toString();
	}
	
	public static <T> String print2D(BTPosition<T> root) 
	{ 
	    // Pass initial space count as 0 
	    return print2DUtil(root, 0); 
	}
}
