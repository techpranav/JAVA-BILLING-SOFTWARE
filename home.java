import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ButtonUI;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class home extends JFrame {

	 AbstractBorder brdr1 = new TextBubbleBorder(Color.red,8,16,16);
     AbstractBorder brdr2= new TextBubbleBorder(Color.GREEN,8,16,16,false);
     AbstractBorder brdr3= new TextBubbleBorder(Color.blue,8,16,16);
     AbstractBorder brdr4= new TextBubbleBorder(Color.cyan,8,16,16,false);
     AbstractBorder brdr0= new TextBubbleBorder(Color.magenta,8,16,16,false);

	private JPanel contentPane;
	private static double width;
	private static double height;
	stock stk ;
	payments payment;
	history hst;
	reports rep;
	
	static home frame =new home();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame=new home();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					//Dimension windowSize = Window.getSize();

					width = screenSize.getWidth();
					height = screenSize.getHeight();
					//int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
	//				int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);

//					frame.setLocation(windowX, windowY);  
					
					frame.setSize((int)width,(int)height);
					frame.setResizable(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);  // Explicit JFrame if outside JFrame constructor.

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public home() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize1.getWidth();
		height = screenSize1.getHeight();
		
		
		setBounds(100, 100, (int)width, (int)height);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 153));
		panel.setBounds(-40, -17, (int)width,(int)height);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnProducts = new JButton("Products");
		btnProducts.setForeground(new Color(153, 0, 0));
		btnProducts.setBackground(new Color(204, 255, 51));
		btnProducts.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnProducts.setBorder(brdr0);
		//btnProducts.setBackground(new Color(0,51,204));//import java.awt.Color;
		//btnProducts.setForeground(Color.WHITE);
		btnProducts.setFocusPainted(false);
		//btnProducts.setBorderPainted(false);
//		btnProducts.setUI(new ButtonUI(Color.RED, Color.ORANGE, Color.GREEN));

		btnProducts.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {


			stk=new stock();
					
			stk.setVisible(true);
			stk.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			//frame.setVisible(false);
			dispose();
		//	stk.setUndecorated(true);
		//	frame.setVisible(false);				
			
			
			}
		});
		btnProducts.setBounds(118, 75, 195, 112);
		panel.add(btnProducts);
		
		JButton btnPayments = new JButton("Payments");
		btnPayments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				payment=new payments();
				
				payment.setVisible(true);
				payment.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//frame.setVisible(false);
				dispose();
			//	stk.setUndecorated(true);
				frame.setVisible(false);				
			}
		});
		btnPayments.setForeground(new Color(153, 0, 0));
		btnPayments.setBackground(new Color(204, 255, 51));
		btnPayments.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPayments.setBounds(118, 261, 205, 112);
		btnPayments.setBorder(brdr2);
		panel.add(btnPayments);
		
		JButton btnHistory = new JButton("History");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hst=new history();
				
				hst.setVisible(true);
				hst.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//frame.setVisible(false);
				dispose();
			//	stk.setUndecorated(true);
				frame.setVisible(false);				
				
			
			}
		});
		btnHistory.setForeground(new Color(153, 0, 0));
		btnHistory.setBackground(new Color(204, 255, 51));
		btnHistory.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHistory.setBounds(118, 459, 205, 112);
		btnHistory.setBorder(brdr3);
		panel.add(btnHistory);
		
		JButton btnReports = new JButton("Reports");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			
				rep=new reports();
				
				rep.setVisible(true);
				rep.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//frame.setVisible(false);
				dispose();
			//	stk.setUndecorated(true);
				frame.setVisible(false);				
				
			
				
			}
		});
		btnReports.setForeground(new Color(153, 0, 0));
		btnReports.setBackground(new Color(204, 255, 51));
		btnReports.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReports.setBounds(118, 635, 195, 112);
		btnReports.setBorder(brdr4);
		panel.add(btnReports);
	
	//=============================================================
		
	}

}
