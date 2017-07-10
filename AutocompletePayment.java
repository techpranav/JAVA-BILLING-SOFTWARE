import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javaGUI.sqlconnection;
import net.proteanit.sql.DbUtils;

public class AutocompletePayment {
	
	public ArrayList recordcustNameList,profitcustNameList,remainingList,paidList,totalList,mobileList,cityList;
	public ArrayList temp;
	public JTextField fld;
	public int ch;
	String query1;
	Autocomplete cmpltObj=new Autocomplete();
	PreparedStatement pst1;
	ResultSet rs1;
	int count;
	//#########################################################################
		public JTextField textFieldIdPayment;
		public JTextField textFieldTotalPayment;
		public JTextField textFieldcNamePayment;

		public JTextField textFieldcNamePaymentD;
		public JTextField textFieldcNamePaymentU,textFieldcNamePaymentS;
		public JTextField textFieldPaidPayment;
		public JTextField textFieldcMobilePayment ;
		public JTextField textFieldTRemainingPayment;
		public JTextField textFieldCityPayment;
		public JTextField textFieldDatePayment;

		float sale_price_table,purchase_price_table,stockpurchase,opening_stk,closing_stk,new_closing_stk,abnormal;	
		public JTextField textFieldAbnormalName,textFieldDiscountName,textFieldReturnName,textFieldAbnormalLoss,textFieldDiscountGiven,textFieldDiscountReceive,textFieldSaleReturn,textFieldPurchaseReturn;
		//######################################################################################################

	public java.sql.Date sqlDate;

	public java.sql.Connection connection=null;
	public int stockSize,cartSize,qt,qs,qc,is,ic,totalRCustomers,totalPCustomers,rcustn,pcustn,rt,rp,pt,tp,tt,pp,prt,trt;

	

	public AutocompletePayment()
	{

		connection=sqlconnection.dbConnector();
	}
	

	public void date()
	{
		java.util.Date utilDate = new java.util.Date();
	    sqlDate = new java.sql.Date(utilDate.getTime());
	  }
	
		

public void paymentcomponents(JTextField textFieldId1,JTextField textFieldTotal1,JTextField textFieldcName1,JTextField textFieldPaid1,JTextField textFieldcMobile1,JTextField textFieldTRemaining1,JTextField textFieldCity1,JTextField textFieldDate1,JTextField textFieldcNamePaymentS1,JTextField textFieldcNamePaymentD1,JTextField textFieldcNamePaymentU1,JTextField textFieldAbnormalName1,JTextField textFieldAbnormalLoss1,JTextField textFieldNameReturnName1,JTextField textFieldSaleReturn1,JTextField textFieldPurchaseReturn1,JTextField textFieldNameDiscount1,JTextField textFieldDiscountGiven1,JTextField textFieldDiscountReceive1)
{
	textFieldIdPayment=textFieldId1;
	textFieldTotalPayment=textFieldTotal1;
	textFieldcNamePayment=textFieldcName1;

	textFieldPaidPayment=textFieldPaid1;
	
	textFieldcMobilePayment=textFieldcMobile1 ;
	textFieldTRemainingPayment=textFieldTRemaining1;
	textFieldCityPayment=textFieldCity1;
	textFieldDatePayment=textFieldDate1;
	textFieldcNamePaymentS=textFieldcNamePaymentS1;
	
	textFieldcNamePaymentD=textFieldcNamePaymentD1;
	textFieldcNamePaymentU=textFieldcNamePaymentU1;
	textFieldAbnormalName=textFieldAbnormalName1;
	textFieldAbnormalLoss=textFieldAbnormalLoss1;
	textFieldReturnName=textFieldNameReturnName1;
	textFieldSaleReturn=textFieldSaleReturn1;
	textFieldPurchaseReturn=textFieldPurchaseReturn1;
	textFieldDiscountName=textFieldNameDiscount1;
	textFieldDiscountGiven=textFieldDiscountGiven1;
	textFieldDiscountReceive=textFieldDiscountReceive1;
	
}



	public void keypress(KeyEvent evt,JTextField st)
	{
		
		switch(evt.getKeyCode())
		{
		case KeyEvent.VK_BACK_SPACE:
			break;
		case KeyEvent.VK_ENTER:
			st.setText(st.getText());
			break;
		default:
			EventQueue.invokeLater(new Runnable(){
					@Override
					public void run()
					{
						try
						{
						String txt=st.getText();
						autoCompleteCustomerNames(txt,st);
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, "=== keypress =="+e);
						}
					}
			});
		}

	}
	
//###############################################################################################################

	public void autoCompleteCustomerNames( String st,JTextField f)
	{

		String complete="";
		int start=st.length();
		int last=st.length();
		accountArray();
		for(int a=0;a<recordcustNameList.size();a++)
		{ 
			if(recordcustNameList.get(a).toString().startsWith(st))
			{
				complete=recordcustNameList.get(a).toString();
				last=complete.length();
				break;
			}
		}
		if(last>start)
		{
			f.setText(complete);
			f.setCaretPosition(last);
			f.moveCaretPosition(start);
		}
			
		
	}
	
	
	
	//====================================================


	public void keypressMobile(KeyEvent evt,JTextField st)
	{
		
		switch(evt.getKeyCode())
		{
		case KeyEvent.VK_BACK_SPACE:
			break;
		case KeyEvent.VK_ENTER:
			st.setText(st.getText());
			break;
		default:
			EventQueue.invokeLater(new Runnable(){
					@Override
					public void run()
					{
						try
						{
						String txt=st.getText();
						autoCompleteMobile(txt,st);
						}
						catch(Exception e)
						{
							JOptionPane.showMessageDialog(null, "=== keypress =="+e);
						}
					}
			});
		}

	}
	
//###############################################################################################################

	public void autoCompleteMobile( String st,JTextField f)
	{

		String complete="";
		int start=st.length();
		int last=st.length();
		accountArray();
		for(int a=0;a<mobileList.size();a++)
		{ 
			if(mobileList.get(a).toString().startsWith(st))
			{
				complete=mobileList.get(a).toString();
				last=complete.length();
				break;
			}
		}
		if(last>start)
		{
			f.setText(complete);
			f.setCaretPosition(last);
			f.moveCaretPosition(start);
		}
			
		
	}
	
	//====================================================


		public void keypressCity(KeyEvent evt,JTextField st)
		{
			
			switch(evt.getKeyCode())
			{
			case KeyEvent.VK_BACK_SPACE:
				break;
			case KeyEvent.VK_ENTER:
				st.setText(st.getText());
				break;
			default:
				EventQueue.invokeLater(new Runnable(){
						@Override
						public void run()
						{
							try
							{
							String txt=st.getText();
							autoCompleteCity(txt,st);
							}
							catch(Exception e)
							{
								JOptionPane.showMessageDialog(null, "=== keypress =="+e);
							}
						}
				});
			}

		}
		
	//###############################################################################################################

		public void autoCompleteCity( String st,JTextField f)
		{

			String complete="";
			int start=st.length();
			int last=st.length();
			accountArray();
			for(int a=0;a<cityList.size();a++)
			{ 
				if(cityList.get(a).toString().startsWith(st))
				{
					complete=cityList.get(a).toString();
					last=complete.length();
					break;
				}
			}
			if(last>start)
			{
				f.setText(complete);
				f.setCaretPosition(last);
				f.moveCaretPosition(start);
			}
				
			
		}
		
	
	//#############################################CHECKING CUSTOMER EXITS OR NOT AS WELL AS HIS PREVIOUS BILLS ##################################

	public int checkCustomerPayment(JTextField textFieldcName)
	{
		rcustn=0;
		
		pcustn=0;
		String query1,query2,query3;
		PreparedStatement pst1,pst2,pst3;
		ResultSet rs1,rs2,rs3;
		accountArray();
		totalPCustomers=profitcustNameList.size();
		totalRCustomers=recordcustNameList.size();
		
		
		while(rcustn<totalRCustomers)
		{
			if(textFieldcName.getText().equals(recordcustNameList.get(rcustn)))
				break;
		
			rcustn++;
		}

		
		while(pcustn<totalPCustomers)
		{
			if(textFieldcName.getText().equals(profitcustNameList.get(pcustn)))
				break;
		
			pcustn++;
		}
		try{
					
			if(rcustn!=totalRCustomers)
			{
			rp=(int)remainingList.get(pcustn);
			tp=(int)totalList.get(pcustn);
			pp=(int)paidList.get(pcustn);
			
			}
			else
			{
				rp=0;
				tp=0;
				pp=0;
			}
			if(textFieldPaidPayment.getText().isEmpty())
			{
			pt=0;
			}
		else{
			pt=Integer.parseInt(textFieldPaidPayment.getText());
			}
			
			if(textFieldTotalPayment.getText().isEmpty())
			{
			tt=0;
			}
		else{
			tt=Integer.parseInt(textFieldTotalPayment.getText());
			}
			cmpltObj.date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			rt=tt-pt;
			//textFieldTRemainingPayment.setText(Integer.toString(rt));
			prt=rp;
			trt=prt+rt;
			//textFieldPRemainingPayment.setText(Integer.toString(prt));
			//textFieldDatePayment.setText(df.format(Calendar.getInstance().getTime()));
			textFieldDatePayment.setText(df.format(cmpltObj.sqlDate));
			textFieldTRemainingPayment.setText(Integer.toString(trt));
			textFieldTRemainingPayment.setEditable(false);
			//textFieldRemainingPayment.setEditable(false);
			//textFieldPRemainingPayment.setEditable(false);
			textFieldDatePayment.setEditable(false);
			//textFieldDatePayment.setText
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "QUANTITY :"+ e);
			e.printStackTrace();
		}

		if(rcustn!=totalRCustomers)
			return 1;
		else
			return 0;
	}


	//############################################## MAKE LISTS OF CUSTOMER NAMES,PAID AMOUNT ,TOTAL AMOUNT AND REMAINING AMMOUNT of Customers #############################(QuantityArray)
	public void accountArray()
	{
		String qua;
		PreparedStatement pst2;
		String query;
		ResultSet rs2;
		try{
			recordcustNameList=new ArrayList();
			profitcustNameList=new ArrayList();
		
			remainingList=new ArrayList();
			paidList=new ArrayList();
			totalList=new ArrayList();
			mobileList=new ArrayList();
			cityList=new ArrayList();

			mobileList.clear();
			cityList.clear();
			recordcustNameList.clear();
			profitcustNameList.clear();
			remainingList.clear();
			paidList.clear();			
			totalList.clear();			

			recordcustNameList=ItemlistArray("record");
			profitcustNameList=ItemlistArray("profit");
			mobileCityListArray("profit");
			
			query="select Remaining from profit";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				remainingList.add(Integer.parseInt(qua));
				
			}
			rs2.close();
			pst2.close();
			query="select paid from profit";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				paidList.add(Integer.parseInt(qua));
				
			}

			rs2.close();
			pst2.close();
			query="select Total from profit";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				totalList.add(Integer.parseInt(qua));
				
			}
			pst2.close();
			rs2.close();
//			connection.close();

		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}
		int s=remainingList.size();
	
	}
//##########################################################################################################


	//########################################DELETE  PAYMENTS FUNCTION##############################################################

	public void deletefun()
	{
	
	try{
		String queryp="delete from profit where lower(`name`)='"+textFieldcNamePaymentD.getText().toLowerCase()+"'";
		String queryr="delete from record where lower(`name`)='"+textFieldcNamePaymentD.getText().toLowerCase()+"'";
		
		PreparedStatement pstp=connection.prepareStatement(queryp);

		PreparedStatement pstr=connection.prepareStatement(queryr);
		if((textFieldcNamePaymentD.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Name is missing \n"+"Please enter Item name to be deleted", "Failure", JOptionPane.ERROR_MESSAGE);
			}
		else
		{
			int check=checkCustomerPayment(textFieldcNamePaymentD);
			if(check!=1)
			{
				JOptionPane.showMessageDialog(null, 
						  "Item not found", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else{
			
				pstp.execute();
				pstr.execute();
				
				
			
			}
		}

		pstp.close();
		pstr.close();
		
	}
	catch(Exception R){
		JOptionPane.showMessageDialog(null, 
				"Duplicate Entry Or Name may be wrong", "Failure", JOptionPane.ERROR_MESSAGE);
		R.printStackTrace();
	}

	}

//#########################################ITEM NAMES STORE IN ARRAYLIST FUNCTION#############################################################

public ArrayList ItemlistArray(String tbl)
{
	try{
		ResultSet rs;
		String query;
		PreparedStatement pst;
		
		temp=new ArrayList();

		temp.clear();
		query="select Name from "+tbl;
		//String query="select * from stock";

		pst=connection.prepareStatement(query);
		rs=pst.executeQuery();
		while(rs.next())
		{
			String name=rs.getString(1);
			temp.add(name);
			
		}
		pst.close();
		rs.close();
//		connection.close();

	}
	catch(Exception R1){
		JOptionPane.showMessageDialog(null, 
				R1, "Failure", JOptionPane.ERROR_MESSAGE);
		
		R1.printStackTrace();
	}
return temp;	
}



public void mobileCityListArray(String tbl)
{
	try{
		ResultSet rs;
		String query;
		PreparedStatement pst;
		
			query="select mobile from "+tbl;
		//String query="select * from stock";

		pst=connection.prepareStatement(query);
		rs=pst.executeQuery();
		while(rs.next())
		{
			String mobile=rs.getString(1);
			mobileList.add(mobile);
			
		}
		

		query="select city from "+tbl;
	//String query="select * from stock";

	pst=connection.prepareStatement(query);
	rs=pst.executeQuery();
	while(rs.next())
	{
		String city=rs.getString(1);
		cityList.add(city);
		
	}
	
	
		pst.close();
		rs.close();
//		connection.close();

	}
	catch(Exception R1){
		JOptionPane.showMessageDialog(null, 
				R1, "Failure", JOptionPane.ERROR_MESSAGE);
		
		R1.printStackTrace();
	}
	
}


	//################################ OLD CUSTOMER INSERT IN PROFIT#############################################

		public void existUpdatePayment()
	{
		try{
			//JOptionPane.showMessageDialog(null, "EXIST UPDATE INSERT ");
			
		String query="Update profit set Paid='"+(pp+pt)+"',Total='"+(tp+Integer.parseInt(textFieldTotalPayment.getText()))+ "',Remaining='"+textFieldTRemainingPayment.getText()+"' where Name='"+textFieldcNamePayment.getText()+"'";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, "EXIST EXIST INSERT =="+e);
		}

	}


		//#########################################INSERT IN RECORD FOR ALL USERS##################################
		public void commonInsertPayment()
		{
			

			try{
			String query="Insert into record (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
			
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1,textFieldcNamePayment.getText().toLowerCase());
			pst.setString(2,textFieldCityPayment.getText().toLowerCase());
			pst.setString(3,textFieldcMobilePayment.getText());
			pst.setString(4,sqlDate.toString());
			pst.setString(5,textFieldPaidPayment.getText());
			pst.setString(6,textFieldTRemainingPayment.getText());
			pst.setString(7,textFieldTotalPayment.getText());

			pst.execute();
			pst.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "EXIST COMMON INSERT =="+e);
			}


		}


		//################################NEW CUSTOMER INSERT IN PROFIT#############################################

	public void newInsertPayment()
	{
		

		try{
	//		JOptionPane.showMessageDialog(null, "NEW INSERT ");
			
		String query="Insert into profit (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
		
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,textFieldcNamePayment.getText().toLowerCase());
		pst.setString(2,textFieldCityPayment.getText().toLowerCase());
		pst.setString(3,textFieldcMobilePayment.getText());
		pst.setString(4,cmpltObj.sqlDate.toString());
		pst.setString(5,textFieldPaidPayment.getText());
		pst.setString(6,textFieldTRemainingPayment.getText());
		pst.setString(7,textFieldTotalPayment.getText());

		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "EXIST NEW INSERT =="+e);
		}


	}
	
	//----------------------------------------------------------------------------
	//###############################################CALCULATE CLOSING STK FUNCTION###################################################################
	public void calculateClosingStk(Date date)
	{
		getData(date);
		new_closing_stk=opening_stk+stockpurchase-purchase_price_table-abnormal;
	}
	

			public void getData(Date dd)
			{
			
				try{
					query1="select * from profitfinder where date ='"+dd+"'";	
					pst1=connection.prepareStatement(query1);
					rs1=pst1.executeQuery();
					
					while(rs1.next())
					{
						abnormal=rs1.getFloat("abnormal");
						purchase_price_table=rs1.getFloat("purchase_price");
						stockpurchase=rs1.getFloat("stockpurchase");
						opening_stk=rs1.getFloat("opening_stk");
						closing_stk=rs1.getFloat("closing_stk");
						
						
					}
					
					rs1.close();
					pst1.close();
					
					JOptionPane.showMessageDialog(null, "abnormal  : "+ abnormal+" sale_price-stk :"+sale_price_table+" purchase_price_table : "+purchase_price_table+" stockpurchase:"+stockpurchase+"  opening_stk: "+opening_stk+" closing_stk:"+closing_stk);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				

			}
			//######################## Date Checker #############################################(Check date exists in profit finder table)
			
			public int dateChecker(Date dd)
			{
			count=0;
			try{
				query1="select * from profitfinder where date ='"+dd+"'";	
				pst1=connection.prepareStatement(query1);
				rs1=pst1.executeQuery();
				while(rs1.next())
				{
					count++;
				}
				rs1.close();
				pst1.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, e);
			}
			

			return count;
			
			}
			
			//#####################################################################################

			public void insfun(float abnormalT)
			{
				cmpltObj.date();
				int check=dateChecker(cmpltObj.sqlDate);
				
				try{
				query1="insert into profitfinder (date,abnormal) values (?,?)";
				pst1=connection.prepareStatement(query1);			
				
				pst1.setString(1,cmpltObj.sqlDate.toString());
				pst1.setString(2,Float.toString(abnormalT));
				
				if(check==0)
					pst1.execute();
				else
					updatefun(abnormalT);
				
				pst1.close();
				updateClosingStk();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"INSERT FUNCTION :"+e);
				}
			
			}
			
			//#########################################################################################
			
			public void updatefun(float abnormalT)
			{
				cmpltObj.date();
			getData(cmpltObj.sqlDate);
			float total=abnormal+ abnormalT;
			JOptionPane.showMessageDialog(null," Abnormal : "+abnormal+" Abnormal TExt:"+abnormalT +" Total :"+total);
			
			try{
				query1="update profitfinder set abnormal='"+total+"' where date = '"+cmpltObj.sqlDate+"'";
				pst1=connection.prepareStatement(query1);			
				
				
				pst1.execute();
				pst1.close();

				updateClosingStk();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"UPDATE FUNCTION :"+e);
				}
				
			
			}
			
			
			


//######################################## ABNORMAL INSERT############################################################
	public void abnormal()
	{
		

		try{
			if((textFieldAbnormalName.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Name is missing \n"+"Please enter the name ", "Failure", JOptionPane.ERROR_MESSAGE);
			}		
			else
			{
				if(textFieldAbnormalLoss.getText().isEmpty())
					textFieldAbnormalLoss.setText(Integer.toString(0));
				String query="Insert into allrecord (Name,date,abnormal) values (?,?,?)";
				cmpltObj.date();
				PreparedStatement pst=connection.prepareStatement(query);
				pst.setString(1,textFieldAbnormalName.getText().toLowerCase());
				pst.setString(3,textFieldAbnormalLoss.getText());
				pst.setString(2,cmpltObj.sqlDate.toString());
				pst.execute();
				pst.close();

				insfun(Float.parseFloat(textFieldAbnormalLoss.getText()));
				updateClosingStk();
				}
			
			}	
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "ABNORMSL INSERT =="+e);
				e.printStackTrace();
			}


	}
	
	
	public void updateClosingStk()
	{
		calculateClosingStk(cmpltObj.sqlDate);
		try{
		String query="update profitfinder set closing_stk='"+new_closing_stk+"' where date = '"+cmpltObj.sqlDate+"'";
		cmpltObj.date();
		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "updateClosingStk :"+e);
		}
	}

	//######################################## RETURN INSERT############################################################
		public void returnfun()
		{
			

			try{
			if((textFieldReturnName.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Name is missing \n"+"Please enter the name ", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(textFieldSaleReturn.getText().isEmpty())
					textFieldSaleReturn.setText(Integer.toString(0));
				if(textFieldPurchaseReturn.getText().isEmpty())
					textFieldPurchaseReturn.setText(Integer.toString(0));
				
			String query="Insert into allrecord (Name,date,purchase_return,sale_return) values (?,?,?,?)";
			cmpltObj.date();
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1,textFieldReturnName.getText().toLowerCase());
			pst.setString(3,textFieldPurchaseReturn.getText());
			pst.setString(4,textFieldSaleReturn.getText());
			pst.setString(2,cmpltObj.sqlDate.toString());
			pst.execute();
			pst.close();
			
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Return INSERT =="+e);
			}


		}

		//######################################## DISCOUNT INSERT############################################################
		public void discount()
		{

			try{	
			if((textFieldDiscountName.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Name is missing \n"+"Please enter the name ", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{

				if(textFieldDiscountReceive.getText().isEmpty())
					textFieldDiscountReceive.setText(Integer.toString(0));
				if(textFieldDiscountGiven.getText().isEmpty())
					textFieldDiscountGiven.setText(Integer.toString(0));
				
				
			String query="Insert into allrecord (Name,date,discount_receive,discount_given) values (?,?,?,?)";
			cmpltObj.date();
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1,textFieldDiscountName.getText().toLowerCase());
			pst.setString(3,textFieldDiscountReceive.getText());
			pst.setString(4,textFieldDiscountGiven.getText());
			pst.setString(2,cmpltObj.sqlDate.toString());
			pst.execute();
			pst.close();
			}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "ABNORMSL INSERT =="+e);
			}


		}
		
			
	
	//#########################################INSERT FUNCTION FOR CUSTOMERS DETAILS#######################################
		public void setValsPayment()
		{	
			
			try{
				
					if((textFieldcNamePayment.getText().toLowerCase()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}

				else if((textFieldcMobilePayment.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter Mobile number...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldTotalPayment.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter Total amount...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldCityPayment.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter city...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 
				else
				{
					if((textFieldPaidPayment.getText()).isEmpty())
					{
					textFieldPaidPayment.setText(Integer.toString(0));
					}  
				
					ch=checkCustomerPayment(textFieldcNamePayment);
					
				}
				//JOptionPane.showMessageDialog(null,"Data Saved..........!");

				
				
			}
			catch(Exception R2){
				JOptionPane.showMessageDialog(null, 
						"CUSTINSFUN  =="+R2.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
				R2.printStackTrace();
			}

			

		}
		
		public void insertNow()
		{
			

			if(ch==1)
			{
				commonInsertPayment();
				existUpdatePayment();
				
			}
			else
			{
				commonInsertPayment();
				newInsertPayment();
			}

		}

		
		public void updateNow()
		{
			ch=checkCustomerPayment(textFieldcNamePaymentU);
			if(ch==1)
			{
				commonInsertPayment();
				existUpdatePayment();
				
			}
			else
			{
				JOptionPane.showMessageDialog(null, 
						"Customer Not Found...!", "Failure", JOptionPane.ERROR_MESSAGE);
				
			}

		}
//=================================================================================================================================================
public void setValsUpdateField()
{
try{
	
	JOptionPane.showMessageDialog(null, 
			"I am in update calll", "Success", JOptionPane.ERROR_MESSAGE);
	
	String query="select * from profit where Name='"+textFieldcNamePaymentU.getText()+"'";
	PreparedStatement pst=connection.prepareStatement(query);

	ResultSet rs=pst.executeQuery();
	
	while(rs.next())
	{
		textFieldIdPayment.setText(rs.getString("ID"));
		if(textFieldcMobilePayment.getText().isEmpty())
			textFieldcMobilePayment.setText(rs.getString("mobile"));
		if(textFieldCityPayment.getText().isEmpty())
			textFieldCityPayment.setText(rs.getString("city"));
		if(textFieldTotalPayment.getText().isEmpty())
			textFieldTotalPayment.setText(Integer.toString(0));
		
			ch=checkCustomerPayment(textFieldcNamePaymentU);
			
		
		
	}
	pst.close();
	rs.close();
	}
catch(Exception e){
	
	e.printStackTrace();
	}


}
}
