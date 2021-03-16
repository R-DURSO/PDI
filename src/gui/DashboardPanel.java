package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import process.Mediator;

public class DashboardPanel extends JPanel{
		
//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();
		private Mediator mediator = new Mediator();
/*		private JPanel resultOfEnterprisePanel = new JPanel();
		private JPanel leaveDayPanel = new JPanel();
		private JPanel salaryNotePanel = new JPanel();
		private JPanel monthSalaryPanel = new JPanel();
		private JPanel tasksDonePanel = new JPanel();*/
		

		public DashboardPanel() {
			
		}
		
		public JPanel creatTasksDonePanel() {
			new JPanel();
			setSize(800,600);
			add();
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
}
