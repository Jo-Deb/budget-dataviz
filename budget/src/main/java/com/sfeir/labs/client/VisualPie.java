package com.sfeir.labs.client;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;

public class VisualPie {
	private AbstractDataTable data;
	private PieChart pieBudgetGlobal;
	private Options options;
	
	public AbstractDataTable getData() {
		return data;
	}

	public PieChart getPieBudgetGlobal() {
		return pieBudgetGlobal;
	}

	public Options getOptions() {
		return options;
	}

	public VisualPie(AbstractDataTable d){
		this.data = d;
		this.setOptionVisual();
		pieBudgetGlobal =new PieChart(this.data, options);
	}
	
	public void setOptionVisual(){
	 	this.options = Options.create();
	    this.options.setHeight(400);
	    this.options.setBackgroundColor("transparent");
	}

}
