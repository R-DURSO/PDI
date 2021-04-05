package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import data.GuiData;

public class MainPanel extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	private ControlPanel controlPanel;	
	private static DashboardPanel dashboardPanel;
	private final static Dimension preferredSize = new Dimension(GuiData.WINDOW_WIDTH, GuiData.WINDOW_HEIGHT);
	private final static Dimension dashboardSize = new Dimension(GuiData.DASHBOARD_PANEL_WIDTH,GuiData.DASHBOARD_PANEL_HEIGHT);
	private final static Dimension controlPanelSize = new Dimension(GuiData.CONTROL_PANEL_WIDTH,GuiData.CONTROL_PANEL_HEIGHT);
	private Container contentPane;
	public MainPanel(String title) {
		super(title);
		this.setPreferredSize(preferredSize);

		init();
	}

	private void init() {
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		dashboardPanel = new DashboardPanel();
		controlPanel = new ControlPanel();
		
		dashboardPanel.setPreferredSize(dashboardSize);
		controlPanel.setPreferredSize(controlPanelSize);
		
		contentPane.add(dashboardPanel, BorderLayout.NORTH);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setPreferredSize(preferredSize);
		setVisible(true);
		setResizable(false);

	}
	@Override	public void run() {
        // TODO Auto-generated method stub
        // actualization(map);
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            dashboardPanel.revalidate();
            // dashboardPanel.repaint();
        }
    }
	
	public static void creatTasksDonePanel() {
		removePanel();
		dashboardPanel.creatTasksDonePanel();
		
	}
	
	public static void creatWagesInfosPanel() {
		removePanel();
		dashboardPanel.creatWagesInfosPanel();

	}
	
	public static void creatLeaveUsagePanel() {
		removePanel();
		dashboardPanel.creatLeaveUsagePanel();

	}
	
	public static void creatMonthEmpPanel() {
		removePanel();
		dashboardPanel.creatMonthEmpPanel();

	}
	
	public static void creatExpensiveEmpPanel() {
		removePanel();
		dashboardPanel.creatExpensiveEmpPanel();

	}
	
	public static void creatFormationUtilityPanel() {
		removePanel();
		dashboardPanel.creatFormationUtilityPanel();

	}
	
	public static void creatResBySeniorityPanel() {
		removePanel();
		dashboardPanel.creatResBySeniorityPanel();

	}
	
	public static void creatTypeContractPanel() {
		removePanel();
		dashboardPanel.creatTypeContractPanel();

	}
	
	public static void creatEmployCostPanel() {
		removePanel();
		dashboardPanel.creatEmployCostPanel();

	}
	
	public static void creatSalGradesPanel() {
		removePanel();
		dashboardPanel.creatSalGradesPanel();

	}
	
	public static void creatPayPanel() {
		removePanel();
		dashboardPanel.creatPayPanel();

	}
	public static void removePanel() {
		dashboardPanel.removeAll();
	}
}