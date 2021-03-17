package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.*;
import data.MediatorResult;
import process.Mediator;

public class DashboardPanel extends JPanel{
		
//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
		private Mediator mediator = new Mediator();
		private MediatorResult	result;/*		private JPanel resultOfEnterprisePanel = new JPanel();
		private JPanel leaveDayPanel = new JPanel();
		private JPanel salaryNotePanel = new JPanel();
		private JPanel monthSalaryPanel = new JPanel();
		private JPanel tasksDonePanel = new JPanel();*/
		

		public DashboardPanel() {
			
		}
		
		public void creatTasksDonePanel() {
	 	new JPanel();
			setSize(800,600);
			//result=mediator.TasksDone();
//			System.out.println(result.getPedagogie());
			GridLayout resultLayout = new GridLayout(1,2);
			setLayout(resultLayout);
			add(creaeJTextArea("test"));
			// creation du cammember 
			DefaultPieDataset  pieDataset = new DefaultPieDataset();
			pieDataset.setValue("GER", 0); // value of german succurale
			pieDataset.setValue("USA", 0); // value of usa succurale
			pieDataset.setValue("CHN", 0); // value of china succurale
			JFreeChart pieChart = ChartFactory.createPieChart("taskdone per Succurale", pieDataset, true, false, false);
			ChartPanel cPanel = new ChartPanel(pieChart); 
			add(cPanel);
			repaint();
		
		}
		
		public JPanel creatWagesInfosPanel() {
			new JPanel();
			setSize(800,600);
			add();
		}
		
		public JPanel creatLeaveUsagePanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatMonthEmpPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public void creatExpensiveEmpPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatFormationUtilityPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatResBySeniorityPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatTypeContractPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatEmployCostPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatSalGradesPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
		}
		
		public JPanel creatPayPanel() {
			new JPanel();
			setSize(800,600);
			add(/*a completer*/);
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
