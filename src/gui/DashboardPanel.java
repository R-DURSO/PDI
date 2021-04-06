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

/**
 * This class process the elements display on the dashboard panel
 * 
 * @author Kevin BERNARD, Raphael D'URSO, Laura FUSTINONI, Aelien MOUBECHE
 * @version
 *
 */
public class DashboardPanel extends JPanel {

//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
	private static Logger logger = LoggerUtility.getLogger(DashboardPanel.class, LoggerUtility.LOG_PREFERENCE);
	private Mediator mediator = new Mediator();
	private MediatorResult result;
	boolean isUser = false;

	/**
	 * Constructor.
	 * This method allows to build the dashboard panel
	 */
	public DashboardPanel() {
		
		JPanel sartPanel = new JPanel();
		sartPanel.setSize(800, 800);
		
	}

	/**
	 * This method creates the tasks done panel elements on the dashboard
	 */
	public void creatTasksDonePanel() {
		
		if (!isUser) {
			result = mediator.TasksDone();
			createCicurlarPanel(result);
			isUser=true;
		}
		
	}

	/**
	 * This method creates the wages information panel elements on the dashboard
	 */
	public void creatWagesInfosPanel() {

	}

	/**
	 * This method creates the leave usage panel elements on the dashboard
	 */
	public void creatLeaveUsagePanel() {
		
		result = mediator.leaveUsage();
		createTextPanel(result);
		
	}

	/**
	 * This method creates the month employee panel elements on the dashboard
	 */
	public void creatMonthEmpPanel() {

	}

	/**
	 * This method creates the expensive employee panel elements on the dashboard
	 */
	public void creatExpensiveEmpPanel() {

	}

	/**
	 * This method creates the formation utility panel elements on the dashboard
	 */
	public void creatFormationUtilityPanel() {

	}

	/**
	 * This method creates the result by seniority panel elements on the dashboard
	 */
	public void creatResBySeniorityPanel() {
		result = mediator.resultsBySeniority();
		createListPanel(result);
	}

	/**
	 * This method creates the type of contract panel elements on the dashboard
	 */
	public void creatTypeContractPanel() {

	}

	/**
	 * This method creates the employ cost panel elements on the dashboard
	 */
	public void creatEmployCostPanel() {

	}

	/**
	 * This method creates the salary grades panel elements on the dashboard
	 */
	public void creatSalGradesPanel() {
		
		result = mediator.SalaryNote();
		createListPanel(result);
		
	}

	/**
	 * This method creates the pay panel elements on the dashboard
	 */
	public void creatPayPanel() {

	}

	/**
	 * This method allows to create a JTextArea on the dashboard
	 * 
	 * @param text : the text to put in the JTextArea in String
	 * @return JTextArea containing the text
	 */
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
	
	/**
	 * This method allows to create a panel with a circular graphic on the dashboard
	 * 
	 * @param result : result of the mediator
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
	
	/**
	 * This method allows to create a panel with a bar char graphic on the dashboard
	 * 
	 * @param result : result of the mediator
	 */
	public void createBarCharGraphic(MediatorResult result) {
		
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
	
	/**
	 * This method allows to create a text panel with information on the dashboard
	 * 
	 * @param result : result of the mediator
	 */
	public void createTextPanel(MediatorResult result) {
		
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		add(creaeJTextArea(result.getInformation()));
		
	}
	
	/**
	 * This method allows to create a list panel on the dashboard
	 * 
	 * @param result : result of the mediator
	 */
	public void createListPanel(MediatorResult result) {
		
		setSize(800, 600);
		GridLayout resultLayout = new GridLayout(1, 2);
		setLayout(resultLayout);
		add(creaeJTextArea(result.getPedagogie()));
		String liString = "";
		for (String  line: result.getResult()) {
			liString =liString+line;
		}
		add( creaeJTextArea(liString));
		
	}
	
}
