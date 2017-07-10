//package javaGUI;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import javaGUI.sqlconnection;

import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;

import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import net.miginfocom.swing.MigLayout;


public class login {

	JFrame frmLogIn;

	 AbstractBorder brdrLeft = new TextBubbleBorder(Color.red,8,16,16);
     AbstractBorder brdrRight = new TextBubbleBorder(Color.GREEN,6,16,16,false);

     JPanel panelMsg,panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					login window = new login();
					window.frmLogIn.setVisible(true);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double width = screenSize.getWidth();
					double height = screenSize.getHeight();
					window.frmLogIn.setSize((int)width,(int)height);
					window.frmLogIn.setResizable(true);
					window.frmLogIn.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		);
	}

	Connection connection =null;
	private JTextField userField;
	private JPasswordField passwordField;
	private static double  width,height;
	private JPasswordField textFieldPassword1;
	private JTextField textFieldAge;
	private JTextField textFieldName;
	private JPasswordField textFieldUsername;
	private JTextField textFieldMobile;
	private JTextField textFieldSurname;
	private JPasswordField textFieldPassword;
	/**
	 * Create the application.
	 */

	public login() {
		initialize();
		connection=sqlconnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//####################################INSERT FUNCTION################################################################
		public void insfun()
		{
			try{
				String query="insert into users (Name,Surname,Username,Password,Age,Mobile) values (?,?,?,?,?,?)";
				
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setString(1,textFieldName.getText());
				pst.setString(2,textFieldSurname.getText());
				pst.setString(3,textFieldUsername.getText());
				pst.setString(4,textFieldPassword.getText());
				int count=textFieldPassword.getPassword().length;
				pst.setString(5,textFieldAge.getText());
				pst.setString(6,textFieldMobile.getText());
				
				if((textFieldSurname.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Surname is missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				else if((textFieldName.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}

				else if((textFieldUsername.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Username is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldPassword.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Password is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					} 
				else if((textFieldPassword1.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Confirm Password before Sign Up", "Failure", JOptionPane.ERROR_MESSAGE);
				} 

				else if((textFieldMobile.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Mobile Number is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				else if((textFieldAge.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Age is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
				}
				else if(count<8)
				{
				JOptionPane.showMessageDialog(null, 
						  "Password should contain atleast 8 characters", "Failure", JOptionPane.ERROR_MESSAGE);
				}
				
				else
				{
					
					if((textFieldPassword.getText()).equals(textFieldPassword1.getText()))
						{
						
						pst.execute();
						JOptionPane.showMessageDialog(null, 
								   "Signed Up Successfully...!","SUCCESS" ,JOptionPane.PLAIN_MESSAGE);
												
						}
						else
					{
						JOptionPane.showMessageDialog(null, 
								  "Password not matched ", "Failure", JOptionPane.ERROR_MESSAGE);
						
					}
					pst.close();
				}
				//JOptionPane.showMessageDialog(null,"Data Saved..........!");

				pst.close();
				
			}
			catch(Exception R2){
				JOptionPane.showMessageDialog(null, 
						R2.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
				R2.printStackTrace();
			}

			
		}

	private void initialize() {
		frmLogIn = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		frmLogIn.setBounds(100, 100, (int)width, (int)height);
		
		frmLogIn.setSize((int)width,(int)height);
		frmLogIn.setResizable(true);
		frmLogIn.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frmLogIn.setTitle("Authentication");
		frmLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		
		Timer timer1=new Timer(30, null);
		timer1.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				if(panelMsg.getHeight() != 105){
					
					panelMsg.setBounds(0,0,frmLogIn.getSize().width,100+5);
					if(panelMsg.getHeight() == 105){
						timer1.stop();
					}
				}
				
			}

	
	});
			
		Timer timer2=new Timer(30, null);
		timer2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e)
			{
				if(panelMsg.getHeight() != 0){
					
					panelMsg.setBounds(0,0,frmLogIn.getSize().width,panelMsg.getHeight() - 5);
					if(panelMsg.getHeight() == 0){
						timer2.stop();
					}
				}
				
			}

	
	});
		
	//Image img3=new ImageIcon(this.getClass().getResource("/ok2.png")).getImage();
		
		//=====================================================================================================
		//Image img=new ImageIcon(this.getClass().getResource("/pc.png")).getImage();
		//Image img1=new ImageIcon(this.getClass().getResource("/login.png")).getImage();
	
		JPanel panelhead = new JPanel();
		panelhead.setToolTipText("DarkShadow Mart");
		panelhead.setBackground(new Color(255, 255, 0));

		panelhead.setVisible(false);
		
				panel = new JPanel();
				panel.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 0, 0), new Color(0, 255, 0), new Color(255, 0, 255), new Color(0, 0, 255)), new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 0, 0), new Color(0, 255, 0), new Color(255, 0, 255), new Color(0, 0, 255)), null)));
				panel.setBackground(new Color(153, 255, 153));
				panel.setVisible(false);
										frmLogIn.getContentPane().setLayout(new MigLayout("", "[1700px]", "[60px][960px]"));
				//=====================================================================================================
			
										JPanel panel_1 = new JPanel();
										panel_1.setBackground(new Color(127, 255, 212));
										frmLogIn.getContentPane().add(panel_1, "cell 0 0 1 2,grow");
										panel_1.setLayout(new MigLayout("", "[663px][219px][818px]", "[517px][70px][50px][60px][50px][40px][173px]"));
										
										JLabel label = new JLabel("TO");
										label.setHorizontalAlignment(SwingConstants.CENTER);
										label.setForeground(new Color(178, 34, 34));
										label.setFont(new Font("Imprint MT Shadow", Font.BOLD, 60));
										panel_1.add(label, "cell 2 2,grow");
										
										JLabel label_1 = new JLabel("DARKSHADOW MART");
										label_1.setHorizontalAlignment(SwingConstants.CENTER);
										label_1.setForeground(new Color(178, 34, 34));
										label_1.setFont(new Font("Imprint MT Shadow", Font.BOLD, 60));
										panel_1.add(label_1, "cell 0 4 3 1,alignx right,growy");
										
										JLabel label_11 = new JLabel("WELCOME");
										label_11.setHorizontalAlignment(SwingConstants.CENTER);
										label_11.setForeground(new Color(178, 34, 34));
										label_11.setFont(new Font("Imprint MT Shadow", Font.BOLD, 60));
										panel_1.add(label_11, "cell 2 0,alignx left,aligny bottom");
										
											
											JButton button = new JButton("ENTER ");
											button.setBorder(brdrRight);
											
		button.setFont(new Font("Segoe Print", Font.BOLD, 30));
		button.setForeground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				
				panel_1.setVisible(false);
				panelhead.setVisible(true);
				

			}
		});
		button.setBackground(new Color(51, 51, 51));
		panel_1.add(button, "cell 0 6,alignx right,aligny top");
		
		JPanel panel_2 = new JPanel();
		
		
		
		
		
		panel_2.setBackground(new Color(127, 255, 212));
		panel_1.add(panel_2, "cell 0 0 3 7,grow");
		panel_2.setLayout(new MigLayout("", "[566px]", "[320px]"));
		//Image img5=new ImageIcon(this.getClass().getResource("/store1.jpg")).getImage();

		JLabel label_2 = new JLabel("");
		label_2.setForeground(Color.BLACK);
		label_2.setBackground(new Color(240, 230, 140));
		//label_2.setIcon(new ImageIcon(img5));
			
			//===================================================================================================
			//Border border = BorderFactory.createLineBorder(Color.RED, 5);
		
			//label_2.setBorder(border);
			label_2.setBorder(brdrLeft);
			panel_2.add(label_2, "cell 0 0,grow");
				frmLogIn.getContentPane().add(panel, "cell 0 1,grow");
				panel.setLayout(new MigLayout("", "[602px][200px][256px][94px][548px]", "[1px][67px][568px]"));
				panelMsg = new JPanel();
				panelMsg.setBackground(Color.BLUE);
				panel.add(panelMsg, "cell 0 0 5 1,growx,aligny center");
				panelMsg.setLayout(null);
				
				
				JLabel labelmsg = new JLabel("");
				labelmsg.setFont(new Font("Tahoma", Font.BOLD, 16));
				labelmsg.setBounds(29, 33, (int)width, 82);
				panelMsg.add(labelmsg);
				labelmsg.setBorder(brdrRight);
				JButton btnOk = new JButton("Ok");
				btnOk.setFont(new Font("Tahoma", Font.BOLD, 15));
//				btnOk.setIcon(new ImageIcon(img3));
				//=====================================================================================================
				
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						timer1.start();
						
							panelMsg.setBounds(0,0,frmLogIn.getSize().width,0);
							timer1.stop();
					}
				});
				btnOk.setBounds(693, 48, 91, 41);
				panelMsg.add(btnOk);
				JLabel labelpic = 	new JLabel("");
						panel.add(labelpic, "cell 2 2,growx,aligny bottom");
						
						JPanel panel_3 = new JPanel();
						panel_3.setBackground(new Color(153, 255, 153));
						panel.add(panel_3, "cell 4 2,grow");
						panel_3.setLayout(null);
						
						JLabel lblUsername_1 = new JLabel("Username");
						lblUsername_1.setBounds(53, 35, 122, 27);
						panel_3.add(lblUsername_1);
						lblUsername_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
						
						userField = new JTextField();
						userField.setBounds(175, 38, 241, 22);
						panel_3.add(userField);
						userField.setColumns(10);
						
						passwordField = new JPasswordField();
						passwordField.setBounds(175, 90, 241, 22);
						panel_3.add(passwordField);
						
						JLabel lblPassword_1 = new JLabel("Password");
						lblPassword_1.setBounds(53, 87, 122, 27);
						panel_3.add(lblPassword_1);
						lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
						
						JButton btnLogIn_1 = new JButton("Log In");
						btnLogIn_1.setBounds(104, 207, 147, 45);
						panel_3.add(btnLogIn_1);
						btnLogIn_1.setFont(new Font("Tahoma", Font.BOLD, 15));
						//btnLogIn_1.setIcon(new ImageIcon(img1));
						
						JCheckBox chckbxShowPassword = new JCheckBox("Show password");
						chckbxShowPassword.setBounds(185, 123, 144, 25);
						panel_3.add(chckbxShowPassword);
						chckbxShowPassword.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent arg0) {
							
								if(chckbxShowPassword.isSelected())
									passwordField.setEchoChar((char)0);
								else
									passwordField.setEchoChar('*');
							
							}
							
							
						});
						chckbxShowPassword.setBackground(Color.LIGHT_GRAY);
						
						JButton btnCancel = new JButton("Cancel");
						btnCancel.setBounds(280, 207, 122, 45);
						panel_3.add(btnCancel);
						btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
						
						JPanel panelAcc = new JPanel();
						panelAcc.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(204, 255, 0), new Color(153, 0, 51), new Color(51, 0, 102), new Color(255, 102, 0)));
						panel.add(panelAcc, "cell 0 2,grow");
						panelAcc.setLayout(null);
						panelAcc.setVisible(false);
						

						JButton btnAddAccount = new JButton("ADD ACCOUNT");
						btnAddAccount.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								panelAcc.setVisible(true);
							}
						});
						btnAddAccount.setFont(new Font("Tahoma", Font.BOLD, 20));
						panel.add(btnAddAccount, "cell 0 1,alignx center,growy");
						
						JLabel lblCreatePassword = new JLabel("Create Password");
						lblCreatePassword.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblCreatePassword.setBounds(43, 149, 171, 27);
						panelAcc.add(lblCreatePassword);
						
						textFieldPassword1 = new JPasswordField();
						textFieldPassword1.setBackground(new Color(204, 102, 204));
						textFieldPassword1.setBounds(263, 192, 241, 22);
						panelAcc.add(textFieldPassword1);
						
						JLabel lblConfirmPassword = new JLabel("Confirm Password");
						lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblConfirmPassword.setBounds(43, 189, 171, 27);
						panelAcc.add(lblConfirmPassword);
						
						JLabel lblAge = new JLabel("Age");
						lblAge.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblAge.setBounds(43, 231, 171, 27);
						panelAcc.add(lblAge);
						
						textFieldAge = new JTextField();
						textFieldAge.setBackground(new Color(204, 102, 204));
						textFieldAge.setColumns(10);
						textFieldAge.setBounds(263, 231, 241, 22);
						panelAcc.add(textFieldAge);
						
						JLabel lblName = new JLabel("Name");
						lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblName.setBounds(43, 32, 171, 27);
						panelAcc.add(lblName);
						
						textFieldName = new JTextField();
						textFieldName.setBackground(new Color(204, 102, 204));
						textFieldName.setColumns(10);
						textFieldName.setBounds(263, 32, 241, 22);
						panelAcc.add(textFieldName);
						
						textFieldUsername = new JPasswordField();
						textFieldUsername.setBackground(new Color(204, 102, 204));
						textFieldUsername.setBounds(263, 109, 241, 22);
						panelAcc.add(textFieldUsername);
						
						JLabel lblUsername = new JLabel("Create Username");
						lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblUsername.setBounds(43, 109, 171, 27);
						panelAcc.add(lblUsername);
						
						JLabel lblMobile = new JLabel("Mobile");
						lblMobile.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblMobile.setBounds(43, 273, 171, 27);
						panelAcc.add(lblMobile);
						

						textFieldMobile = new JTextField();
						textFieldMobile.setBackground(new Color(204, 102, 204));
						textFieldMobile.setColumns(10);
						textFieldMobile.setBounds(263, 273, 241, 22);
						panelAcc.add(textFieldMobile);
						
						JLabel lblSurname = new JLabel("Surname");
						lblSurname.setFont(new Font("Tahoma", Font.BOLD, 15));
						lblSurname.setBounds(43, 72, 171, 27);
						panelAcc.add(lblSurname);
						
						textFieldSurname = new JTextField();
						textFieldSurname.setBackground(new Color(204, 102, 204));
						textFieldSurname.setColumns(10);
						textFieldSurname.setBounds(263, 72, 241, 22);
						panelAcc.add(textFieldSurname);
						
						JButton btnSignUp = new JButton("Sign Up");
						btnSignUp.setBackground(new Color(255, 153, 102));
						btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
						btnSignUp.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								insfun();
							}
						});
						btnSignUp.setBounds(165, 341, 112, 40);
						panelAcc.add(btnSignUp);
						
						textFieldPassword = new JPasswordField();
						textFieldPassword.setBackground(new Color(204, 102, 204));
						textFieldPassword.setBounds(263, 149, 241, 22);
						panelAcc.add(textFieldPassword);
						btnCancel.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								panel.setVisible(false);
								panel_1.setVisible(true);
								panelhead.setVisible(false);
							}
						});
						
							btnLogIn_1.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									try{
										labelmsg.setText("Duplicate Username and Password ");
										labelmsg.setForeground(Color.red);	
										timer1.start();
									
										String query="select * from users where Username=? and Password=?";
										PreparedStatement pst=connection.prepareStatement(query);
										pst.setString(1, userField.getText());
										pst.setString(2, passwordField.getText());
										ResultSet rs=pst.executeQuery();
										int count=0; 
										while(rs.next())
											count=count+1;
										rs.close();
										pst.close();
										if(count==1)
										{
											labelmsg.setText("Username and Password is correct");
											labelmsg.setForeground(Color.green);	
											timer1.start();
										/*	TrivialJob job = new TrivialJob();
							   			    job.schedule(5000);
											*/
											/*	
											 * 
											 * stock stk=new stock();
											stk.setVisible(true);
											 */
											frmLogIn.dispose();
											home hm=new home();
											hm.setVisible(true);
										}
										else if(count>1)
										{
											labelmsg.setText("Duplicate Username and Password ");
											labelmsg.setForeground(Color.red);	
											timer1.start();
										
										}
										else
										{
											labelmsg.setText("Username and Password is incorrect");
											labelmsg.setForeground(Color.red);	
											timer1.start();
										
										}	
										rs.close();
										pst.close();
									
									}
									catch(Exception e)
									{
										JOptionPane.showMessageDialog(null,e);

									}
								
								} 
							});
		frmLogIn.getContentPane().add(panelhead, "cell 0 0,grow");
		    Graphics2D g2d;
		panelhead.setLayout(new MigLayout("", "[648px]", "[60px]"));

		JLabel lblDarkshadowMart = new JLabel("DarkShadow Mart");
		lblDarkshadowMart.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarkshadowMart.setForeground(new Color(153, 0, 102));
		lblDarkshadowMart.setFont(new Font("Agency FB", Font.BOLD, 36));
		panelhead.add(lblDarkshadowMart, "cell 0 0,grow");

		//===================================================================================================
		/*
		JLabel label_3 = new JLabel("WELCOME ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(178, 34, 34));
		label_3.setFont(new Font("Imprint MT Shadow", Font.BOLD, 40));
		label_3.setBounds(400, 500, 600, 116);
		panel_1.add(label_3);
		*/
	}
}
