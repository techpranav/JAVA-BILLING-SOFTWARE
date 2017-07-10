import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import javaGUI.sqlconnection;

public class profitFinder {

	int n,count;
	public java.sql.Date sqlDate;
	Autocomplete cmpltObj =new Autocomplete();
	Date d;
	String query1,query2,query3;
	PreparedStatement pst1,pst2,pst3;
	ResultSet rs1,rs2,rs3;
	float sale_price_table,purchase_price_table,stockpurchase,opening_stk,closing_stk;
	Connection connection=null;
	public profitFinder()
	{
		//connection=sqlconnection.dbConnector();
		
	}
	
	//################################ Get Date today################################################
	public void date()
	{
		java.util.Date utilDate = new java.util.Date();
	    sqlDate = new java.sql.Date(utilDate.getTime());
	  }
	
	public int checkDate()
	{

		int ch=0;
		try{
			cmpltObj.date();
			
			String query="select date from profitfinder where date='"+cmpltObj.sqlDate+"';";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			ch=1;	
			}
			pst.close();
			rs.close();

		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR IN CHRCKDATE :"+ e);
			e.printStackTrace();
		}
		
		return ch;
	}
	

		
	public void getData(Date dd)
	{
	
		try{
			query1="select * from profitFinder where date ='"+dd+"'";	
			pst1=connection.prepareStatement(query1);
			rs1=pst1.executeQuery();
			while(rs1.next())
			{
				sale_price_table=rs1.getFloat("sale_price");
				purchase_price_table=rs1.getFloat("purchase_price");
				stockpurchase=rs1.getFloat("stockpurchase");
				opening_stk=rs1.getFloat("opening_stk");
				closing_stk=rs1.getFloat("closing_stk");
				
				
			}
			
			rs1.close();
			pst1.close();
			
			JOptionPane.showMessageDialog(null, "sale_price-stk :"+sale_price_table+" purchase_price_table : "+purchase_price_table+" stockpurchase:"+stockpurchase+"  opening_stk: "+opening_stk+" closing_stk:"+closing_stk);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		

	}
	//#####################################################################################
/*
		public void insfun(float totalpurchase)
		{
			date();
			int check=checkDate();
			
			try{
			query1="insert into profitFinder (date,stockpurchase) values (?,?)";
			pst1=connection.prepareStatement(query1);			
			
			pst1.setString(1,sqlDate.toString());
			pst1.setString(2,Float.toString(totalpurchase));
			
			if(check==0)
				pst1.execute();
			else
				updatefun(totalpurchase);
			
			pst1.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"INSERT FUNCTION :"+e);
			}
		
		}
		
		//#########################################################################################
		
		public void updatefun(float totalpurchase)
		{
		date();
		getData(sqlDate);
		float total=stockpurchase+ totalpurchase;
		JOptionPane.showMessageDialog(null," stockpurchase :"+stockpurchase +" totalpurchase :"+totalpurchase +" Total :"+total);
		//JOptionPane.showMessageDialog(null," stockpurchase"+ +" Total :"+total);
		
		try{
			query1="update profitFinder set stockpurchase='"+total+"' where date = '"+sqlDate+"'";
			
			
			pst1=connection.prepareStatement(query1);			
			
			
			pst1.execute();
			pst1.close();
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"UPDATE FUNCTION :"+e);
			}
			
		
		}
		
		
		
	}
*/	
		
}