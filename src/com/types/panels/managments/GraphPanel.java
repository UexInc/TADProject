package com.types.panels.managments;

import com.types.panels.Entry;
import com.types.panels.StandartPanel;
import com.types.util.Descriptions;
import com.types.util.Tables;

public class GraphPanel extends StandartPanel {

	private static final long serialVersionUID = 1019346404504823868L;
	
	public GraphPanel() {
		init();
		renderComponents();
	}
	
	public void renderComponents() {
		tables = new Tables(11);
		instanceButton();
		buttonEvents(null);
		Descriptions.descriptionGraph(this);
		generateLateral(false);
	}
	
	protected Entry insertEntry() { return null; }
	protected Entry removeEntry() { return null; }
	protected void insertEvent() { }
	protected void removeEvent() { }

}
