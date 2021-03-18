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
import org.jfree.*;
import data.MediatorResult;
import process.Mediator;

public class DashboardPanel extends JPanel{
		
//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
		private Mediator mediator = new Mediator();
		private MediatorResult	result;
		private JPanel resultOfEnterprisePanel = new JPanel();
		private JPanel leaveDayPanel = new JPanel();
		private JPanel salaryNotePanel = new JPanel();
		private JPanel monthSalaryPanel = new JPanel();
		private JPanel tasksDonePanel = new JPanel();
		

		public DashboardPanel() {
		 	 JPanel sartPanel = new JPanel();
		 	 sartPanel.setSize(800,600);
		}
		
		public void creatTasksDonePanel() {
			setSize(800,600);
			result=mediator.TasksDone();
//			System.out.println(result.getPedagogie());
			GridLayout resultLayout = new GridLayout(1,2);
			setLayout(resultLayout);
			add(creaeJTextArea("test"));
			// creation du cammember 
			DefaultPieDataset  pieDataset = new DefaultPieDataset();
			pieDataset.setValue("FR", 150); // value of german succurale
			pieDataset.setValue("GER", 50); // value of german succurale
			pieDataset.setValue("USA", 100); // value of usa succurale
			pieDataset.setValue("CHN", 200); // value of china succurale
			JFreeChart pieChart = ChartFactory.createPieChart("taskdone per Succurale", pieDataset, true, false, false);
			ChartPanel cPanel = new ChartPanel(pieChart); 
			add(cPanel);
			setVisible(true);
	
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
