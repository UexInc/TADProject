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
	
	public static <T> String print2D(BTPosition<T> root) 
	{ 
	    // Pass initial space count as 0 
	    return print2DUtil(root, 0); 
	}
}
