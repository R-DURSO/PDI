package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.GuiData;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
* This class process the elements display and actions on the control panel
* 
* @author Kevin BERNARD, Raphael D'URSO, Laura FUSTINONI, Aelien MOUBECHE
* @version
*
*/
public class ControlPanel extends JPanel {
		
//		private ElementManager manager;
//		private PaintStrategy paintStrategy = new PaintStrategy();

	/**
	 * Constructor.
	 * This method allows to build the control panel with buttons and define their action
	 */
	public ControlPanel() {
		
		new JPanel();
		
		JPanel statPanel = new JPanel();
		JPanel payPanel = new JPanel();
		
		GridLayout panelLayout = new GridLayout(1,2);
		GridLayout statPanelLayout = new GridLayout(5,2);
		GridLayout payPanelLayout = new GridLayout(1,1);
		
		setLayout(panelLayout);
		statPanel.setLayout(statPanelLayout);
		payPanel.setLayout(payPanelLayout);
		/*statPanelLayout.preferredLayoutSize(statPanel);
		payPanelLayout.preferredLayoutSize(payPanel);
		panelLayout.preferredLayoutSize(payPanel);*/
			
		//setSize(800,200);
		/*statPanel.setSize(400,200);
		payPanel.setSize(400,200);*/
		
		JButton tasksDoneButton = new JButton();
		tasksDoneButton.setText("Résultats de l'entreprise");
		tasksDoneButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		tasksDoneButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(tasksDoneButton);
		tasksDoneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatTasksDonePanel();
			}
		});
		
		JButton wagesInfosButton = new JButton();
		wagesInfosButton.setText("Moyenne des salaires");
		wagesInfosButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		wagesInfosButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(wagesInfosButton);
		wagesInfosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatWagesInfosPanel();
			}
		});
		
		JButton leaveUsageButton = new JButton();
		leaveUsageButton.setText("Utilisation des congés");
		leaveUsageButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		leaveUsageButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(leaveUsageButton);
		leaveUsageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatLeaveUsagePanel();
			}
		});
		
		JButton monthEmpButton = new JButton();
		monthEmpButton.setText("Employé du mois");
		monthEmpButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		monthEmpButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(monthEmpButton);
		monthEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatMonthEmpPanel();
			}
		});
		
		JButton expensiveEmpButton = new JButton();
		expensiveEmpButton.setText("Employé le plus cher");
		expensiveEmpButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		expensiveEmpButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(expensiveEmpButton);
		expensiveEmpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatExpensiveEmpPanel();
			}
		});
		
		JButton formationUtilityButton = new JButton();
		formationUtilityButton.setText("Efficacité des formations");
		formationUtilityButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		formationUtilityButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(formationUtilityButton);
		formationUtilityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatFormationUtilityPanel();
			}
		});
		
		JButton resBySeniorityButton = new JButton();
		resBySeniorityButton.setText("Résultats par ancienneté");
		resBySeniorityButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		resBySeniorityButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(resBySeniorityButton);
		resBySeniorityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatResBySeniorityPanel();
			}
		});
		
		JButton typeContractButton = new JButton();
		typeContractButton.setText("Taux des contrats");
		typeContractButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		typeContractButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(typeContractButton);
		typeContractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatTypeContractPanel();
			}
		});
		
		JButton employCostButton = new JButton();
		employCostButton.setText("Coût de l'emploi");
		employCostButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		employCostButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(employCostButton);
		employCostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			MainPanel.creatEmployCostPanel();
			}
		});
		
		JButton salGradesButton = new JButton();
		salGradesButton.setText("Notes des salariés");
		salGradesButton.setBackground(GuiData.BACK_STAT_BUTTON_COLOR);
		salGradesButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		statPanel.add(salGradesButton);
		salGradesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatSalGradesPanel();
			}
		});
		
		//statPanel.add(new Button("Résultats de l'entreprise")); // tasksDoneButton
		//statPanel.add(new Button("Moyenne des salaires")); // wagesInfosButton
		//statPanel.add(new Button("Utilisation des congés")); // leaveUsageButton
		//statPanel.add(new Button("Employé du mois")); // monthEmpButton
		//statPanel.add(new Button("Employé le plus cher")); // expensiveEmpButton
		//statPanel.add(new Button("Efficacité des formations")); // formationUtilityButton
		//statPanel.add(new Button("Résultats par ancienneté")); // resBySeniorityButton
		//statPanel.add(new Button("Taux des contrats")); // typeContractButton
		//statPanel.add(new Button("Coût de l'emploi")); // employCostButton
		//statPanel.add(new Button("Notes des salariés")); // salGradesButton
		
		JButton payButton = new JButton();
		payButton.setText("Fiches de paie");
		payButton.setBackground(GuiData.BACK_PAY_BUTTON_COLOR);
		payButton.setForeground(GuiData.FORE_BUTTON_COLOR);
		payPanel.add(payButton);
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.creatPayPanel();
			}
		});
		
		add(statPanel);
		add(payPanel);
		}
	
//		public void paintComponent(Graphics g){
//			super.paintComponent(g);
//			paintStrategy.paint(, g);
			
//		}
		
}
