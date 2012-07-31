package com.sfeir.labs.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.sfeir.labs.client.Layout.CommentsThread;
import com.sfeir.labs.client.Layout.MainLayout;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;

public class Budget implements EntryPoint {
	
	private VisualPie visualPie;
	private VisualHisto visualHisto;
	private Data data = new Data(2011);
	private MainLayout mainLayout;
	private StyleBundle styleBundle = GWT.create(StyleBundle.class);
	  
	@Override
	public void onModuleLoad() {
		mainLayout = new MainLayout(new CommentsThread("budgetdata"));
		 RootPanel.get().add(mainLayout);
		styleBundle.table().ensureInjected();

		Runnable onLoadCallback = new Runnable() {
	      public void run() {
	    	  visualPie = new VisualPie(data.allMinister());
	    	  data.setLegend(new FlexTable());
	    	  visualHisto = new VisualHisto(data.oneMinister(data.getCodeMinisterMax()));
	    	  mainLayout.addLegend(data.getLegend());
	    	  visualPie.getPieBudgetGlobal().addSelectHandler(createSelectHandler(visualPie.getPieBudgetGlobal()));
	    	  visualHisto.getColumnChart().addSelectHandler(createSelectLegend(visualHisto.getColumnChart()));
	    	  mainLayout.setPieChart(visualPie.getPieBudgetGlobal(),"Proportions des budgets par ministère");
			  mainLayout.setColumn(visualHisto.getColumnChart(),data.getTitreGraphe());
	      }
	    };
	    VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
	}
//------------------------------------------------------------------------------
	  private SelectHandler createSelectHandler(final PieChart chart) {
	    return new SelectHandler() {
	      @Override
	      public void onSelect(SelectEvent event) {
	        JsArray<Selection> selections = chart.getSelections();
	        for (int i = 0; i < selections.length(); i++) {
	          Selection selection = selections.get(i);
	          if (selection.isCell() || selection.isRow()) {
	        	  double codeMinister=0;
	        	  int cMinister;
	        	 codeMinister =  data.allMinister().getValueDouble(selection.getRow(), 2);
	        	 cMinister = (int)codeMinister;
	        	  mainLayout.removeWidget(data.getLegend());
	        	  data.setLegend(new FlexTable());
	        	  visualHisto = new VisualHisto(data.oneMinister(cMinister));
	        	  mainLayout.addLegend(data.getLegend());
	        	  mainLayout.setColumn(visualHisto.getColumnChart(),data.getTitreGraphe());
	        	  visualHisto.getColumnChart().addSelectHandler(createSelectLegend(visualHisto.getColumnChart()));
	          }
	        }
	      }
	    };
	  }   
	
	//------------------------------------------------------------------------------
	  private SelectHandler createSelectLegend(final ColumnChart histo) {
	    return new SelectHandler() {
	      @Override
	      public void onSelect(SelectEvent event) {
	    	 // HTMLTable.RowFormatter rf = data.getLegend().getRowFormatter();
	    	  HTMLTable.RowFormatter rf = data.getLegend().getRowFormatter();
	    	  double numLigne=0;
	        JsArray<Selection> selections = histo.getSelections();
	        for(int i=0; i<data.getLegend().getRowCount(); i++){
	        	rf.setStyleName(i, styleBundle.table().initialStyle());
	        }
	        for (int i = 0; i < selections.length(); i++) {
	        	
	        	rf = data.getLegend().getRowFormatter();
	        	rf.setStyleName(i, styleBundle.table().initialStyle());
	        	
	        	Selection selection = selections.get(i);
	        	if (selection.isCell() || selection.isRow()) {
	        	  numLigne  =  selection.getRow();
	        	  rf.setStyleName((int)numLigne, styleBundle.table().flexTableEvenRow());
	        	  mainLayout.positionLegend((int)numLigne*20);
	          }
	        }
	      }
	    };
	  }
	  
}
