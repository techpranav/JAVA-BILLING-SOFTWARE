import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


public class test extends JFrame {
	
	static JPanel panel;
	private JPanel contentPane;
	static double width,height;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					width = screenSize.getWidth()+500;
					height = screenSize.getHeight()+500;
					
					GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					GraphicsDevice gs = ge.getDefaultScreenDevice();
			        GraphicsConfiguration gc = gs.getDefaultConfiguration();
					test frame = new test(gc);
					//Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setSize((int)width,(int)height);
						frame.setResizable(false);
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
						
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public test(GraphicsConfiguration gc) {
		super("myao",gc);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setBounds(236, 236, (int)width, (int)height);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
	}

}
