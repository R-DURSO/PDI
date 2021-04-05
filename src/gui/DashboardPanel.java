package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.log4j.Logger;
import org.jfree.*;

import data.DataForBarChartGraphic;
import data.DataForLinearGraphic;
import data.DataforCircularGraphic;
import data.MediatorResult;
import data.Pedagogy;
import process.Mediator;
import logger.LoggerUtility;

public class DashboardPanel extends JPanel {

//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
	private static Logger logger = LoggerUtility.getLogger(DashboardPanel.class, LoggerUtility.LOG_PREFERENCE);
	private Mediator mediator = new Mediator();
	private MediatorResult result;
	boolean isUser = false;

	public DashboardPanel() {
		JPanel sartPanel = new JPanel();
		sartPanel.setSize(800, 600);
	}

	public void creatTasksDonePanel() {
		if (!isUser) {
			result = mediator.TasksDone();
			createCicurlarPanel(result);
			isUser=true;
		}
	}

	public void creatWagesInfosPanel() {

	}

	public void creatLeaveUsagePanel() {
		result = mediator.leaveUsage();
		createTextPanel(result);
	}

	public void creatMonthEmpPanel() {

	}

	public void creatExpensiveEmpPanel() {

	}

	public void creatFormationUtilityPanel() {

	}

	public void creatResBySeniorityPanel() {

	}

	public void creatTypeContractPanel() {

	}

	public void creatEmployCostPanel() {

	}

	public void creatSalGradesPanel() {
		result = mediator.SalaryNote();
		
	}

	public void creatPayPanel() {

	}

	public JTextArea creaeJTextArea(String text) {
			JTextArea jTextArea = new JTextArea(text);
			jTextArea.setEnabled(false);
			jTextArea.setForeground(Color.WHITE);
			jTextArea.setLineWrap(true);
			jTextArea.setBackground(Color.BLACK);
			return jTextArea;
		}
	/*
	 * Prototypes histogramme
	 * 

	 * 
	 * ChartPanel cPanel = new ChartPanel(barChart); pnl.add(cPanel);
	 */

	/*
	 * Prototype graphique
	 *


	 *
		
		

	*/
	public void createCicurlarPanel(MediatorResult result) {
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		// creation du cammember
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		/*
		 * we use data from MediatorResult for create de cicurla graphic 
		 */
		for(DataforCircularGraphic  data : result.getCicularGraphic() ) {
			pieDataset.setValue(data.getWhoValues(),data.getValues());
		}
		JFreeChart pieChart = ChartFactory.createPieChart(result.getGraphicTitle(), pieDataset, true, false, false);
		ChartPanel cPanel = new ChartPanel(pieChart);
		add(cPanel);
		isUser = true;
		
	}
	
	public void createLinearGraphic(MediatorResult result) {
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		 var series = new XYSeries(result.getNameCourbe()); // nom de la courbe 
		 for( DataForLinearGraphic data : result.getLinearGraphics()) {
				series.add(data.getValuesX(),data.getValuesY() ) ;  // axe X , axe Y 
		 }
		 var dataset = new XYSeriesCollection();
		 dataset.addSeries(series); // ajout des statistisque remplit dans series 
		 	JFreeChart chart = ChartFactory.createXYLineChart(
	        result.getGraphicTitle(), // titre du graphique 
	        result.getValueX(), 			// nom de la valeur de l'axe X
	        result.getValueY(),  // nom de la valeur de l'axe Y
	        dataset, 				// donnée a mettre 
	        PlotOrientation.VERTICAL,
	        true, 
	        true, 
	        false 
	);
		 	ChartPanel chartPanel = new ChartPanel(chart);
		 	add(chartPanel);
	}
	
	public void createBarCharGraphic(MediatorResult result){
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		 DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
		 for(DataForBarChartGraphic data : result.getBarChartGraphic()) {
			 dataset.setValue(data.getValue(),data.getCompareValue() ,data.getWhoHaveValue() );
		 }
		 JFreeChart barChart = ChartFactory.createBarChart(result.getGraphicTitle(), "", result.getValueCompare() ,
		 dataset, PlotOrientation.VERTICAL, true, true, false); 
		 ChartPanel chartPanel = new ChartPanel( barChart ); 
		 add(chartPanel);
		 isUser=true;
	}
	public void createTextPanel(MediatorResult result) {
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		add(creaeJTextArea(result.getInformation()));
	}
	
	public void createListPanel(MediatorResult result) {
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		
		JTextArea  List = creaeJTextArea("");
	}
}
