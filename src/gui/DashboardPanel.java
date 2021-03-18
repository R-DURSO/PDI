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
import org.jfree.data.general.DefaultPieDataset;
import org.apache.log4j.Logger;
import org.jfree.*;
import data.MediatorResult;
import data.Pedagogy;
import process.Mediator;
import logger.LoggerUtility;

public class DashboardPanel extends JPanel{
		
//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
		private static Logger logger = LoggerUtility.getLogger(DashboardPanel.class, LoggerUtility.LOG_PREFERENCE);
		private Mediator mediator = new Mediator();
		private MediatorResult	result;
		boolean isUser = false;		

		public DashboardPanel() {
		 	 JPanel sartPanel = new JPanel();
		 	 sartPanel.setSize(800,600);
		}
		
		public void  creatTasksDonePanel() {
			if(!isUser) {
				setSize(800,600);
				result=mediator.TasksDone();
//				System.out.println(result.getPedagogie());
				GridLayout resultLayout = new GridLayout(1,2);
				setLayout(resultLayout);
				add(creaeJTextArea(Pedagogy.statTasksDones));
				// creation du cammember 
				DefaultPieDataset  pieDataset = new DefaultPieDataset();
				pieDataset.setValue("FR", result.getResult().get("FR")); // value of german succurale
				pieDataset.setValue("GER", result.getResult().get("GER")); // value of german succurale
				pieDataset.setValue("USA", result.getResult().get("USA")); // value of usa succurale
				pieDataset.setValue("CHN", result.getResult().get("CHN")); // value of china succurale
				JFreeChart pieChart = ChartFactory.createPieChart("taskdone per Succurale", pieDataset, true, false, false);
				ChartPanel cPanel = new ChartPanel(pieChart); 
				add(cPanel);
				isUser=true;
			}

	
		/*	new JPanel();
			setSize(800,600);
			JTextField jtf = new JTextField();
			jtf.setText("Coucou ça fonctionne !!!");
			add(jtf);
			repaint();*/
		}
		
		public void creatWagesInfosPanel() {

		}
		
		public void creatLeaveUsagePanel() {

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

}
