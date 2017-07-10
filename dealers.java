import java.awt.EventQueue;
import java.awt.event.KeyEvent;
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

public class dealers {
	
	public ArrayList dealersList,remainingList,paidList,totalList,mobileList,cityList;
	public ArrayList temp;
	public JTextField fld;
	public int ch;

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
	
		//######################################################################################################

	public java.sql.Date sqlDate;

	public java.sql.Connection connection=null;
	public int stockSize,cartSize,qt,qs,qc,is,ic,totalDCustomers,total,dcustn,rt,rp,pt,tp,tt,pp,prt,trt;

	

	public dealers()
	{

		connection=sqlconnection.dbConnector();
	}
	

	public void date()
	{
		java.util.Date utilDate = new java.util.Date();
	    sqlDate = new java.sql.Date(utilDate.getTime());
	  }
	
		

public void paymentcomponents(JTextField textFieldId1,JTextField textFieldTotal1,JTextField textFieldcName1,JTextField textFieldPaid1,JTextField textFieldcMobile1,JTextField textFieldTRemaining1,JTextField textFieldCity1,JTextField textFieldDate1,JTextField textFieldcNamePaymentS1,JTextField textFieldcNamePaymentD1,JTextField textFieldcNamePaymentU1)
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
		for(int a=0;a<dealersList.size();a++)
		{ 
			if(dealersList.get(a).toString().startsWith(st))
			{
				complete=dealersList.get(a).toString();
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
		
		dcustn=0;
		String query1,query2,query3;
		PreparedStatement pst1,pst2,pst3;
		ResultSet rs1,rs2,rs3;
		accountArray();
		totalDCustomers=dealersList.size();
		
		
		while(dcustn<totalDCustomers)
		{
			if(textFieldcName.getText().equals(dealersList.get(dcustn)))
				break;
		
			dcustn++;
		}

		try{
					
			if(dcustn!=totalDCustomers)
			{
			rp=(int)remainingList.get(dcustn);
			tp=(int)totalList.get(dcustn);
			pp=(int)paidList.get(dcustn);
			
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
			date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			rt=tt-pt;
			//textFieldTRemainingPayment.setText(Integer.toString(rt));
			prt=rp;
			trt=prt+rt;
			//textFieldPRemainingPayment.setText(Integer.toString(prt));
			//textFieldDatePayment.setText(df.format(Calendar.getInstance().getTime()));
			textFieldDatePayment.setText(df.format(sqlDate));
			textFieldTRemainingPayment.setText(Integer.toString(trt));
			textFieldTRemainingPayment.setEditable(false);
			//textFieldRemainingPayment.setEditable(false);
			//textFieldPRemainingPayment.setEditable(false);
			textFieldDatePayment.setEditable(false);
			//textFieldDatePayment.setText
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "QUANTITY");
			
		}

		if(dcustn!=totalDCustomers)
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
			dealersList=new ArrayList();
			
			remainingList=new ArrayList();
			paidList=new ArrayList();
			totalList=new ArrayList();
			mobileList=new ArrayList();
			cityList=new ArrayList();

			mobileList.clear();
			cityList.clear();
			dealersList.clear();
			remainingList.clear();
			paidList.clear();			
			totalList.clear();			

			dealersList=ItemlistArray("dealers");
			mobileCityListArray("dealers");
			
			query="select Remaining from dealers";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				remainingList.add(Integer.parseInt(qua));
				
			}
			query="select paid from dealers";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				paidList.add(Integer.parseInt(qua));
				
			}
			query="select Total from dealers";
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
	//	int s=remainingList.size();
	
	}
//##########################################################################################################
		public void insertDebitNow()
		{
			int ch=checkCustomerPayment(textFieldcNamePayment);

			if(ch==1)
			{
				commonInsertDebitPayment();
				existUpdateDebitPayment();
				
			}
			else
			{
				commonInsertDebitPayment();
				newInsertDebitPayment();
			}

		}

		

	//########################################DELETE  PAYMENTS FUNCTION##############################################################

	public void deletefun()
	{
	
	try{
		String queryp="delete from dealers where lower(`name`)='"+textFieldcNamePaymentD.getText().toLowerCase()+"'";
		String queryr="delete from debit where lower(`name`)='"+textFieldcNamePaymentD.getText().toLowerCase()+"'";
		
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
				pstp.close();
				pstr.close();
				
			}
			else{
			
				pstp.execute();
				pstp.close();
				pstr.execute();
				pstr.close();
				
			
			}
		}

		
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


	//################################ OLD CUSTOMER INSERT IN dealers#############################################

		public void existUpdateDebitPayment()
	{
		try{
			//JOptionPane.showMessageDialog(null, "EXIST UPDATE INSERT ");
			
		String query="Update dealers set Paid='"+(pp+pt)+"',Total='"+(tp+Integer.parseInt(textFieldTotalPayment.getText()))+ "',Remaining='"+textFieldTRemainingPayment.getText()+"' where Name='"+textFieldcNamePayment.getText()+"'";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, "EXIST EXIST INSERT =="+e);
		}

	}


		//#########################################INSERT IN debit FOR ALL USERS##################################
		public void commonInsertDebitPayment()
		{
			

			try{
			String query="Insert into debit (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
			
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

		//################################NEW CUSTOMER INSERT IN dealers#############################################

	public void newInsertDebitPayment()
	{
		

		try{
	//		JOptionPane.showMessageDialog(null, "NEW INSERT ");
			
		String query="Insert into dealers (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
		
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
			JOptionPane.showMessageDialog(null, "EXIST NEW INSERT =="+e);
		}


	}


	
	
	//#########################################INSERT FUNCTION FOR CUSTOMERS DETAILS#######################################
		public void setValsPayment()
		{	
			
			try{
				
				JOptionPane.showMessageDialog(null, 
						"I am in insert calll", "Success", JOptionPane.ERROR_MESSAGE);
				
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
				else if((textFieldPaidPayment.getText()).isEmpty())
					{
					textFieldPaidPayment.setText(Integer.toString(0));
					}  
				else
				{
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
		
		
		
		public void updateDebitNow()
		{
			int ch=checkCustomerPayment(textFieldcNamePayment);
			if(ch==1)
			{
				commonInsertDebitPayment();
				existUpdateDebitPayment();
				
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
	
	String query="select * from dealers where Name='"+textFieldcNamePaymentU.getText()+"'";
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
		{
			ch=checkCustomerPayment(textFieldcNamePaymentU);
			
		}
		
	}
	pst.close();
	rs.close();
	}
catch(Exception e){
	
	e.printStackTrace();
	}


}
}
