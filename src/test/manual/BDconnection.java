package test.manual;

import gui.ControlPanel;
import gui.MainPanel;
import process.Mediator;

public class BDconnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mediator test = new Mediator();
		MainPanel mainPanel = new MainPanel("Global Human Resources");
		Thread mainPanelThread = new Thread(mainPanel);
		mainPanelThread.start();
		
/*		ControlPanel controlPanel =  new ControlPanel();
		Thread controlPanelThread = new Thread(controlPanel);
		controlPanelThread.start();*/
		
		test.MonthSalary();
		test.SalaryNote();
		test.LeaveDay();
	}

}
