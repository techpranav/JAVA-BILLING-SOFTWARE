import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class entry extends JFrame {

	private JPanel contentPane;
	static entry frame;
	static double width,height;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new entry();
					frame.setVisible(true);
					  Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						width = screenSize.getWidth();
						height = screenSize.getHeight();
						frame.setSize((int)width,(int)height);
						frame.setResizable(true);
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public entry() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1159, 1110);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(144, 238, 144));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(148, 0, 211), new Color(220, 20, 60), new Color(85, 107, 47), new Color(255, 215, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1920px]", "[1080px]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[398px][657px][140px]", "[179px][86px][116px][116px][360px]"));
		
		JLabel lblTo = new JLabel("TO");
		lblTo.setForeground(new Color(128, 0, 0));
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Imprint MT Shadow", Font.BOLD, 45));
		panel.add(lblTo, "cell 1 2,alignx center,growy");
		
		JLabel lblDarkshadowMart = new JLabel("DARKSHADOW MART");
		lblDarkshadowMart.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarkshadowMart.setForeground(new Color(178, 34, 34));
		lblDarkshadowMart.setFont(new Font("Imprint MT Shadow", Font.BOLD, 45));
		panel.add(lblDarkshadowMart, "cell 1 3,alignx right,growy");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(144, 238, 144));
		panel.add(panel_1, "cell 0 0,grow");
		panel_1.setLayout(new MigLayout("", "[374px]", "[153px]"));
		
		JLabel label_1 = new JLabel("");
		panel_1.add(label_1, "cell 0 0,grow");
		label_1.setBackground(new Color(144, 238, 144));
		label_1.setForeground(new Color(0, 0, 0));
		Image img=new ImageIcon(this.getClass().getResource("/images.jpg")).getImage();
		label_1.setIcon(new ImageIcon(img));
	
		JLabel label = new JLabel("WELCOME ");
		panel.add(label, "cell 1 0 1 3,growx,aligny center");
		label.setForeground(new Color(178, 34, 34));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Imprint MT Shadow", Font.BOLD, 45));
		
	
		
		JLabel label_3 = new JLabel("");
		label_3.setForeground(Color.BLACK);
		label_3.setBackground(new Color(240, 230, 140));
		Image img2=new ImageIcon(this.getClass().getResource("/1.png")).getImage();
		label_3.setIcon(new ImageIcon(img2));
		panel.add(label_3, "cell 1 4,alignx left,aligny top");
		label_3.setVisible(true);
	
		
		
		JButton btnEnter = new JButton("ENTER ");
		btnEnter.setForeground(Color.BLACK);
		btnEnter.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*frame.dispose();
				login lgn=new login();
				lgn.setVisible(true); */
				//frame.dispose();
				
				login lgn=new login();
				lgn.frmLogIn.setVisible(true);
			}
		});
		
		
		btnEnter.setBackground(new Color(0, 255, 255));
		panel.add(btnEnter, "cell 2 4,growx,aligny bottom");
	
	}
}
