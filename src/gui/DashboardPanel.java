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
		
/*		public JPanel creatResultOfEnterprisePanel(){
			new JPanel();
			setSize(800,600);
			add();
		}
		
		public JPanel creatLeaveDayPanel(){
			new JPanel();
			setSize(800,600);
//			add();
		}
		
		public JPanel creatSalaryNotePanel(){
			new JPanel();
			setSize(800,600);
//			add();
		}
		
		public JPanel creatMonthSalaryPanel(){
			new JPanel();
			setSize(800,600);
			add();
		}*/
		
		public void creatTasksDonePanel(){
			new JPanel();
			setSize(800,600);
			JTextField jtf = new JTextField();
			jtf.setText("Coucou ça fonctionne !!!");
			add(jtf);
			repaint();
		}
}
