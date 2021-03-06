package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import data.GuiData;

public class MainPanel extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private ControlPanel controlPanel;	
	private DashboardPanel dashboardPanel;
	private final static Dimension preferredSize = new Dimension(GuiData.WINDOW_WIDTH, GuiData.WINDOW_HEIGHT);
	private final static Dimension dashboardSize = new Dimension(GuiData.DASHBOARD_PANEL_WIDTH,GuiData.DASHBOARD_PANEL_HEIGHT);
	private final static Dimension controlPanelSize = new Dimension(GuiData.CONTROL_PANEL_WIDTH,GuiData.CONTROL_PANEL_HEIGHT);

	public MainPanel(String title) {
		super(title);
		this.setPreferredSize(preferredSize);

		init();
//		run();
	}

	private void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		dashboardPanel = new DashboardPanel();
		controlPanel = new ControlPanel();
		
		dashboardPanel.setPreferredSize(dashboardSize);
		controlPanel.setPreferredSize(controlPanelSize);
		
		contentPane.add(dashboardPanel, BorderLayout.NORTH);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
	}

	public void run() {
		// TODO Auto-generated method stub
		// actualization(map);
		while (true) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			dashboardPanel.repaint();
		}
	}
}