package com.types.design;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class LayoutPanel extends GridBagLayout {

	private static final long serialVersionUID = -6386629469893646173L;

	public GridBagConstraints cons;
	
	public LayoutPanel() {
		this.cons = new GridBagConstraints();
	}
	
	public void setConstraint(int fill, int anchor, int x, int y, double wX, double wY) {
		this.cons.fill = fill;
		this.cons.anchor = anchor;
		this.cons.gridx = x;
		this.cons.gridy = y;
		this.cons.weightx = wX;
		this.cons.weighty = wY;
	}

}
