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
		run();
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
		/*BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("H:\\Desktop\\png-transparent-business-professional-services-computer-icons-dynamics-365-business.png"));
			setIconImage(myPicture);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
			repaint();
		}
	}
	
	public static void creatTasksDonePanel() {
		dashboardPanel.creatTasksDonePanel();
	}
	
	public static void creatWagesInfosPanel() {
		dashboardPanel.creatWagesInfosPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatLeaveUsagePanel() {
		dashboardPanel.creatLeaveUsagePanel();
		dashboardPanel.repaint();
	}
	
	public static void creatMonthEmpPanel() {
		dashboardPanel.creatMonthEmpPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatExpensiveEmpPanel() {
		dashboardPanel.creatExpensiveEmpPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatFormationUtilityPanel() {
		dashboardPanel.creatFormationUtilityPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatResBySeniorityPanel() {
		dashboardPanel.creatResBySeniorityPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatTypeContractPanel() {
		dashboardPanel.creatTypeContractPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatEmployCostPanel() {
		dashboardPanel.creatEmployCostPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatSalGradesPanel() {
		dashboardPanel.creatSalGradesPanel();
		dashboardPanel.repaint();
	}
	
	public static void creatPayPanel() {
		dashboardPanel.creatPayPanel();
		dashboardPanel.repaint();
	}
	
}