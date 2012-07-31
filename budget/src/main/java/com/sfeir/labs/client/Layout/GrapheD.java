package com.sfeir.labs.client.Layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class GrapheD extends Composite {

	private static GrapheDUiBinder uiBinder = GWT.create(GrapheDUiBinder.class);

	interface GrapheDUiBinder extends UiBinder<Widget, GrapheD> {
	}

	

	@UiField
	HTMLPanel globalContainer2;
	
	public GrapheD() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setColomn(IsWidget child) {
		globalContainer2.clear();
		globalContainer2.add(child);
	}
	
}
