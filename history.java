import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import javaGUI.sqlconnection;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class history extends JFrame {
	int sumT=0,sumP=0,sumR=0,pur=111;
	 home hm;
	 static history frame ;
	 //private JComboBox startYear=new JComboBox(),startMonth=new JComboBox(),startDate=new JComboBox(),endYear=new JComboBox(),endMonth=new JComboBox(),endDate=new JComboBox();
		
	private JComboBox startYear,startMonth,startDate,endYear,endMonth,endDate;
	private JPanel contentPane;
	public java.sql.Date sqlDate1,sqlDate2;
	int count = 0;
	int compare = 0;
	Object obj;
	String name = "";
	Connection connection=null;
	static JPanel panel;
	private static JScrollPane scrollPane ;
	static JPanel panel_1;
	public static double width,height;
	JTable table;
	JLabel lblTotal;
	PreparedStatement pst;
	ResultSet rs;
	int  padx;
	int starty,startm,startd,endy,endm,endd;
	String sDate2,sDate1;
	String query;
	java.util.Date date2,date1;
	static DateCombo datebox=new DateCombo();
	private JTextField textField1,textField2,textField3;
	private JLabel lblNewLabel;
	private JComboBox dealersBox,discountBox;
	private JLabel lblStart;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblYear_1;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	/**
	 * Launch the application.
	 */
	
	//getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate)
	
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//===========================FILL DEALERS COMBO BOX====================================
	
	public void fillComboBoxD(JComboBox st)
	{
		
		try{
			st.removeAllItems();
		

				query="select distinct dealer from stockdaily ;";
				
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			st.addItem("all");
			while(rs.next())
				st.addItem(rs.getString(1));
			
			pst.close();
			rs.close();
		}
		catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "ERROR :"+ e);
			e.printStackTrace();
		}

		
	}

	public void fillComboBoxC(JComboBox st)
	{	
		
		try{
		
			st.removeAllItems();

				query="select distinct name from allrecord where discount_receive !=0  OR discount_given !=  0 ;";
				
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				st.addItem(rs.getString(1));
			
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		st.addItem("all");
		
	}

	//==============================SUM OF COLUMNS==================================
	  public int getSum(int columnIndex)
	  {
		  
		    int rowsCount = table.getRowCount();
	        int sum=0;
	        if(rowsCount !=0)
	        {
	        for(int i = 0; i < rowsCount; i++){
	        	{
	        	sum = sum+Integer.parseInt(table.getValueAt(i, columnIndex).toString());
	        	
	        	}
	        }
	        }
	        return sum;
	      
	  }
	//===============================SHOW STOCK==========================================
	
	 public void showStock()
	 {
		 lblNewLabel.setVisible(false);
			
		 try{
			 dealersBox.setVisible(false);
			 	query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from stock";
				
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				textField1.setVisible(true);
				textField2.setVisible(false);
				textField3.setVisible(false);
				lblTotal.setVisible(true);
				table.setModel(DbUtils.resultSetToTableModel(rs));
				int s=getSum(4);
				pst.close();
				rs.close();	

				int wid=((int)width-336)/5;
				
				padx=(wid*4)+50;
				textField1.setBounds(padx,(int)height-235, wid, 22);
				lblTotal.setBounds(padx-wid+100,(int)height-235, wid-100, 22);
				discountBox.setVisible(false);
				dealersBox.setVisible(false);
				textField1.setText(Integer.toString(s));
				
			}
			catch(Exception e){
				
				e.printStackTrace();
			}

	 }

		//===============================SHOW STOCK==========================================
		
	 public void showCustomer()
	 {


		 lblNewLabel.setVisible(false);
		 discountBox.setVisible(false);
			dealersBox.setVisible(false);
		 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);
			try{
				query="select ID,name,city,mobile,date,paid ,remaining,total from profit where date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
				//String query="select * from payments";

				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));

				sumP=getSum(5);
				sumR=getSum(6);
				sumT=getSum(7);
			    textField1.setText(Integer.toString(sumT));
			    textField2.setText(Integer.toString(sumR));
			    
			    textField3.setText(Integer.toString(sumP));
			    
				pst.close();
				rs.close();
			}
			catch(Exception e){
				
				e.printStackTrace();
			}


			textField1.setVisible(true);
			textField2.setVisible(true);
			textField3.setVisible(true);
			int wid=((int)width-336)/8;
			
			padx=(wid*7)+50;
			int t=1;
			textField1.setBounds(padx,(int)height-235, wid, 22);
			textField2.setBounds(padx-wid*t,(int)height-235, wid, 22);
			textField3.setBounds(padx-(wid*(t+1)),(int)height-235, wid, 22);
			lblTotal.setBounds(padx-(wid*(t+2))+100,(int)height-235, wid-100, 22);
			
			textField1.setText(Integer.toString(sumT));
			textField2.setText(Integer.toString(sumR));
			textField3.setText(Integer.toString(sumP));
				
		    
			

	 }
	 

	 
	 //==================================SHOW SELL==================================
	 public void showSell()
	 {

		 lblNewLabel.setVisible(false);
		 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);
		 discountBox.setVisible(false);
			dealersBox.setVisible(false);
			try{
					  
				 //=============================
				 JOptionPane.showMessageDialog(null,"START :"+ sqlDate1+" END : "+sqlDate2);
				 
				 
				//query="select ID,name,city,mobile,date,paid ,remaining,total from profit where MONTH(date)>= '"+startm+"' and YEAR(date)>='"+starty+"'and DATE(date)>='"+startd+"'";
				//String query="select * from payments";
				 query="select ID,name,city,mobile,date,paid ,remaining,total from record where date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
					
				 JOptionPane.showMessageDialog(null,"StartD :"+startd+ " StartM :"+startm+"StartY :"+starty+" EndD :"+endd+" EndM :"+endm+" EndY :"+endy);
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));

				

				textField1.setVisible(true);
				textField2.setVisible(true);
				textField3.setVisible(true);
				int wid=((int)width-336)/8;
			
				padx=(wid*7)+50;
				int t=1;
				textField1.setBounds(padx,(int)height-235, wid, 22);
				textField2.setBounds(padx-wid*t,(int)height-235, wid, 22);
				textField3.setBounds(padx-(wid*(t+1)),(int)height-235, wid, 22);
				lblTotal.setBounds(padx-(wid*(t+2))+100,(int)height-235, wid-100, 22);
				
					
				sumP=getSum(5);
				sumR=getSum(6);
				sumT=getSum(7);
			    textField1.setText(Integer.toString(sumT));
			    textField2.setText(Integer.toString(sumR));
			    
			    textField3.setText(Integer.toString(sumP));

				
				
				//getSum();
			    //textFieldTotalT.setText(Integer.toString(sumT));
			    //textFieldRemainingT.setText(Integer.toString(sumR));
			    
			    //textFieldPaidT.setText(Integer.toString(sumP));
			    
				pst.close();
				rs.close();
			}
			catch(Exception e){
				
				e.printStackTrace();
			}

	 }
	 

	 
	 //==================================SHOW PURCHASE==================================
	 public void showPurchase()
	 {
		// fillComboBoxD(dealersBox);
		 pur=1;

		 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);

		 lblNewLabel.setVisible(true);
		 discountBox.setVisible(false);
			dealersBox.setVisible(true);

			try{

				textField1.setVisible(true);
				textField2.setVisible(false);
				textField3.setVisible(false);
				
				
				 String checkDealer=(String)dealersBox.getSelectedItem();
				//query="select ID,name,city,mobile,date,paid ,remaining,total from profit where MONTH(date)>= '"+startm+"' and YEAR(date)>='"+starty+"'and DATE(date)>='"+startd+"'";
				//String query="select * from payments";
				if(checkDealer=="all")
					query="select dealer,name,date,quantity,purchase_price,total_purchase_price as total from stockdaily where date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
				else
					query="select dealer,name,date,quantity,purchase_price,total_purchase_price as total from stockdaily where dealer='"+checkDealer+"' and date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
				 
				pst=connection.prepareStatement(query);
				rs=pst.executeQuery();
				
				table.setModel(DbUtils.resultSetToTableModel(rs));
				int s=getSum(5);
				
			
				int wid=((int)width-336)/6;
				
				padx=(wid*5)+50;
				textField1.setBounds(padx,(int)height-235, wid, 22);
				lblTotal.setBounds(padx-wid+100,(int)height-235, wid-100, 22);
				
				textField1.setText(Integer.toString(s));
				lblTotal.setVisible(true);
				pst.close();
				rs.close();
			}
			catch(Exception e){
				
				e.printStackTrace();
			}
			
		

	 }
	 
	//===============================SHOW DISCOUNT==========================================
		
		 public void showDiscount()
		 {
			 pur=2;
			 discountBox.setVisible(true);
				dealersBox.setVisible(false);

				 lblNewLabel.setVisible(false);
			 try{
				 	//dealersBox.setVisible(false);
				 	if(discountBox.getSelectedItem()!="all")
				 		query="select ID,name,date,discount_receive,discount_given from allrecord where name='"+discountBox.getSelectedItem()+"'";
				 	else
				 		query="select ID,name,date,discount_receive,discount_given from allrecord where discount_receive !=0 OR discount_given !=0;";
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					textField1.setVisible(true);
					textField2.setVisible(true);
					textField3.setVisible(false);
					lblTotal.setVisible(true);
					table.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();	
					
					int wid=((int)width-336)/6;
					
					padx=(wid*5)+50;
					textField1.setBounds(padx,(int)height-235, wid, 22);					
					textField2.setBounds(padx-wid*1,(int)height-235, wid, 22);
					lblTotal.setBounds(padx-(wid*2)+100,(int)height-235, wid-100, 22);
					
						
					sumP=getSum(4);
					sumR=getSum(5);
				    textField1.setText(Integer.toString(sumR));
				    textField2.setText(Integer.toString(sumP));
				
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

		 }


		 //==================================SHOW EXPENSE==================================
		 public void showExpense()
		 {

			 lblNewLabel.setVisible(false);
			 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);
			 discountBox.setVisible(false);
				dealersBox.setVisible(false);
				try{
						  
					 //=============================
					 
					 
					//query="select ID,name,city,mobile,date,paid ,remaining,total from profit where MONTH(date)>= '"+startm+"' and YEAR(date)>='"+starty+"'and DATE(date)>='"+startd+"'";
					//String query="select * from payments";
					 query="select name,date,expense from allrecord where expense !=0 and date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
						
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

					

					textField1.setVisible(true);
					textField2.setVisible(false);
					textField3.setVisible(false);
					int wid=((int)width-336)/4;
				
					padx=(wid*3)+50;

					textField1.setBounds(padx,(int)height-235, wid, 22);
					textField2.setBounds(padx-wid,(int)height-235, wid, 22);
					lblTotal.setBounds(padx-(wid*2)+650,(int)height-235, wid-200, 22);
				
						
					sumT=getSum(2);
				    textField1.setText(Integer.toString(sumT));
				    
					
					
					//getSum();
				    //textFieldTotalT.setText(Integer.toString(sumT));
				    //textFieldRemainingT.setText(Integer.toString(sumR));
				    
				    //textFieldPaidT.setText(Integer.toString(sumP));
				    
					pst.close();
					rs.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

		 }
		 

		 //==================================SHOW RETURN==================================
		 public void showReturn()
		 {

			 lblNewLabel.setVisible(false);
			 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);
			 discountBox.setVisible(false);
				dealersBox.setVisible(false);
				try{
						  
					 //=============================
					 
					 
					//query="select ID,name,city,mobile,date,paid ,remaining,total from profit where MONTH(date)>= '"+startm+"' and YEAR(date)>='"+starty+"'and DATE(date)>='"+startd+"'";
					//String query="select * from payments";
					 query="select name,date,purchase_return,sale_return from allrecord where (purchase_return !=0 OR sale_return!=0) and date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
						
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

					

					textField1.setVisible(true);
					textField2.setVisible(true);
					textField3.setVisible(false);
					int wid=((int)width-336)/4;
				
					padx=(wid*3)+50;

					textField1.setBounds(padx,(int)height-235, wid, 22);
					textField2.setBounds(padx-wid*1,(int)height-235, wid, 22);
					lblTotal.setBounds(padx-(wid*2)+100,(int)height-235, wid-100, 22);
				
						
					sumT=getSum(3);

					sumP=getSum(2);
				    textField1.setText(Integer.toString(sumT));
				    textField2.setText(Integer.toString(sumP));
				    
					
					
					//getSum();
				    //textFieldTotalT.setText(Integer.toString(sumT));
				    //textFieldRemainingT.setText(Integer.toString(sumR));
				    
				    //textFieldPaidT.setText(Integer.toString(sumP));
				    
					pst.close();
					rs.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

		 }
		 

		 //==================================SHOW ABNORMAL==================================
		 public void showAbnormal()
		 {

			 lblNewLabel.setVisible(false);
			 getPeriod(startYear,startMonth,startDate,endYear,endMonth,endDate);
			 discountBox.setVisible(false);
				dealersBox.setVisible(false);
				try{
						  
					 //=============================
					 
					 
					//query="select ID,name,city,mobile,date,paid ,remaining,total from profit where MONTH(date)>= '"+startm+"' and YEAR(date)>='"+starty+"'and DATE(date)>='"+startd+"'";
					//String query="select * from payments";
					 query="select name,date,abnormal from allrecord where abnormal !=0 and date between '"+sqlDate1+"' and '"+sqlDate2+"' ORDER BY date DESC";
						
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

					

					textField1.setVisible(true);
					textField2.setVisible(false);
					textField3.setVisible(false);
					int wid=((int)width-336)/4;
				
					padx=(wid*3)+50;

					textField1.setBounds(padx,(int)height-235, wid, 22);
					textField2.setBounds(padx-wid,(int)height-235, wid, 22);
					lblTotal.setBounds(padx-(wid*2)+650,(int)height-235, wid-200, 22);
				
						
					sumT=getSum(2);
				    textField1.setText(Integer.toString(sumT));
				    
					
					
					//getSum();
				    //textFieldTotalT.setText(Integer.toString(sumT));
				    //textFieldRemainingT.setText(Integer.toString(sumR));
				    
				    //textFieldPaidT.setText(Integer.toString(sumP));
				    
					pst.close();
					rs.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

		 }
		 



	 //================================MAIN FUNCTION======================================
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					width = screenSize.getWidth();
					height = screenSize.getHeight();
					frame= new history();

					frame.setVisible(true);
					frame.setSize((int)width,(int)height);
					frame.setResizable(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//				frame.getRootPane().setDefaultButton(btnSave);

					panel.setBounds(0, 0, (int)width, 136);
					panel_1.setBounds(0, 137, (int)width, (int)height-140);
					scrollPane.setBounds(50, 65, (int)width-336, (int)height-300);
					datebox.set();
		//			frame.getRootPane().setDefaultButton(btnShow);
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
	public history() {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			width = screenSize.getWidth();
			height = screenSize.getHeight();
		
		
			
//			frame.getRootPane().setDefaultButton(btnShow);
		
		connection=sqlconnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1921, 879);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(144, 238, 144));
		panel.setBounds(0, 0, 1462, 136);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnShowStock = new JButton("SHOW STOCK");
		btnShowStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			showStock();
			}
		});
		btnShowStock.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowStock.setBackground(new Color(255, 160, 122));
		btnShowStock.setBounds(37, 60, 166, 25);
		panel.add(btnShowStock);
		
		JButton btnShowCustomer = new JButton("SHOW CUSTOMER");
		btnShowCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showCustomer();
			}
		});
		btnShowCustomer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowCustomer.setBackground(new Color(255, 160, 122));
		btnShowCustomer.setBounds(201, 60, 179, 25);
		panel.add(btnShowCustomer);
		
		JButton btnShowSale = new JButton("SHOW SALE");
		btnShowSale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showSell();
			}
		});
		btnShowSale.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowSale.setBackground(new Color(255, 160, 122));
		btnShowSale.setBounds(379, 60, 145, 25);
		panel.add(btnShowSale);
		
		JButton btnShowExpense = new JButton("SHOW EXPENSE");
		btnShowExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showExpense();
			}
		});
		btnShowExpense.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowExpense.setBackground(new Color(255, 160, 122));
		btnShowExpense.setBounds(891, 60, 191, 25);
		panel.add(btnShowExpense);
		
		JButton btnShowPurchase = new JButton("SHOW PURCHASE");
		btnShowPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPurchase();
			}
		});
		btnShowPurchase.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowPurchase.setBackground(new Color(255, 160, 122));
		btnShowPurchase.setBounds(523, 60, 179, 25);
		panel.add(btnShowPurchase);
		
		JButton btnShowDiscount = new JButton("SHOW DISCOUNT");
		btnShowDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showDiscount();
			}
		});
		btnShowDiscount.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowDiscount.setBackground(new Color(255, 160, 122));
		btnShowDiscount.setBounds(701, 60, 191, 25);
		panel.add(btnShowDiscount);
		
		JButton btnShowAbnormal = new JButton("SHOW ABNORMAL");
		btnShowAbnormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbnormal();
			}
		});
		btnShowAbnormal.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowAbnormal.setBackground(new Color(255, 160, 122));
		btnShowAbnormal.setBounds(1261, 60, 190, 25);
		panel.add(btnShowAbnormal);
		
		JButton btnShowReturn = new JButton("SHOW RETURN");
		btnShowReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			showReturn();
			}
		});
		btnShowReturn.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShowReturn.setBackground(new Color(255, 160, 122));
		btnShowReturn.setBounds(1081, 60, 179, 25);
		panel.add(btnShowReturn);
						
		//============================DATE==============================
		ArrayList<String> years_tmp = new ArrayList<String>();
        for(int years = 1980 ; years<=Calendar.getInstance().get(Calendar.YEAR);years++){
        	years_tmp.add(years+"");
       }

        
        panel_1 = new JPanel();
		panel_1.setBackground(new Color(127, 255, 212));
		panel_1.setBounds(0, 137, 1462, 626);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		//===============================SCROLL PANE =========================================================================================================================
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, new Color(153, 102, 204), new Color(153, 255, 0), new Color(255, 0, 0), new Color(255, 204, 102)));
		scrollPane.setToolTipText("STOCK");
		scrollPane.setBounds(50, 65, 1154, 539);
		panel_1.add(scrollPane);
		
		table = new JTable();

		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setRowHeight(25);
		
		table.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(table);
		
		textField1 = new JTextField();
		textField1.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField1.setForeground(new Color(0, 0, 0));
		textField1.setBackground(new Color(255, 255, 0));
		textField1.setBounds(padx ,(int)height-300, 187, 22);
		panel_1.add(textField1);
		textField1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField2.setForeground(new Color(0, 0, 0));
		textField2.setBackground(new Color(255, 255, 0));
		textField2.setBounds(padx ,(int)height-300, 187, 22);
		panel_1.add(textField2);
		textField1.setColumns(10);
	
		textField3 = new JTextField();
		textField3.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField3.setForeground(new Color(0, 0, 0));
		textField3.setBackground(new Color(255, 255, 0));
		textField3.setBounds(padx ,(int)height-300, 187, 22);
		panel_1.add(textField3);
		textField3.setColumns(10);
	
		
		lblTotal = new JLabel("TOTAL");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTotal.setBounds(942, 561, 75, 20);
		panel_1.add(lblTotal);
		textField1.setVisible(false);
		textField2.setVisible(false);
		textField3.setVisible(false);
		lblTotal.setVisible(false);
		panel.setBounds(0, 0, (int)width, 136);
		
		


		
		
		panel_1.setBounds(0, 137, (int)width, (int)height-140);
		scrollPane.setBounds(50, 23, (int)width-336, (int)height-300);
		
		endYear = new JComboBox();
		endYear.setBounds(1646, 217, 61, 22);
		panel_1.add(endYear);
		
		endMonth = new JComboBox();
		endMonth.setBounds(1732, 217, 61, 22);
		panel_1.add(endMonth);
		endDate = new JComboBox();
		endDate.setBounds(1820, 217, 61, 22);
		panel_1.add(endDate);
		
		startYear = new JComboBox();
		startYear.setBounds(1646, 125, 61, 22);
		panel_1.add(startYear);
		startMonth = new JComboBox();
		startMonth.setBounds(1732, 125, 61, 22);
		panel_1.add(startMonth);
		
		startDate = new JComboBox();
		startDate.setBounds(1820, 125, 61, 22);
		panel_1.add(startDate);
		datebox.historyComponents(startYear,startMonth,startDate,endYear,endMonth,endDate);
		
		lblStart = new JLabel("START");
		lblStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStart.setBounds(1732, 31, 56, 16);
		panel_1.add(lblStart);
		
		lblYear = new JLabel("YEAR");
		lblYear.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYear.setBounds(1646, 82, 56, 30);
		panel_1.add(lblYear);
		
		lblMonth = new JLabel("MONTH");
		lblMonth.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMonth.setBounds(1732, 89, 93, 16);
		panel_1.add(lblMonth);
		
		lblDay = new JLabel("DAY");
		lblDay.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDay.setBounds(1825, 89, 56, 16);
		panel_1.add(lblDay);
		
		lblYear_1 = new JLabel("YEAR");
		lblYear_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYear_1.setBounds(1646, 176, 56, 16);
		panel_1.add(lblYear_1);
		
		label = new JLabel("MONTH");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(1732, 176, 93, 16);
		panel_1.add(label);
		
		label_1 = new JLabel("DAY");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(1820, 90, 56, 16);
		panel_1.add(label_1);
		
		label_2 = new JLabel("DAY");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(1825, 177, 56, 16);
		panel_1.add(label_2);

        
        lblNewLabel = new JLabel("Dealers");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(199, 104, 81, 16);
		panel.add(lblNewLabel);
		
		discountBox = new JComboBox();
		dealersBox = new JComboBox();
		
		fillComboBoxC(discountBox);
		fillComboBoxD(dealersBox);
		
		discountBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					showDiscount();
				
			}
		});
		discountBox.setBounds(278, 102, 116, 22);
		panel.add(discountBox);
		
		dealersBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					showPurchase();
				
			}
		});
		
		dealersBox.setBounds(278, 102, 116, 22);
		panel.add(dealersBox);
		
				
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hm=new home();
				
				hm.setVisible(true);

				hm.setExtendedState(JFrame.MAXIMIZED_BOTH); 

				dispose();


			}
		});
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(1728, 518, 114, 51);
		panel_1.add(btnNewButton);
		discountBox.setVisible(false);
		dealersBox.setVisible(false);
		
		datebox.set();
		
	}
}
