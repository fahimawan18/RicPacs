package com.pacs.ui.beans.admin;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.pacs.bll.admin.ChartBll;
import com.pacs.utils.Environment;

@ManagedBean(name="chartBean")
@SessionScoped
public class ChartBean 
{
	private PieChartModel aetUsedSpaceChart;
	private PieChartModel modalityUsedSpaceChart;
	private BarChartModel modalityCountChart;
	
	
	
	private Integer showFreeSpaceOptionAet=1;
	private Integer showFreeSpaceOptionModality=1;
//	0-> Do not Show, 1-> Show
	
	private Integer conversionOption =1;
//	0->KB, 1->MB, 2->GB
	
	public ChartBean() 
	{
		
		this.aetUsedSpaceChart = new PieChartModel();
		this.modalityUsedSpaceChart = new PieChartModel();
		this.modalityCountChart = new BarChartModel();
		
		
	}

	
	@PostConstruct
	private void initDiskUsageCharts()
	{
		initAetChart();
		initModalityChart();
		initModalityCountChart();
		
	}
	
	private void initAetChart()
	{
		System.out.println("In initAetChart PostConstruct");
		ChartBll bll = new ChartBll();		
//		aetUsedSpaceChart.set("Brand 4", 421);
		this.aetUsedSpaceChart = new PieChartModel();
		this.aetUsedSpaceChart = bll.populateAetChart(aetUsedSpaceChart,conversionOption,showFreeSpaceOptionAet);		
		aetUsedSpaceChart.setTitle("AET Usage Chart");
		aetUsedSpaceChart.setLegendPosition("w");
		aetUsedSpaceChart.setShowDataLabels(true);
		aetUsedSpaceChart.setShowDatatip(true);
	}
	
	private void initModalityChart()
	{
		ChartBll bll = new ChartBll();
		System.out.println("In initModalityChart PostConstruct");
		this.modalityUsedSpaceChart = new PieChartModel();
		this.modalityUsedSpaceChart = bll.populateModalityChart(modalityUsedSpaceChart, conversionOption,showFreeSpaceOptionModality);
		modalityUsedSpaceChart.setTitle("Modality Usage Chart");
		modalityUsedSpaceChart.setLegendPosition("e");
		modalityUsedSpaceChart.setShowDataLabels(true);
		modalityUsedSpaceChart.setShowDatatip(true);
	}
	
	
	private void initModalityCountChart()
	{
		ChartBll bll = new ChartBll();
		System.out.println("In initModalityCountChart PostConstruct");
		this.modalityCountChart = new BarChartModel();
		this.modalityCountChart = bll.populateModalityCountChart(modalityCountChart);
		modalityCountChart.setTitle("Studies Against Modalities ");
//		modalityCountChart.setLegendPosition("ne");
		
		Axis xAxis = modalityCountChart.getAxis(AxisType.X);
        xAxis.setLabel("Modalities");
         
        Axis yAxis = modalityCountChart.getAxis(AxisType.Y);
        yAxis.setLabel("Number of Studies");
        Integer width;
        try
        {
        	width =Integer.valueOf(Environment.getBarChartWidth());
        }
        catch(NumberFormatException e)
        {
        	width = 60;
        }
        catch(Exception e)
        {
        	width = 60;
        }
        
		modalityCountChart.setBarWidth(width);
		modalityCountChart.setShowDatatip(false);
		modalityCountChart.setShowPointLabels(true);
		modalityCountChart.setExtender("ext");
		
	}
	
	
	
	public void onAetConversionSelect(AjaxBehaviorEvent event) 
	 {
		 System.out.println("Selected conversion option is ="+this.conversionOption);
		 System.out.println("free space option is ="+this.showFreeSpaceOptionAet);
		 initAetChart();
	 }
	
	public void onModalityConversionSelect(AjaxBehaviorEvent event) 
	 {
		 System.out.println("Selected conversion option is ="+this.conversionOption);
		 System.out.println("free space option is ="+this.showFreeSpaceOptionModality);
		 initModalityChart();
	 }
	
	
	
	
	

	public PieChartModel getAetUsedSpaceChart() {
		return aetUsedSpaceChart;
	}

	public void setAetUsedSpaceChart(PieChartModel aetUsedSpaceChart) {
		this.aetUsedSpaceChart = aetUsedSpaceChart;
	}

	public PieChartModel getModalityUsedSpaceChart() {
		return modalityUsedSpaceChart;
	}

	public void setModalityUsedSpaceChart(PieChartModel modalityUsedSpaceChart) {
		this.modalityUsedSpaceChart = modalityUsedSpaceChart;
	}

	public Integer getConversionOption() {
		return conversionOption;
	}

	public void setConversionOption(Integer conversionOption) {
		this.conversionOption = conversionOption;
	}


	public Integer getShowFreeSpaceOptionAet() {
		return showFreeSpaceOptionAet;
	}


	public void setShowFreeSpaceOptionAet(Integer showFreeSpaceOptionAet) {
		this.showFreeSpaceOptionAet = showFreeSpaceOptionAet;
	}


	public Integer getShowFreeSpaceOptionModality() {
		return showFreeSpaceOptionModality;
	}


	public void setShowFreeSpaceOptionModality(Integer showFreeSpaceOptionModality) {
		this.showFreeSpaceOptionModality = showFreeSpaceOptionModality;
	}


	public BarChartModel getModalityCountChart() {
		return modalityCountChart;
	}


	public void setModalityCountChart(BarChartModel modalityCountChart) {
		this.modalityCountChart = modalityCountChart;
	}


	
}

