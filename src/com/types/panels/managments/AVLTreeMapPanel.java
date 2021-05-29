package com.types.panels.managments;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.tads.AVLTreeMap;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class AVLTreeMapPanel extends StandartPanel {

	private static final long serialVersionUID = -4234178847655292824L;

	private AVLTreeMap<Object, Object> AVL = new AVLTreeMap<Object, Object>();
	
	public AVLTreeMapPanel() {
		init();
		renderComponents();
	}
	
	public void renderComponents() {
		tables = new Tables(10);
		instanceButton();
		buttonEvents(AVL);
		Descriptions.descriptionAVL(this);
		generateLateral(true);
	}
	
	@Override
	protected Entry insertEntry() {
		return null;
	}

	@Override
	protected Entry removeEntry() {
		return null;
	}

	@Override
	protected void insertEvent() {
		
	}

	@Override
	protected void removeEvent() {
		
	}

}
