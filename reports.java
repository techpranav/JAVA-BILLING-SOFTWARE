import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javaGUI.sqlconnection;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class reports extends JFrame {

	private static JPanel panelMain ;
	private JPanel contentPane;
	public static double width,height;
	private JPanel panelFooter;
	private JLabel lblProfit;
	private JLabel profitval;
	private JLabel lossval;
	private JLabel lblLoss;
	private JLabel lblClosingStock;
	static DateCombo datebox=new DateCombo();
	private JComboBox startYear,startMonth,startDate,endYear,endMonth,endDate;
	Autocomplete cmpltObj=new Autocomplete();
	private JLabel closingstk;
	private JLabel lblOpeningStock;
	private JLabel openingstk;
	private  String query1,query2,query3;
	PreparedStatement pst1,pst2,pst3;
	ResultSet rs1,rs2,rs3;
	
	int starty,startm,startd,endy,endm,endd;
	String sDate2,sDate1;
	String query;
	java.util.Date date2,date1;
	public java.sql.Date sqlDate1,sqlDate2;
	float closing_stk=0,tax=0,opening_stk=0,sale=0,bill_purchase=0,stk_purchase=0,abnormal=0,expense=0,discount_given=0,discount_receive=0,sale_return=0,purchase_return=0,profit=0,loss=0;
	PreparedStatement pst;
	ResultSet rs;
	int index=0;
	public java.sql.Date sqlDate,sqlDate11,sqlDate22;
	
	Connection connection=null;
	/**
	 * Launch the application.
	 */
	
	Date minDate,maxDate;
	public void boundaryDates()
	{
		try{
			query="select MIN(date) from profitfinder ;";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				minDate=rs2.getDate("date");
			System.out.println("minDate:"+ minDate);
			JOptionPane.showMessageDialog(null, "MIN DATE:"+minDate);
			}
			rs2.close();
			pst.close();
			query="select MAX(date) from profitfinder ;";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				maxDate=rs2.getDate("date");
			System.out.println("maxDate:"+ maxDate);
			JOptionPane.showMessageDialog(null, "MAX DATE:"+maxDate);
			}
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date checking error");
			e.printStackTrace();
		}
 		
	}
	public void dateIncrement(Date sqlDatett)
	{
		
		Calendar c = Calendar.getInstance();
		c.setTime(sqlDatett);
		System.out.println("sqlDatett :"+sqlDatett);
		c.add(Calendar.DATE,+1);  // number of days to add
		 sqlDate22 = new java.sql.Date(c.getTimeInMillis());  // dt is now the new date
	   }
	public void dateDecrement(Date sqlDatet)
	{
		
		Calendar c = Calendar.getInstance();
		c.setTime(sqlDatet);
		c.add(Calendar.DATE,-1);  // number of days to add
		 sqlDate11 = new java.sql.Date(c.getTimeInMillis());  // dt is now the new date
	   }
	
	public int checkDateO(Date sqlDatet1)
	{
	/*	Date date0=null;
		int exist=0;
		try{
			query="select * from profitfinder where date='"+sqlDatet1+"';";
		 	System.out.println("SQLDATET1 :"+sqlDatet1);
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			if(rs2.next())
			{
				sqlDate11=sqlDatet1;
			System.out.println("FOUND :"+ sqlDatet1);
			}else
			{
			dateDecrement(sqlDatet1);
			System.out.println("NOT FOUND :"+ sqlDatet1);
				
			checkDateO(sqlDate11);
			}
		
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date checking error");
			e.printStackTrace();
		}
 		System.out.println("Opening stock over");
*/
		 
		/*String query = "SELECT (count(*) > 0) as found FROM profitfinder WHERE date ='"+sqlDatet1+"'";
		try{
			pst = connection.prepareStatement(query);
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "chec error :"+e);
			e.printStackTrace();
		}
		try (ResultSet rs = pst.executeQuery()) {

			// 	Only expecting a single result
			if (rs.next()) {
				boolean found = rs.getBoolean(1); // "found" column
				if (found) {
					sqlDate11=sqlDatet1;
					System.out.println("FOUND :"+ sqlDatet1);
					    // You have rows
				} else {
					// You have no rows
					dateDecrement(sqlDatet1);
					System.out.println("NOT FOUND :"+ sqlDatet1);
						
					checkDateO(sqlDate11);
									
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Date checking error");
			e.printStackTrace();
		}
	
	*/
		boundaryDates();

			int ch=0;
			try{
				cmpltObj.date();
				
				String query="select date from profitfinder where date='"+sqlDatet1+"';";
				
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
				{
				ch=1;	
				}
				pst.close();
				rs.close();

				if(ch==1)
				{
					sqlDate11=sqlDatet1;
					System.out.println("FOUND :"+ sqlDatet1);
					
				}else
				{
					dateDecrement(sqlDatet1);
					System.out.println("NOT FOUND :"+ sqlDatet1);
					if(sqlDatet1.compareTo(minDate)>0)
						checkDateO(sqlDate11);
					
				}
			
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "ERROR IN CHRCKDATE :"+ e);
				e.printStackTrace();
			}
			
			return ch;
		
		

			

	}

	
	public void checkDateC(Date sqlDatet1)
	{
		/*Date date0=null;
		int exist=0;
		try{
		query="select * from profitfinder where date='"+sqlDatet1+"'";
		 
		pst=connection.prepareStatement(query);
		rs=pst.executeQuery();
		if(rs.next())
		{
			exist++;
			sqlDate22=sqlDatet1;
			 System.out.println("SQLDATET1 :"+sqlDate22);
					
		}else
		{
			index++;
			dateIncrement(sqlDatet1);
			 System.out.println("sQLDATE22  --"+sqlDate22);
				
			checkDateC(sqlDate22);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "checkDateC Error :"+e);
		}
		
		*/
		//=====================================================================================
		/*String query = "SELECT (count(*) > 0) as found FROM profitfinder WHERE date ='"+sqlDatet1+"'";
        PreparedStatement pst = connection.prepareStatement(query);

        try (ResultSet rs = pst.executeQuery()) {
            // Only expecting a single result
            if (rs.next()) {
                boolean found = rs.getBoolean(1); // "found" column
                if (found) {
                	exist++;
        			sqlDate22=sqlDatet1;
        			 System.out.println("exist SQLDATET1 :"+sqlDatet1);
        			        // You have rows
                } else {
                    // You have no rows
                	index++;
        			dateIncrement(sqlDatet1);
        			System.out.println("not EXIST sQLDATE22  --"+sqlDate22);
        			
        			checkDateC(sqlDate22);
        				
                }
            }
        }
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "Date checking error");
		e.printStackTrace();
	}*/
	 	
		//============================================================

		int ch=0;
		try{
			cmpltObj.date();
			
			String query="select date from profitfinder where date='"+sqlDatet1+"';";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			ch=1;	
			}
			pst.close();
			rs.close();

			if(ch==1)
			{
				sqlDate22=sqlDatet1;
        		System.out.println("FOUND :"+ sqlDatet1);
				
			}else
			{
				dateIncrement(sqlDatet1);
				System.out.println("NOT FOUNDdd :"+ sqlDatet1);
				if(maxDate.compareTo(sqlDate22)>0)
					checkDateO(sqlDate22);
			}
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR IN CHRCKDATE :"+ e);
			e.printStackTrace();
		}
		
	
		}

	
	
	public void calculateAll()
	 {
		// fillComboBoxD(dealersBox);
		closing_stk=0;
		tax=0;
		opening_stk=0;
		sale=0;
		bill_purchase=0;
		stk_purchase=0;
		abnormal=0;
		expense=0;
		discount_given=0;
		discount_receive=0;
		sale_return=0;
		purchase_return=0;
		profit=0;
		loss=0;
		 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);

		 Date d1=sqlDate1,d2=sqlDate2;
		 System.out.println("SQLDATE1 :"+sqlDate1);
		 System.out.println("SQLDATE2 :"+sqlDate2);
			try{

				
				
					query="select * from allrecord where date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
				 
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				while(rs.next())
				{
					discount_receive+=Float.parseFloat(rs.getString("discount_receive"));
					discount_given+=Float.parseFloat(rs.getString("discount_given"));
					expense+=Float.parseFloat(rs.getString("expense"));
					abnormal+=Float.parseFloat(rs.getString("abnormal"));
					sale_return+=Float.parseFloat(rs.getString("sale_return"));
					purchase_return+=Float.parseFloat(rs.getString("purchase_return"));
					
				}
				
				query="select * from profitfinder where date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
				 
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				while(rs.next())
				{
					tax+=Float.parseFloat(rs.getString("tax"));
					stk_purchase+=Float.parseFloat(rs.getString("stockpurchase"));
					bill_purchase+=Float.parseFloat(rs.getString("purchase_price"));
					sale+=Float.parseFloat(rs.getString("sale_price"));
					
				}
				
				checkDateO(sqlDate1);
				checkDateC(sqlDate2);
				 System.out.println("SQLDATE1 :"+sqlDate11);
				 System.out.println("SQLDATE2 :"+sqlDate22);
			
				 
				query="select * from profitfinder where date ='"+sqlDate11+"'";
				 
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				while(rs.next())
				{
					if(d1==sqlDate11)
						opening_stk=Float.parseFloat(rs.getString("opening_stk"));
					else
						opening_stk=Float.parseFloat(rs.getString("closing_stk"));
				}
				
				query="select * from profitfinder where date ='"+sqlDate22+"'";
				 
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				while(rs.next())
				{
					if(d1==sqlDate11)
						closing_stk=Float.parseFloat(rs.getString("closing_stk"));
					else
						closing_stk=Float.parseFloat(rs.getString("opening_stk"));
					
					
				}
				
				
				System.out.println("discount_receive:"+discount_receive);
				System.out.println("discount_given:"+discount_given);
				System.out.println("sale_return:"+sale_return);
				System.out.println("expense:"+expense);
				System.out.println("abnormal:"+abnormal);
				System.out.println("purchase_return:"+purchase_return);
				System.out.println("stk_purchase:"+stk_purchase);
				System.out.println("bill_purchase:"+bill_purchase);
				System.out.println("sale:"+sale);
				System.out.println("tax:"+tax);
				System.out.println("opening_stk:"+opening_stk);
				System.out.println("closing_stk:"+closing_stk);
				profit=closing_stk+sale+abnormal-opening_stk-stk_purchase-expense-discount_given+discount_receive+purchase_return-sale_return;
				loss= -profit;
				System.out.println("profit:"+profit);
				System.out.println("loss:"+loss);
				
				
				if(profit>0)
				{
					profitval.setText(Float.toString(profit));
					lossval.setText("NO LOSS...!");
				}else
				{
					profitval.setText("NO PROFIT...!");
					lossval.setText(Float.toString(loss));
				}
				
				openingstk.setText(Float.toString(opening_stk));
				closingstk.setText(Float.toString(closing_stk));
				
				
				pst.close();
				rs.close();
			}
			catch(Exception e){
				
				e.printStackTrace();
			}
			
		

	 }
	 
	//###################################  SET DURATION   ###########################################
	
	public void getPeriod(JComboBox startYear,JComboBox startMonth,JComboBox startDate,JComboBox endYear,JComboBox endMonth,JComboBox endDate)
	{
		starty=Integer.parseInt(startYear.getSelectedItem().toString());
//		startm=Integer.parseInt(startMonth.getSelectedItem());
		startm=Integer.parseInt(startMonth.getSelectedItem().toString());

	//	startm=Integer.parseInt(startMonth.getSelectedItem().toString());
		startd=Integer.parseInt((String)startDate.getSelectedItem());
		endy=Integer.parseInt(endYear.getSelectedItem().toString());
		endm=Integer.parseInt(endMonth.getSelectedItem().toString());
		endd=Integer.parseInt((String) endDate.getSelectedItem());
		sDate1=starty+"-"+startm+"-"+startd;
		try{ 
		date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
		 sDate2=endy+"-"+endm+"-"+endd;
		 date2=new SimpleDateFormat("yyyy-MM-dd").parse(sDate2);
		 
		 //==============================
		    sqlDate1 = new java.sql.Date(date1.getTime());
		    sqlDate2 = new java.sql.Date(date2.getTime());

			boundaryDates();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	

	
	//###################################  SET DURATION   ###########################################
	
	/*	public void setday()
		{

			int n;
			int year = Calendar.getInstance().get(Calendar.YEAR);
			String months[]=(new DateFormatSymbols()).getMonths();

			String weeks[]=(new DateFormatSymbols()).getWeekdays();

			
			if(year%4==0)
				n=366;
			else
				n=365;
			
			for(int i=1;i<=n;i++)
				dayBox.addItem(i);
			
			
			for(int i=0;i<12;i++)
				monthBox.addItem(months[i]);
			
			for(int i=1;i<8;i++)
				weekBox.addItem(weeks[i]);
			
			for(int i=2000;i<2041;i++)
				yearBox.addItem(i);
			
			
		}
		
		//###################################  daySelection    ###########################################
		
		public void selectDay()
		{
		}

		//###################################  monthSelection    ###########################################
		
		public void monthSelection()
		{
			int n=monthBox.getSelectedIndex();
			if(n==0)
			{
				query1="select closing_stk from allrecord where ";
			}
			
		}
		*/
		
	//#########################  MAIN  FUNCTION ######################################################
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						width = screenSize.getWidth();
						height = screenSize.getHeight();
						reports frame = new reports();

						frame.setVisible(true);
						frame.setSize((int)width,(int)height);
						frame.setResizable(true);
						frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//				frame.getRootPane().setDefaultButton(btnSave);
						panelMain.setBounds(0, 0, (int)width, (int)height);
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
	public reports() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		connection=sqlconnection.dbConnector();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1353, 1160);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMain = new JPanel();
		panelMain.setBounds(0, 0, (int)width, (int)height);
		contentPane.add(panelMain);
		panelMain.setLayout(null);
		
		JPanel panelHead = new JPanel();
		panelHead.setBackground(new Color(189, 183, 107));
		panelHead.setBounds(0, 0, 1920, 180);
		panelMain.add(panelHead);
		panelHead.setLayout(null);
		
		endYear = new JComboBox();
		endYear.setBounds(818, 117, 61, 22);
		panelHead.add(endYear);
		
		endMonth = new JComboBox();
		endMonth.setBounds(899, 117, 61, 22);
		panelHead.add(endMonth);
		
		endDate = new JComboBox();
		endDate.setBounds(992, 117, 61, 22);
		panelHead.add(endDate);
		
		startYear = new JComboBox();
		startYear.setBounds(168, 117, 61, 22);
		panelHead.add(startYear);
		
		startMonth = new JComboBox();
		startMonth.setBounds(254, 117, 61, 22);
		panelHead.add(startMonth);
		
		startDate = new JComboBox();
		startDate.setBounds(342, 117, 61, 22);
		panelHead.add(startDate);
		
		JLabel label_1 = new JLabel("YEAR");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(168, 74, 56, 30);
		panelHead.add(label_1);
		
		JLabel label_2 = new JLabel("MONTH");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(254, 81, 93, 16);
		panelHead.add(label_2);
		
		JLabel label_3 = new JLabel("DAY");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_3.setBounds(347, 81, 56, 16);
		panelHead.add(label_3);
		
		JLabel label_4 = new JLabel("YEAR");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_4.setBounds(818, 81, 56, 16);
		panelHead.add(label_4);
		
		JLabel label_5 = new JLabel("MONTH");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(904, 81, 93, 16);
		panelHead.add(label_5);
		
		JLabel label_7 = new JLabel("DAY");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_7.setBounds(997, 81, 56, 16);
		panelHead.add(label_7);
		
		panelFooter = new JPanel();
		panelFooter.setBackground(new Color(238, 232, 170));
		panelFooter.setBounds(0, 180, (int)width, (int)height-180);
		panelMain.add(panelFooter);
		panelFooter.setLayout(null);
		
		lblProfit = new JLabel("Profit                 :");
		lblProfit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProfit.setBounds(70, 284, 167, 25);
		panelFooter.add(lblProfit);
		
		profitval = new JLabel("");
		profitval.setFont(new Font("Tahoma", Font.BOLD, 20));
		profitval.setBounds(255, 284, 167, 25);
		panelFooter.add(profitval);
		
		lossval = new JLabel("");
		lossval.setFont(new Font("Tahoma", Font.BOLD, 20));
		lossval.setBounds(255, 362, 167, 25);
		panelFooter.add(lossval);
		
		lblLoss = new JLabel("Loss                   :");
		lblLoss.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLoss.setBounds(70, 362, 184, 25);
		panelFooter.add(lblLoss);
		
		lblClosingStock = new JLabel("Closing Stock    :");
		lblClosingStock.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblClosingStock.setBounds(70, 205, 173, 25);
		panelFooter.add(lblClosingStock);
		
		closingstk = new JLabel("");
		closingstk.setFont(new Font("Tahoma", Font.BOLD, 20));
		closingstk.setBounds(255, 205, 167, 25);
		panelFooter.add(closingstk);
		
		lblOpeningStock = new JLabel("Opening Stock  :");
		lblOpeningStock.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOpeningStock.setBounds(70, 127, 184, 25);
		panelFooter.add(lblOpeningStock);
		
		openingstk = new JLabel("");
		openingstk.setFont(new Font("Tahoma", Font.BOLD, 20));
		openingstk.setBounds(255, 127, 167, 25);
		panelFooter.add(openingstk);
		
		JButton SHOW = new JButton("SHOW");
		SHOW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculateAll();
						
			}
		});
		SHOW.setFont(new Font("Tahoma", Font.BOLD, 18));
		SHOW.setBounds(377, 546, 97, 36);
		panelFooter.add(SHOW);
	
		datebox.historyComponents(startYear,startMonth,startDate,endYear,endMonth,endDate);

		datebox.set();
		
		//setduration();
		//setday();
	}
}




