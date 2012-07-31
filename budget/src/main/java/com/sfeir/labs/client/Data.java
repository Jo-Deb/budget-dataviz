package com.sfeir.labs.client;

import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.DataView;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.sfeir.labs.client.data.DataBundle;

public class Data {
	
	private FlexTable dataBudget = new FlexTable();
	private String titreGraphe;
	private FlexTable legend = new FlexTable();
	private DataTable oneMinister;
	private int codeMinisterMax;
	private int codeMinisterCurrent;
	
	public Data(int year){
		//gestion fichier	
		   //ouverture du fichier CSV
			TextResource budget;
			DataBundle dataBundle = GWT.create(DataBundle.class);
			if (year == 2012){
				budget = dataBundle.budget2012();
			}else{
				budget = dataBundle.budget2011();
			}
			String text = budget.getText();
			List<String> lines = Lists.newArrayList(Splitter.on("\n").split(text));
			//traitement des lignes récupérées
			int ligne=0,colonne=0;
			for (String tmp_ligne : lines) {
				colonne=0;
				List<String> content_line = Lists.newArrayList(Splitter.on(";").split(tmp_ligne));
				for (String tmp_ligne2 : content_line) {
						dataBudget.setText(ligne, colonne, tmp_ligne2);
						colonne++;
				}
				ligne++;	
			}
	}

	  public AbstractDataTable allMinister(){
			 
		  DataTable tabMinister = DataTable.create();
		  tabMinister.addColumn(ColumnType.STRING, "nomminister");
		  tabMinister.addColumn(ColumnType.NUMBER, "code");
		  tabMinister.addColumn(ColumnType.NUMBER, "budget");

		  int nbTempMinister=0;
		  double tempAE=0;
		  double totalAE=0;
		  double codeminister=0;
		  String NomMinister=null;
	
		  for (int i = 0; i < this.dataBudget.getRowCount(); i++) {
			  
			    tempAE = Double.parseDouble(this.dataBudget.getText(i,6));
			    codeminister = Double.parseDouble(this.dataBudget.getText(i,0));
			    NomMinister = this.dataBudget.getText(i,1);
			  
			  if (i!=0 && this.dataBudget.getText(i,0).equals(this.dataBudget.getText(i-1,0))) {
				  nbTempMinister = tabMinister.getNumberOfRows()-1;
				//somme budget
					totalAE= tempAE + tabMinister.getValueDouble(nbTempMinister,1);
					
			} else if(i==0){
				nbTempMinister = 0;
				tabMinister.addRows(1);
				tabMinister.setValue(0, 0, "code");
				tabMinister.setValue(0, 1, 0);
				tabMinister.setValue(0, 2, 0);
				//somme budget
				totalAE= tempAE + tabMinister.getValueDouble(nbTempMinister,1);
			} else{
				nbTempMinister = tabMinister.getNumberOfRows();
				tabMinister.addRows(1);
				//somme budget
				totalAE= tempAE;
			}
			//enregistrement dans cellule du code ministere
			
			//double to string
			//totalAEs = Double.toString(totalAE);
			//enregistrement dans cellule du total 
			tabMinister.setValue(nbTempMinister, 0,NomMinister );  
	  		tabMinister.setValue(nbTempMinister, 1,totalAE );
	  		tabMinister.setValue(nbTempMinister, 2, codeminister);
	  		//Recherche du budget majeur
	  		double tempMax = tabMinister.getValueDouble(0, 1);
	  		double codeMax = tabMinister.getValueDouble(0, 2);;
	  		for (int j = 0; j < tabMinister.getNumberOfRows(); j++) {
				if (tabMinister.getValueDouble(j, 1)> tempMax) {
					tempMax =tabMinister.getValueDouble(j, 1);
					codeMax = tabMinister.getValueDouble(j, 2);
				}
			}
	  		codeMinisterMax = (int)codeMax;
	  		//CodeMinisterMax = codeMax;
	  		//------------------------------------------------------------------------------------
	  		//------------------------------------------------------------------------------------
		}   
		  DataView result = DataView.create(tabMinister);
		  result.setColumns(new int[]{0, 2});
		  return tabMinister;
	  }
	
	  public int getCodeMinisterMax() {
		return codeMinisterMax;
	}

	public AbstractDataTable oneMinister(int code){
		  
		  this.oneMinister = DataTable.create();
		  this.oneMinister.addColumn(ColumnType.STRING, "Ministère");
		  this.oneMinister.addColumn(ColumnType.NUMBER, "Total AE");
		  this.oneMinister.addColumn(ColumnType.NUMBER, "Total CP");
		  
		  int nbTempMinister=0;
		  double AE=0;
		  double CP=0;
		  double codeminister=0;
		  String libProg=null;

		  for (int i = 0; i < dataBudget.getRowCount(); i++) {
			  
			  	codeminister = Double.parseDouble(dataBudget.getText(i,0));
			  	libProg = dataBudget.getText(i,3);
			    AE = Double.parseDouble(dataBudget.getText(i,6));
			    CP = Double.parseDouble(dataBudget.getText(i,9));
			    
			  if (codeminister==code) {
				  
				  	nbTempMinister = oneMinister.getNumberOfRows();
					
				  	oneMinister.addRows(1);;
					
					this.legend.setText(nbTempMinister, 0, new Integer(nbTempMinister+1).toString());
					this.legend.setText(nbTempMinister, 1, libProg);
					
					oneMinister.setValue(nbTempMinister, 0,new Integer(nbTempMinister+1).toString()); 
					oneMinister.setValue(nbTempMinister, 1,AE );
					oneMinister.setValue(nbTempMinister, 2, CP);
					
					this.titreGraphe = dataBudget.getText(i,1);				
			} 
		} 
		  return oneMinister;
	  }

	public FlexTable getDataBudget() {
		return dataBudget;
	}

	public String getTitreGraphe() {
		return titreGraphe;
	}

	public DataTable getOneMinister() {
		return oneMinister;
	}

	public FlexTable getLegend() {
		return legend;
	}

	public void setLegend(FlexTable legend) {
		this.legend = legend;
	}

}