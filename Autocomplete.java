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

public class Autocomplete {
	
	private ArrayList billItemlist,recordcustNameList,profitcustNameList,remainingList,paidList,totalList;
	public ArrayList temp;
	public ArrayList stockItemlist;
	public ArrayList quantArry;
	private JTextField fld;
	private JTextField textFieldIdBill;
	private JTextField textFieldPriceBill;
	private JTextField textFieldTotalBill;
	private JTextField textFieldcNameBill;

	private JTextField textFieldNameBill;
	private JTextField textFieldPaidBill;
	private JTextField textFieldRemainingBill;
	private JTextField textFieldcMobileBill ;
	private JTextField textFieldPRemainingBill;
	private JTextField textFieldTRemainingBill;
	private JTextField textFieldCityBill;
	private JTextField textFieldDateBill;
	private JTextField textFieldQuantityBill;


	//#########################################################################
		private JTextField textFieldIdPayment;
		private JTextField textFieldPricePayment;
		private JTextField textFieldTotalPayment;
		private JTextField textFieldcNamePayment;

		private JTextField textFieldNamePayment;
		private JTextField textFieldPaidPayment;
		private JTextField textFieldRemainingPayment;
		private JTextField textFieldcMobilePayment ;
		private JTextField textFieldPRemainingPayment;
		private JTextField textFieldTRemainingPayment;
		private JTextField textFieldCityPayment;
		private JTextField textFieldDatePayment;
		private JTextField textFieldQuantityPayment;

		//######################################################################################################

	public java.sql.Date sqlDate;

	private java.sql.Connection connection=null;
	public int stockSize,cartSize,qt,qs,qc,is,ic,totalRCustomers,totalPCustomers,rcustn,pcustn,rt,rp,pt,tp,tt,pp,prt,trt;

	

	public Autocomplete()
	{

		connection=sqlconnection.dbConnector();
	}
	

	//#################################################################
	public void date()
	{
		java.util.Date utilDate = new java.util.Date();
	    sqlDate = new java.sql.Date(utilDate.getTime());

	    /*
	     * FOR YESTERDAY
	    */
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, 8);
	    sqlDate= new java.sql.Date(calendar.getTimeInMillis());
	     
	}

	

	public void billcomponents(JTextField textFieldQuantity1,JTextField textFieldName1,JTextField textFieldId1,JTextField textFieldPrice1,JTextField textFieldTotal1,JTextField textFieldcName1,JTextField textFieldPaid1,JTextField textFieldRemaining1,JTextField textFieldcMobile1,JTextField textFieldPRemaining1,JTextField textFieldTRemaining1,JTextField textFieldCity1,JTextField textFieldDate1)
	{
		textFieldIdBill=textFieldId1;
		textFieldPriceBill=textFieldPrice1;
		textFieldTotalBill=textFieldTotal1;
		textFieldcNameBill=textFieldcName1;

		textFieldNameBill=textFieldName1;
		textFieldPaidBill=textFieldPaid1;
		textFieldRemainingBill=textFieldRemaining1;
		textFieldcMobileBill=textFieldcMobile1 ;
		textFieldPRemainingBill=textFieldPRemaining1;
		textFieldTRemainingBill=textFieldTRemaining1;
		textFieldCityBill=textFieldCity1;
		textFieldDateBill=textFieldDate1;
		textFieldQuantityBill=textFieldQuantity1;

		
	}
		

public void paymentcomponents(JTextField textFieldQuantity1,JTextField textFieldName1,JTextField textFieldId1,JTextField textFieldPrice1,JTextField textFieldTotal1,JTextField textFieldcName1,JTextField textFieldPaid1,JTextField textFieldRemaining1,JTextField textFieldcMobile1,JTextField textFieldPRemaining1,JTextField textFieldTRemaining1,JTextField textFieldCity1,JTextField textFieldDate1)
{
	textFieldIdPayment=textFieldId1;
	textFieldPricePayment=textFieldPrice1;
	textFieldTotalPayment=textFieldTotal1;
	textFieldcNamePayment=textFieldcName1;

	textFieldNamePayment=textFieldName1;
	textFieldPaidPayment=textFieldPaid1;
	textFieldRemainingPayment=textFieldRemaining1;
	textFieldcMobilePayment=textFieldcMobile1 ;
	textFieldPRemainingPayment=textFieldPRemaining1;
	textFieldTRemainingPayment=textFieldTRemaining1;
	textFieldCityPayment=textFieldCity1;
	textFieldDatePayment=textFieldDate1;
	textFieldQuantityPayment=textFieldQuantity1;

	
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
						autoComplete(txt,st);
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

	public void autoComplete( String st,JTextField f)
	{

		String complete="";
		int start=st.length();
		int last=st.length();
		for(int a=0;a<stockItemlist.size();a++)
		{ 
			if(stockItemlist.get(a).toString().startsWith(st))
			{
				complete=stockItemlist.get(a).toString();
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

	//#############################################CHECKING CUSTOMER EXITS OR NOT AS WELL AS HIS PREVIOUS BILLS ##################################

	
	public int checkCustomer(JTextField textFieldcName)
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
		
		/*query1="select Remaining from profit where Name='"+textFieldcNameBill.getText()+"'";
		pst1=connection.prepareStatement(query1);
		if(custn!=totalCustomers)
			{
			rs1=pst1.executeQuery();
			
		
			while(rs1.next())
				rp=Integer.parseInt(rs1.getString(1));
			}

		query1="select Total from profit where Name='"+textFieldcNameBill.getText()+"'";
		pst1=connection.prepareStatement(query1);
		if(custn!=totalCustomers)
			{
			rs1=pst1.executeQuery();
			
		
			while(rs1.next())
				tp=Integer.parseInt(rs1.getString(1));
			}
		

		*/
			
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
			if(textFieldPaidBill.getText().isEmpty())
			{
			pt=0;
			}
		else{
			pt=Integer.parseInt(textFieldPaidBill.getText());
			}
			
			if(textFieldTotalBill.getText().isEmpty())
			{
			tt=0;
			}
		else{
			tt=Integer.parseInt(textFieldTotalBill.getText());
			}
			date();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			rt=tt-pt;
			textFieldRemainingBill.setText(Integer.toString(rt));
			prt=rp;
			trt=prt+rt;
			textFieldPRemainingBill.setText(Integer.toString(prt));
			textFieldDateBill.setText(df.format(Calendar.getInstance().getTime()));
			textFieldTRemainingBill.setText(Integer.toString(trt));
			textFieldTRemainingBill.setEditable(false);
			textFieldRemainingBill.setEditable(false);
			textFieldPRemainingBill.setEditable(false);
			textFieldDateBill.setEditable(false);
			//textFieldDateBill.setText
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "QUANTITY");
			
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

			recordcustNameList.clear();
			profitcustNameList.clear();
			
			recordcustNameList=ItemlistArray("record");
			profitcustNameList=ItemlistArray("profit");
			
			remainingList.clear();
			paidList.clear();			
			totalList.clear();			

			query="select Remaining from profit";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				remainingList.add(Integer.parseInt(qua));
				
			}
			query="select paid from profit";
			pst2=connection.prepareStatement(query);
			rs2=pst2.executeQuery();
			while(rs2.next())
			{
				qua=rs2.getString(1);
				paidList.add(Integer.parseInt(qua));
				
			}
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

	//########################################CHECK QUANTITY OF ITEMS IN STOCK AS WELL AS IN CART##############################################################

public void quantity()
{
	ic=0;
	is=0;
	String query1,query2,query3;
	PreparedStatement pst1,pst2,pst3;
	ResultSet rs1,rs2,rs3;
	quantityArray();
	stockSize=stockItemlist.size();
	cartSize=billItemlist.size();
	
	
	while(is<stockSize)
	{
		if(textFieldNameBill.getText().equals(stockItemlist.get(is)))
			break;
	
		is++;
	}
	while(ic<cartSize)
	{
		if(textFieldNameBill.getText().equals(billItemlist.get(ic)))
			break;
	
		ic++;
	}

	try{
	
	query1="select Quantity from bill where Name='"+textFieldNameBill.getText()+"'";
	pst1=connection.prepareStatement(query1);
	if(ic!=cartSize)
		{
		rs1=pst1.executeQuery();
		
	
		while(rs1.next())
		qc=Integer.parseInt(rs1.getString(1));
		rs1.close();
	
		}
	pst1.close();
		
	if(textFieldQuantityBill.getText().isEmpty())
	{
		qt=0;
	}
	else{
		qt=Integer.parseInt(textFieldQuantityBill.getText());
		}
	if(is!=stockSize)
		qs=(int)quantArry.get(is);
	
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "QUANTITY");
		
	}
}

//#########################################ITEM NAMES STORE IN ARRAYLIST FUNCTION#############################################################

public ArrayList ItemlistArray(String tbl)
{
	try{
		temp=new ArrayList();

		temp.clear();
		String query="select Name from "+tbl;
		//String query="select * from stock";

		PreparedStatement pst=connection.prepareStatement(query);
		ResultSet rs=pst.executeQuery();
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

//########################################################## MAKE ARRAY LIST OF QUANTITIES ############################################

public void quantityArray()
{
	try{
		stockItemlist=new ArrayList();
		quantArry=new ArrayList();

		billItemlist=new ArrayList();
		stockItemlist.clear();
		stockItemlist=ItemlistArray("stock");
		billItemlist.clear();
		billItemlist=ItemlistArray("bill");
		quantArry.clear();
		
		String query="select Quantity from stock";
		PreparedStatement pst2=connection.prepareStatement(query);
		ResultSet rs2=pst2.executeQuery();
		while(rs2.next())
		{
			String qua=rs2.getString(1);
			quantArry.add(Integer.parseInt(qua));
			
		}
		pst2.close();
		rs2.close();
//		connection.close();

	}
	catch(Exception R1){
		JOptionPane.showMessageDialog(null, 
				R1, "Failure", JOptionPane.ERROR_MESSAGE);
		
		R1.printStackTrace();
	}
	
}
//#########################################################################################################


}
