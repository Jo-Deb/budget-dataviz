package com.sfeir.labs.client.Layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class GrapheG extends Composite {

	private static GrapheGUiBinder uiBinder = GWT.create(GrapheGUiBinder.class);

	interface GrapheGUiBinder extends UiBinder<Widget, GrapheG> {
	}

	@UiField
	HTMLPanel globalContainer;
	
	public GrapheG() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setPanel(IsWidget child) {
		globalContainer.add(child);
	}
}
