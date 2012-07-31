package com.sfeir.labs.client;

import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

public class VisualHisto {

	private ColumnChart columnChart;
	public ColumnChart getColumnChart() {
		return columnChart;
	}

	public AbstractDataTable getData() {
		return data;
	}

	public Options getOptions() {
		return options;
	}

	private AbstractDataTable data;
	private Options options;
	
	public VisualHisto(AbstractDataTable d){
		this.data=d;
		this.setVisual();
		this.columnChart = new ColumnChart(this.data,this.options);
	}
	
	public void setVisual(){
	  this.options = Options.create();
	  this.options.setBackgroundColor("transparent");
	  this.options.setHeight(400);
	}
}
