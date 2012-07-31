package com.sfeir.labs.client.Layout;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;

public class MainLayout extends Composite{ 

//	@UiField
//	HTMLPanel image;
	
	@UiField
	//HTMLPanel legende;
	ScrollPanel legende;
	
	@UiField
	Label titreG;
	
	@UiField
	Label titreD;

	@UiField
	GrapheG grapheG;
	
	@UiField(provided = true)
	final CommentsThread commentsThread;
	
	@UiField
	GrapheD grapheD;

	private static MainLayoutUiBinder uiBinder = GWT.create(MainLayoutUiBinder.class);

	interface MainLayoutUiBinder extends UiBinder<Widget, MainLayout> {
	}

	public MainLayout(CommentsThread commentsThread) {
		this.commentsThread = commentsThread;
		initWidget(uiBinder.createAndBindUi(this));
		this.legende.setStyleName(" table-stabletriped table-bordered table-condensed");
		commentsThread.showComments("budgetComments");
//		Image mypic = new Image("something");
//		this.image.add(mypic);
	}

	public void setPieChart(PieChart pieBudgetGlobal, String string) {
		titreG.setText(string);
		grapheG.setPanel(pieBudgetGlobal);
		
	}
	
	public void setColumn(ColumnChart histogram, String titre) {
		titreD.setText(titre);
		grapheD.setColomn(histogram);
	}
	
	public void addLegend(FlexTable flexTable){
		legende.setSize("470px", "150px");
		legende.add(flexTable);
		
		legende.scrollToTop();
		
	}
	
	public void positionLegend(int position){
		
		legende.setVerticalScrollPosition(position);		
		//legende.setHorizontalScrollPosition(position);	
	}
	
	public void removeWidget(FlexTable flexTable){
		
		legende.remove(flexTable);
	}
	
	
//
//	public void displayData(String aFournir){
//		commentsThread.showComments(aFournir);
//	}
}
