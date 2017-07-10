import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;
import javaGUI.sqlconnection;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class stock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane ;
	AutocompletePayment objPayment=new AutocompletePayment();
	
	Connection connection=null;
	private JComboBox comboBox,optionBox,gstBox ;
	private JComboBox comboBoxdel ;
	private JComboBox comboBoxsrch ;
	private JTextField textFieldNameS;
	private JTextField textFieldNamed;
	private JTextField textFieldNames;
	private JTextField textFieldIds;
	private static double width;
	private static double height;
	static stock frame=new stock();
	home hm;
	private JTextField textFieldName;
	private JTextField textFieldQuantity;
	private JTextField textFieldId;
	private JTextField textFieldSellPrice;
	private ArrayList quantArry;
	private ArrayList names;
	private ArrayList temp;
	private String query,query2,query3,query4;
	public java.sql.Date sqlDate;
	private JPanel panelDelete,panelUpdate,panel_4  ;
	private int ns,qt,qs,is;
	private float totalsell,totalpurchase;
	Autocomplete cmpltObj=new Autocomplete();
	profitFinder objp=new profitFinder();
	PreparedStatement pst4;														
	JLabel lblSellPrice;
	private JTextField textFieldPurchase;
	private JTextField textFieldDealer;
	float stkpurchasePrice,stockpurchaseP,total,previous_closing_stk;
	int tax;
	float total_tax;
	//######################################################################################################
	float sale_price,purchase_price,stockpurchase,opening_stk,closing_stk,new_closing_stk,newStockPurchase,abnormal;
	String sName;
	int squantity,spurchase,stotalpurchase,ssale,stotalsale,stax,stotaltax;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try   
				{

					frame = new stock();
					frame.setLocationRelativeTo(null);  // Explicit JFrame if outside JFrame constructor.
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setUndecorated(true);
					frame.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					width = screenSize.getWidth();
					height = screenSize.getHeight();
					frame.setSize((int)width,(int)height);
					frame.setResizable(true);
					
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public void getProfitFinderComponents(Date date1)
	{

		try{
			
			String query="select * from profitfinder where date='"+date1+"';";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				sale_price=rs.getFloat("sale_price");
				purchase_price=rs.getFloat("purchase_price");
				stockpurchase=rs.getFloat("stockpurchase");
				opening_stk=rs.getFloat("opening_stk");
				closing_stk=rs.getFloat("closing_stk");
				abnormal=rs.getFloat("abnormal");
				
			}
			pst.close();
			rs.close();

		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR IN CHRCKDATE :"+ e);
			e.printStackTrace();
		}
		


	}
	
	
	//#################################SELECT PREVIOUS CLOSING STK FUNCTION #################################################
	public void selectPreviousClosingStk()
	{
		
		try{
			previous_closing_stk=0;
			String query="select * from profitfinder where id=  ( select max(ID) from profitfinder);";
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				previous_closing_stk=rs.getFloat("closing_stk");
			}
			pst.close();
			rs.close();

		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR IN CHRCKDATE :"+ e);
			e.printStackTrace();
		}
		
		
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
	
	public void getDataProfitFinder(Date date2)
	{
		try{
			String query="select * from profitfinder where date='"+date2+"' ;";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				stockpurchaseP=rs.getFloat("stockpurchase");
			}
			pst.close();
			rs.close();
			}
			catch(Exception e)
			{	
				JOptionPane.showMessageDialog(null, "EXIST EXIST INSERT =="+e);
			}

	}
	public void updateProfitFinder(float purchaseTotal)
	{
		float previous=0;
		try
		{
		cmpltObj.date();
		getDataProfitFinder(cmpltObj.sqlDate);
		String query2="select * from profitfinder where date=='"+cmpltObj.sqlDate+"';";

		PreparedStatement pst2=connection.prepareStatement(query2);
		ResultSet rs=pst2.executeQuery();
		while(rs.next())
			previous=Float.parseFloat(rs.getString("stockpurchase"));
		newStockPurchase=purchaseTotal+previous;
		
		
		String query="Update profitfinder set stockpurchase='"+(purchaseTotal+previous) +"' where date='"+cmpltObj.sqlDate+"';";

		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();
		
		pst.close();
		rs.close();

		objPayment.updateClosingStk();
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, "update profit finder =="+e);
			e.printStackTrace();
		}

	}
	//#########################################################################################################################
	public void profitFinderInsert(float stockpurchaseText)
	{

	int ch=0;
	
	
	
	try{
		cmpltObj.date();
		ch=checkDate();
		selectPreviousClosingStk();
		String query="Insert into profitfinder (date,stockpurchase,opening_stk) values (?,?,?)";
		newStockPurchase=stockpurchaseText;
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,cmpltObj.sqlDate.toString());
		pst.setString(2,Float.toString(stockpurchaseText));
		pst.setFloat(3,previous_closing_stk);
		
		if(ch!=1)
			{
		//	JOptionPane.showMessageDialog(null, "INSERT");
			pst.execute();
			pst.close();
			}
		else
		{
			pst.close();
			//JOptionPane.showMessageDialog(null, "UPDATE");
			updateProfitFinder(stockpurchaseText);
		}

		objPayment.updateClosingStk();
	
	}
	catch(Exception e)
	{
		
	}
	System.out.println(total);
	
	
	}

		//#############################################################################################################
	
	public void fillComboBoxs(JComboBox st)
	{
		
		try{
			st.removeAllItems();
		

				query="select * from stock";
				
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				st.addItem(rs.getString("Name"));
			
			}
			rs.close();

			pst.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		
	}


	public void fillComboBoxe(JComboBox st)
	{
		
		try{
			st.removeAllItems();
		

			
				query="select * from expense";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				st.addItem(rs.getString("Name"));
			
			}
			rs.close();

			pst.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		
	}
	
	//#######################################DISPLAY FUNCTION##########################################################
	
	public void showStock()
	{
	
		try{
			if(optionBox.getSelectedItem()=="stock")
				query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from stock";
			else
				query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from expense";
			//"select * from stock";

			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

	}
	//#########################################################################################################

	public void createBill()
	{
		
		try{
			PreparedStatement pst=null;
			
			java.sql.DatabaseMetaData dmd=connection.getMetaData();
		ResultSet rs=dmd.getTables(null, null, "bills", null);
		if(rs.next())
		{
			
		}
		else
		{
			query="CREATE TABLE bills (id int(10),name text(20),Quantity int(10),Price int(10) ); ";
			pst=connection.prepareStatement(query);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table bills created successfully");
			
		}
		rs=dmd.getTables(null, null, "profit2", null);
		if(rs.next())
		{
			
		}
		else
		{

			query="CREATE TABLE profit2 (id int(10) ,name text(20) NOT NULL,mobile bigint(20),city text(10),date DATE,Paid int(10),Remaining int(10),Total int(10) ); ";
			pst=connection.prepareStatement(query);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table profit2 created successfully");
			

		}

		rs=dmd.getTables(null, null, "expense", null);
		if(rs.next())
		{
			
		}
		else
		{

			query="CREATE TABLE expense (id int(10) ,name text(20) NOT NULL,mobile bigint(20),city text(10),date DATE,Paid int(10),Remaining int(10),Total int(10) ); ";
			pst=connection.prepareStatement(query);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table expense created successfully");
			

		}
		rs=dmd.getTables(null, null, "expensedaily", null);
		if(rs.next())
		{
			
		}
		else
		{

			query="CREATE TABLE expensedaily (id int(10) ,name text(20) NOT NULL,mobile bigint(20),city text(10),date DATE,Paid int(10),Remaining int(10),Total int(10) ); ";
			pst=connection.prepareStatement(query);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Table expensedaily created successfully");
			

		}

		pst.close();
		rs.close();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, 
					"Table creation failed", "Failure", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

	
	public void comboBoxclick(JComboBox cb)
	{
		
		try{
			scrollPane.setVisible(true);
		//	scrollPane2.setVisible(false);
			if(optionBox.getSelectedItem()=="stock")
				query="select * from stock where name= ?";
			else
				query="select * from expense where name= ?";
			
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, (String)cb.getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				textFieldName.setText(rs.getString("Name"));
				textFieldIds.setText(rs.getString("ID"));
				textFieldNameS.setText(rs.getString("Name"));
				textFieldId.setText(rs.getString("ID"));
				textFieldQuantity.setText(rs.getString("Quantity"));
				textFieldSellPrice.setText(rs.getString("Price"));
				textFieldNames.setText(rs.getString("Name"));
				textFieldNamed.setText(rs.getString("Name"));
				textFieldPurchase.setText(rs.getString("Price"));
				
			}
			
			pst.close();
			rs.close();
		}
		catch(Exception ee){
			
			ee.printStackTrace();
		}

	}
//######################################################Quantity Function#################################################################
	public void quantity()
	{
		quantityArray();
		ns=0;
		ns=names.size();
		is=0;
		
		while(is<ns)
		{
			if(textFieldName.getText().equals(names.get(is)))
				break;
		
			is++;
		}
	
		try{
		

		qt=Integer.parseInt(textFieldQuantity.getText());
		
		if(is!=ns)
			qs=(int)quantArry.get(is);
		
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "QUANTITY");
			
		}
		
	}
	//###########################################QuantityArray Function#########################################################################
	public void quantityArray()
	{
		
		try{
			names=new ArrayList();
			quantArry=new ArrayList();

			names.clear();
			
			quantArry.clear();
			
			if(optionBox.getSelectedItem()=="stock")
				{
				names=namesArray("stock");
				query="select Quantity from stock";
				}
			else
			{	
				names=namesArray("expense");
				query="select Quantity from expense";
			}
			
				
			PreparedStatement pst2=connection.prepareStatement(query);
			ResultSet rs2=pst2.executeQuery();
			
			while(rs2.next())
			{
				String qua=rs2.getString(1);
				quantArry.add(Integer.parseInt(qua));
				
			}
			pst2.close();
			rs2.close();
			
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}
		
	}
	//#########################################################################################################

	public ArrayList namesArray(String tbl)
	{
		try{
			temp=new ArrayList();

			temp.clear();
			query="select Name from "+tbl;
		
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String name=rs.getString(1);
				temp.add(name);
				
			}
			pst.close();
			rs.close();

		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					"395"+R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}
	return temp;	
	}


	
	//#################################################################
	public void date()
	{
		java.util.Date utilDate = new java.util.Date();
	    sqlDate = new java.sql.Date(utilDate.getTime());

	    /*
	     * FOR YESTERDAY
*/	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, 8);
	    sqlDate= new java.sql.Date(calendar.getTimeInMillis());
	     
	}


		

	//####################################INSERT FUNCTION################################################################

	public void insfun()
	{
		PreparedStatement pst,pst2;
		try{
			date();

			
			if((textFieldName.getText().toLowerCase()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
				}

			else if((textFieldDealer.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Please Enter Dealer Name...!", "Failure", JOptionPane.ERROR_MESSAGE);
				} 

			else if((textFieldQuantity.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Quantity is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
				} 

			else if(Integer.parseInt(textFieldQuantity.getText())<=0)
			{
			JOptionPane.showMessageDialog(null, 
					  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else if(textFieldPurchase.getText().isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Purchase Price is missing", "Failure", JOptionPane.ERROR_MESSAGE);
			}  
			else
			{

				
			
				if(optionBox.getSelectedItem()=="stock")
				{	
					
					query="insert into stock (name,Quantity,purchase_price,Price,total_sale_price,total_purchase_price,tax) values (?,?,?,?,?,?,?)";
					query2="insert into stockdaily (name,Date,Quantity,purchase_price,total_purchase_price,Price,total_sale_price,dealer,tax) values (?,?,?,?,?,?,?,?,?)";
					query4="insert into allrecord (name,Date,sale,purchase,dealer,tax) values (?,?,?,?,?,?)";
					
					totalpurchase=Integer.parseInt(textFieldQuantity.getText())*Integer.parseInt(textFieldPurchase.getText());
					totalsell=Integer.parseInt(textFieldQuantity.getText())*Integer.parseInt(textFieldSellPrice.getText());
					total_tax=((totalsell-totalpurchase)*Float.valueOf(gstBox.getSelectedItem().toString()))/100;
					
					pst2=connection.prepareStatement(query2);			
					pst=connection.prepareStatement(query);
					pst.setString(1,textFieldName.getText().toLowerCase());
					pst.setString(2,textFieldQuantity.getText());
					pst.setString(3,textFieldPurchase.getText());
					pst.setString(4,textFieldSellPrice.getText());

					pst.setString(5,Float.toString(totalsell));
					pst.setString(6,Float.toString(totalpurchase));
					pst.setString(7,gstBox.getSelectedItem().toString());
					
					
					pst2.setString(1,textFieldName.getText().toLowerCase());
					pst2.setString(2,sqlDate.toString());
					pst2.setString(3,textFieldQuantity.getText());
					pst2.setString(4,textFieldPurchase.getText());
					pst2.setString(5,Float.toString(totalpurchase));
					pst2.setString(6,textFieldSellPrice.getText());
					pst2.setString(7,Float.toString(totalsell));
					pst2.setString(8,textFieldDealer.getText());
					pst2.setString(9,gstBox.getSelectedItem().toString());
					
					
					pst4=connection.prepareStatement(query4);
					pst4.setString(1,textFieldName.getText().toLowerCase());
					pst4.setString(2,sqlDate.toString());
					pst4.setString(3,Float.toString(totalsell));
					pst4.setString(4,Float.toString(totalpurchase));
					pst4.setString(5,textFieldDealer.getText());
					pst4.setString(6,gstBox.getSelectedItem().toString());
							
					profitFinderInsert(totalpurchase);
				
				}else
				{
					query="insert into expense (name,Quantity,Price,total) values (?,?,?,?)";
					query2="insert into expensedaily (name,Date,Quantity,Price,total) values (?,?,?,?,?)";
					query4="insert into allrecord (name,Date,expense) values (?,?,?)";
					
					totalpurchase=Integer.parseInt(textFieldQuantity.getText())*Integer.parseInt(textFieldPurchase.getText());
					pst2=connection.prepareStatement(query2);			
					
					textFieldSellPrice.setText(Integer.toString(0));
					pst=connection.prepareStatement(query);
					pst.setString(1,textFieldName.getText().toLowerCase());
					pst.setString(2,textFieldQuantity.getText());
					pst.setString(3,textFieldPurchase.getText());
					pst.setString(4,Float.toString(totalpurchase));
					
					pst2.setString(1,textFieldName.getText().toLowerCase());
					pst2.setString(2,sqlDate.toString());
					pst2.setString(3,textFieldQuantity.getText());
					pst2.setString(4,textFieldPurchase.getText());
					pst2.setString(5,Float.toString(totalpurchase));
				

					pst4=connection.prepareStatement(query4);
					pst4.setString(1,textFieldName.getText().toLowerCase());
					pst4.setString(2,sqlDate.toString());
					pst4.setString(3,Float.toString(totalpurchase));
					
				}
					
				
				
				//objp.insfun(totalpurchase);
				
				quantity();
				if(is==ns)
				{	
					pst2.execute();
					pst2.close();
					pst4.execute();
					pst4.close();
					
					pst.execute();
					
					pst.close();
					showStock();					
	
				}
				else
				{
					pst.close();
					
					pst4.execute();
					pst4.close();
					
					pst2.execute();
					pst2.close();					
					
					insupdatefun(qs+qt);
					showStock();

						
				}
				if(optionBox.getSelectedItem()=="stock")
				{
				fillComboBoxs(comboBox);
				fillComboBoxs(comboBoxdel);
				fillComboBoxs(comboBoxsrch);
				}
				else
				{fillComboBoxe(comboBox);
				fillComboBoxe(comboBoxdel);
				fillComboBoxe(comboBoxsrch);
				
					
					
				}
				table.getSelectionModel().setSelectionInterval(4, 4);
				table.scrollRectToVisible(new Rectangle(table.getCellRect(4, 0, true)));

				
			}
			//JOptionPane.showMessageDialog(null,"Data Saved..........!");

			
		}
		catch(Exception R2){
			JOptionPane.showMessageDialog(null, 
					"insert problem :"+R2.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
			R2.printStackTrace();
		}

		
	}
	//########################################DELETE FUNCTION##############################################################
	public void deletefun()
	{
		String name;
		int quantity=0,purchase=0,totalp=0,sell=0,totals=0;
		try{
			query3="select quantity,purchase_price,price from stock where Name='"+textFieldNamed.getText()+"'";
			PreparedStatement pst=connection.prepareStatement(query3);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				quantity=Integer.parseInt(rs.getString("Quantity"));
				purchase=Integer.parseInt(rs.getString("purchase_price"));
				sell=Integer.parseInt(rs.getString("price"));
				
			}
			pst.close();
			rs.close();
			query3="select dealer from stockdaily where Name='"+textFieldNamed.getText()+"'";
			pst=connection.prepareStatement(query3);
			String dealern="";
			rs=pst.executeQuery();
			rs.close();
			pst.close();
			while(rs.next())
			{
				dealern=rs.getString("dealer");
				
			}
			
			query2="insert into stockdaily (name,Date,Quantity,purchase_price,total_purchase_price,Price,total_sale_price,dealer) values (?,?,?,?,?,?,?,?)";

			query4="insert into allrecord (name,Date,dealer,purchase) values (?,?,?,?)";

			totalsell=(0-quantity)*sell;
			totalpurchase=(0-quantity)*purchase;
			date();
			PreparedStatement pst2=connection.prepareStatement(query2);
			pst2.setString(1,textFieldNamed.getText().toLowerCase());
			pst2.setString(2,sqlDate.toString());
			pst2.setString(3,Integer.toString(quantity));
			pst2.setString(4,Integer.toString(purchase));
			pst2.setString(5,Float.toString(totalpurchase));
			pst2.setString(6,Integer.toString(sell));
			pst2.setString(7,Float.toString(totalsell));
			pst2.setString(8,dealern);
			
			pst4=connection.prepareStatement(query4);
			
			
			pst4.setString(1,textFieldNamed.getText().toLowerCase());
			pst4.setString(2,sqlDate.toString());
			pst4.setString(3,dealern);
			pst4.setString(4,Float.toString(totalpurchase));
			
			
			if(optionBox.getSelectedItem()=="stock")
				query="delete from stock where Name='"+textFieldNamed.getText()+"'";
			else
				query="delete from expense where Name='"+textFieldNamed.getText()+"'";
			pst=connection.prepareStatement(query);
			if((textFieldNamed.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "ID is missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
				pst.close();
				pst2.close();
				pst4.close();
				
				}
			else
			{
			pst.execute();
			pst.close();
			
			pst2.execute();
			pst2.close();
			
			pst4.execute();
			pst4.close();
			
			showStock();					
			
			}
			if(optionBox.getSelectedItem()=="stock")
			{
			fillComboBoxs(comboBox);
			fillComboBoxs(comboBoxdel);
			fillComboBoxs(comboBoxsrch);
			}
			else
			{fillComboBoxe(comboBox);
			fillComboBoxe(comboBoxdel);
			fillComboBoxe(comboBoxsrch);
			
				
				
			}
			
			
		}
		catch(Exception R){
			JOptionPane.showMessageDialog(null, 
					"Duplicate Entry Or ID may be wrong", "Failure", JOptionPane.ERROR_MESSAGE);
			R.printStackTrace();
		}

			
		}
	
	//###########################################SEARCH FUNCTION###########################################################
	
	public void searchfun()
	{
		try{
			if(optionBox.getSelectedItem()=="stock")
				query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from stock where name= '"+textFieldNames.getText().toLowerCase()+"'";
			else
				query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from expense where name= '"+textFieldNames.getText().toLowerCase()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

	}
	/*//############################################GET STOCK DATA####################################################
	public void getStockDate()
	{

		try{
			query="select * from stock";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			int squantity,spurchase,stotalpurchase,ssale,stotalsale,stax,stotaltax;
			
			while(rs.next())
			{
			squantity=Integer.parseInt(rs.getString("Quantity"));
			spurchase=Integer.parseInt(rs.getString("Quantity"));
			stotalpurchase=Integer.parseInt(rs.getString("Quantity"));
			ssale=Integer.parseInt(rs.getString("Quantity"));
			stotalsale=Integer.parseInt(rs.getString("Quantity"));
			stax=Integer.parseInt(rs.getString("Quantity"));
			stotaltax=Integer.parseInt(rs.getString("Quantity"));
			
			}
			rs.close();

			pst.close();
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
		}

	}
	
	*/
	//################################################INSUPDATE FUNCTION######################################################
	
	public void insupdatefun(int q)
	{
		String total,query;
		total=Integer.toString(q);
		
		try{

			if(optionBox.getSelectedItem()=="stock")
				query="Update stock set Quantity='"+total+"' where Name='"+textFieldName.getText()+"'";
			else
				query="Update expense set Quantity='"+total+"' where Name='"+textFieldName.getText()+"'";
			PreparedStatement pst=connection.prepareStatement(query);
				

			if(Integer.parseInt(textFieldQuantity.getText())<0)
			{
			JOptionPane.showMessageDialog(null, 
					  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else{
				pst.execute();
				
			showStock();	
			if(optionBox.getSelectedItem()=="stock")
			{
			fillComboBoxs(comboBox);
			fillComboBoxs(comboBoxdel);
			fillComboBoxs(comboBoxsrch);
			}
			else
			{fillComboBoxe(comboBox);
			fillComboBoxe(comboBoxdel);
			fillComboBoxe(comboBoxsrch);
			
				
				
			}
			
			//JOptionPane.showMessageDialog(null,"Data Updated Successfully..........!");
			}
			pst.close();
			
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					"1016 :"+R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}

		
		
	}
	
	//######################################################################################################


	//################################################UPDATE FUNCTION######################################################
	
	public void updatefun()
	{
		int first=0,last=0,purchase=0;
		try{
			query3="select quantity,purchase_price from stock where Name='"+textFieldNameS.getText()+"'";
			PreparedStatement pst=connection.prepareStatement(query3);
			
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				first=Integer.parseInt(rs.getString("Quantity"));
				purchase=Integer.parseInt(rs.getString("purchase_price"));
			}
			rs.close();
			pst.close();
			query3="select dealer from stockdaily where Name='"+textFieldNameS.getText()+"'";
			PreparedStatement  pst3=connection.prepareStatement(query3);
			String dealern="";
			
			rs=pst3.executeQuery();
			while(rs.next())
			{
				dealern=rs.getString("dealer");
				
			}
			
			
			rs.close();
			pst3.close();
			last=Integer.parseInt(textFieldQuantity.getText());
			if(optionBox.getSelectedItem()=="stock")
				query="Update stock set Name='"+textFieldName.getText().toLowerCase()+"',Quantity='"+textFieldQuantity.getText()+"',tax='"+gstBox.getSelectedItem().toString()+"',Price='"+textFieldSellPrice.getText()+"' where Name='"+textFieldNameS.getText()+"'";
			else
				query="Update expense set Name='"+textFieldName.getText().toLowerCase()+"',Quantity='"+textFieldQuantity.getText()+"',Price='"+textFieldSellPrice.getText()+"' where Name='"+textFieldNameS.getText()+"'";
			
			pst=connection.prepareStatement(query);
				
			if((textFieldId.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "ID is missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
			}
		else if((textFieldName.getText().toLowerCase()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
			}

		else if((textFieldQuantity.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Quantity is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
			} 

		else if((textFieldSellPrice.getText()).isEmpty())
			{
			JOptionPane.showMessageDialog(null, 
					  "Price is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
			} 
		else if(Integer.parseInt(textFieldQuantity.getText())<0)
		{
		JOptionPane.showMessageDialog(null, 
				  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
		} 
		else
		{
			if(textFieldPurchase.getText().isEmpty())
			{
				textFieldPurchase.setText(Integer.toString(purchase));
			}
		pst.execute();
		pst.close();
		query2="insert into stockdaily (name,Date,Quantity,purchase_price,total_purchase_price,Price,total_sale_price,dealer,tax) values (?,?,?,?,?,?,?,?,?)";

		query3="insert into allrecord (name,Date,purchase,dealer,tax) values (?,?,?,?,?)";
		pst3=connection.prepareStatement(query3);
		totalsell=(last-first)*Integer.parseInt(textFieldSellPrice.getText());
		totalpurchase=(last-first)*Integer.parseInt(textFieldPurchase.getText());
		date();
		PreparedStatement pst2=connection.prepareStatement(query2);
		pst2.setString(1,textFieldName.getText().toLowerCase());
		pst2.setString(2,sqlDate.toString());
		pst2.setString(3,Integer.toString(last-first));
		pst2.setString(4,textFieldPurchase.getText());
		pst2.setString(5,Float.toString(totalpurchase));
		pst2.setString(6,textFieldSellPrice.getText());
		pst2.setString(7,Float.toString(totalsell));
		pst2.setString(8,dealern);
		pst2.setString(9,gstBox.getSelectedItem().toString());
		
		pst3.setString(1,textFieldName.getText().toLowerCase());
		pst3.setString(2,sqlDate.toString());
		pst3.setString(3,Float.toString(totalpurchase));
		pst3.setString(4,dealern);
		pst3.setString(5,gstBox.getSelectedItem().toString());
		
		//objp.insfun(totalpurchase);
		if(last-first !=0)
			pst2.execute(); 	
		pst2.close();
		pst3.execute();
		pst3.close();

		showStock();					
		}


			
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					"874 :"+R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}
		if(optionBox.getSelectedItem()=="stock")
		{
		fillComboBoxs(comboBox);
		fillComboBoxs(comboBoxdel);
		fillComboBoxs(comboBoxsrch);
		}
		else
		{fillComboBoxe(comboBox);
		fillComboBoxe(comboBoxdel);
		fillComboBoxe(comboBoxsrch);
		
			
			
		}
				
	}
	
	//######################################################################################################
	public stock() {
		connection=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1433, 1093);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();


		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 102));
		panel.setBounds(0, 0, (int)width, (int)height);
		contentPane.add(panel);
		panel.setLayout(null);
	
		
		

		JButton btnTransactions = new JButton("Transactions");
		btnTransactions.setBackground(new Color(51, 255, 204));
		btnTransactions.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTransactions.setBounds(807, 102, 129, 25);
		panel.add(btnTransactions);
		
		JButton btnBill = new JButton("Bill");
		btnBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//createBill();
				int no=1;
			try{
				query="select id from record order by id desc limit 1 offset 1;";
			
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				while(rs.next())
					no=Integer.parseInt(rs.getString(1));
				no=no+2;
			
				pst.close();
				rs.close();
			//	connection.close();


			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,"945"+ e);
			}
		    try
		    {
		  //  _deleteTableDtataStmt = connection.createStatement();

		    //String _deleteTableData ="DELETE FROM bill";
		    //_deleteTableDtataStmt.executeUpdate(_deleteTableData);

			query="DELETE FROM bill";
			
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			pst.close();
		    }
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "ERROR in Truncating="+e);
		}

			frame.dispose();	
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();

			bill bl1=new bill(no,gc);
				bl1.setVisible(true);
				
				bl1.setLocationRelativeTo(null);  // Explicit JFrame if outside JFrame constructor.
				bl1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				//frame.setVisible(false);
				dispose();
				frame.setVisible(false);				
				
			
		}
		});
		btnBill.setBackground(new Color(51, 255, 204));
		btnBill.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBill.setBounds(668, 102, 97, 25);
		panel.add(btnBill);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBackground(new Color(51, 255, 204));
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPrint.setBounds(961, 102, 88, 25);
		panel.add(btnPrint);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.info);
		panel_1.setBounds(0, 156, (int)width,(int)height);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, new Color(153, 102, 204), new Color(153, 255, 0), new Color(255, 0, 0), new Color(255, 204, 102)));
		scrollPane.setToolTipText("STOCK");
		scrollPane.setBounds(595, 56, (int)width*5/8, (int)height*5/8);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					if(optionBox.getSelectedItem()=="stock")
						query="select * from stock where ID='"+id+"'";
					else
						query="select * from expense where ID='"+id+"'";
					PreparedStatement pst=connection.prepareStatement(query);

					ResultSet rs=pst.executeQuery();
					
					while(rs.next())
					{
						textFieldName.setText(rs.getString("Name"));
						textFieldId.setText(rs.getString("ID"));
						textFieldQuantity.setText(rs.getString("Quantity"));
						textFieldSellPrice.setText(rs.getString("Price"));
						textFieldNameS.setText(rs.getString("Name"));
						textFieldIds.setText(rs.getString("ID"));
						textFieldNamed.setText(rs.getString("Name"));
						
					}
					pst.close();
					rs.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.BOLD, 15));
		table.setRowHeight(25);
		
		table.setBackground(new Color(255, 250, 205));
		scrollPane.setViewportView(table);
		optionBox = new JComboBox();

		optionBox.addItem("stock");
		optionBox.addItem("expense");
		
		optionBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(optionBox.getSelectedItem()=="stock")
				{
				fillComboBoxs(comboBox);
				fillComboBoxs(comboBoxdel);
				fillComboBoxs(comboBoxsrch);
				lblSellPrice.setVisible(true);
				textFieldSellPrice.setVisible(true);	
				
				}
				else
				{fillComboBoxe(comboBox);
				fillComboBoxe(comboBoxdel);
				fillComboBoxe(comboBoxsrch);
				lblSellPrice.setVisible(false);
					
				textFieldSellPrice.setVisible(false);	
				}
				
				showStock();
			}
		});
		optionBox.setFont(new Font("Tahoma", Font.BOLD, 20));
		optionBox.setForeground(new Color(139, 0, 0));
		optionBox.setBounds(31, 18, 149, 37);
		panel_1.add(optionBox);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(12, 187, 400, 522);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setVisible(false);

		JButton btnShow = new JButton("Show");
		btnShow.setBackground(new Color(51, 255, 204));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.setVisible(true);
			//	panel_4.setVisible(false);
				panelDelete.setVisible(false);
				panelUpdate.setVisible(false);
				showStock();
					}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnShow.setBounds(12, 102, 97, 25);
		panel.add(btnShow);
		
		panelUpdate = new JPanel();
		panelUpdate.setBackground(SystemColor.menu);
		panelUpdate.setBounds(0, 13, 400, 53);
		panel_2.add(panelUpdate);
		panelUpdate.setLayout(null);
		
		JLabel label = new JLabel("Name to Update");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(12, 13, 157, 22);
		panelUpdate.add(label);
		
		textFieldNameS = new JTextField();
		textFieldNameS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				
				cmpltObj.quantityArray();;
				cmpltObj.keypress(evt, textFieldNameS);
			}
		});
		
		textFieldNameS.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNameS.setColumns(10);
		textFieldNameS.setBackground(new Color(153, 204, 102));
		textFieldNameS.setBounds(181, 13, 207, 22);
		panelUpdate.add(textFieldNameS);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(SystemColor.control);
		panel_4.setBounds(0, 66, 401, 462);
		panel_2.add(panel_4);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_1.setBounds(32, 168, 89, 16);
		panel_4.add(label_1);
		
		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			
				public void keyPressed(java.awt.event.KeyEvent evt) {
						
				
				cmpltObj.quantityArray();;
				cmpltObj.keypress(evt, textFieldName);
			}
				}
			
		);
		
		textFieldName.setColumns(10);
		textFieldName.setBounds(158, 165, 215, 22);
		panel_4.add(textFieldName);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds(158, 208, 215, 22);
		panel_4.add(textFieldQuantity);
		
		JLabel label_2 = new JLabel("Quantity");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(32, 211, 89, 16);
		panel_4.add(label_2);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxclick(comboBox);
			}
		});
		comboBox.setBounds(32, 23, 127, 22);
		panel_4.add(comboBox);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblId.setBounds(32, 75, 89, 16);
		panel_4.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
		textFieldId.setBounds(158, 72, 215, 22);
		panel_4.add(textFieldId);
		
		textFieldSellPrice = new JTextField();
		textFieldSellPrice.setColumns(10);
		textFieldSellPrice.setBounds(158, 297, 215, 22);
		panel_4.add(textFieldSellPrice);
		
		lblSellPrice = new JLabel("Sell Price");
		lblSellPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSellPrice.setBounds(32, 300, 89, 16);
		panel_4.add(lblSellPrice);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(33, 362, 97, 25);
		panel_4.add(btnSave);
		btnSave.setBackground(new Color(51, 255, 0));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try{
				insfun();
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(null,"1196 :"+ee);
			}
			
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnUpdate_1 = new JButton("Update");
		btnUpdate_1.setBounds(205, 362, 97, 25);
		panel_4.add(btnUpdate_1);
		btnUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					updatefun();
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(null,"1213 :"+ee);
				}
				}
		});
		btnUpdate_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate_1.setBackground(new Color(51, 255, 0));
		
		JLabel lblpurchase = new JLabel("Purchase Price");
		lblpurchase.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblpurchase.setBounds(32, 255, 127, 16);
		panel_4.add(lblpurchase);
		
		textFieldPurchase = new JTextField();
		textFieldPurchase.setColumns(10);
		textFieldPurchase.setBounds(158, 253, 215, 22);
		panel_4.add(textFieldPurchase);
		
		JLabel lblDealer = new JLabel("Dealer");
		lblDealer.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDealer.setBounds(32, 126, 89, 16);
		panel_4.add(lblDealer);
		
		textFieldDealer = new JTextField();
		textFieldDealer.setColumns(10);
		textFieldDealer.setBounds(158, 124, 215, 22);
		panel_4.add(textFieldDealer);
		
		gstBox = new JComboBox();
		gstBox.setBounds(246, 23, 127, 22);
		panel_4.add(gstBox);
		
		panelDelete = new JPanel();
		panelDelete.setBackground(new Color(204, 102, 51));
		panelDelete.setBounds(12, 181, 400, 200);

		panelDelete.setVisible(false);
		panel_1.add(panelDelete);
		panelDelete.setLayout(null);
		
		JLabel lblId_delete = new JLabel("Name ");
		lblId_delete.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId_delete.setBounds(12, 100, 126, 22);
		panelDelete.add(lblId_delete);
		
		textFieldNamed = new JTextField();
		textFieldNamed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				cmpltObj.quantityArray();;
			cmpltObj.keypress(e, textFieldNamed);
			}
		});
		textFieldNamed.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNamed.setColumns(10);
		textFieldNamed.setBackground(new Color(153, 204, 102));
		textFieldNamed.setBounds(164, 100, 176, 22);
		panelDelete.add(textFieldNamed);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					deletefun();
				}
				catch(Exception ee)
				{
					JOptionPane.showMessageDialog(null,"1275 :"+ee);
				}
			}
		});
		btnDelete_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete_1.setBackground(new Color(204, 204, 255));
		btnDelete_1.setBounds(53, 150, 97, 25);
		panelDelete.add(btnDelete_1);
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBackground(new Color(204, 204, 153));
		panelSearch.setBounds(12, 181, 400, 350);
		panel_1.add(panelSearch);

		panelSearch.setVisible(false);
		panelSearch.setLayout(null);
		
		JLabel lblNames = new JLabel("Name");
		lblNames.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNames.setBounds(22, 150, 78, 22);
		panelSearch.add(lblNames);
		
		textFieldNames = new JTextField();
		textFieldNames.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				cmpltObj.quantityArray();;
				cmpltObj.keypress(e, textFieldNames);
			
			}
		});
		textFieldNames.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldNames.setColumns(10);
		textFieldNames.setBackground(new Color(255, 255, 51));
		textFieldNames.setBounds(174, 150, 176, 22);
		panelSearch.add(textFieldNames);
		
		JLabel lblIds = new JLabel("ID");
		lblIds.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIds.setBounds(22, 100, 78, 22);
		panelSearch.add(lblIds);
		
		textFieldIds = new JTextField();
		textFieldIds.setFont(new Font("Tahoma", Font.BOLD, 14));
		textFieldIds.setColumns(10);
		textFieldIds.setBackground(new Color(255, 255, 51));
		textFieldIds.setBounds(174, 100, 176, 22);
		panelSearch.add(textFieldIds);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				searchfun();
			}
		});
		btnSearch_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch_1.setBackground(new Color(255, 204, 51));
		btnSearch_1.setBounds(81, 250, 97, 25);
		panelSearch.add(btnSearch_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hm=new home();
				
				hm.setVisible(true);

				hm.setExtendedState(JFrame.MAXIMIZED_BOTH); 

				dispose();

				frame.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 25));
		btnBack.setBackground(new Color(51, 255, 204));
		btnBack.setBounds(488, (int)height-280, 149, 78);
		panel_1.add(btnBack);
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showStock();
				
				panel_2.setBackground(SystemColor.control);
				lblId.setText("ID");
				panel_2.setVisible(true);
				panelUpdate.setVisible(false);
				btnSave.setVisible(true);
				btnUpdate_1.setVisible(false);
				panelDelete.setVisible(false);
				lblId.setVisible(false);
				textFieldId.setVisible(false);
				panelSearch.setVisible(false);

			}
		});
		btnInsert.setBackground(new Color(51, 255, 204));
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnInsert.setBounds(144, 102, 97, 25);
		panel.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					showStock();
					panel_2.setVisible(false);
					panelUpdate.setVisible(false);
					panelDelete.setVisible(true);

					panelSearch.setVisible(false);
			
			}
			
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBackground(new Color(51, 255, 204));
		btnDelete.setBounds(396, 102, 97, 25);
		panel.add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				showStock();
				panel_2.setBackground(SystemColor.control);
				lblId.setText("New ID");
				panelUpdate.setVisible(true);
				btnSave.setVisible(false);
				btnUpdate_1.setVisible(true);

				panelDelete.setVisible(false);

				panelSearch.setVisible(false);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdate.setBackground(new Color(51, 255, 204));
		btnUpdate.setBounds(264, 102, 97, 25);
		panel.add(btnUpdate);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBackground(new Color(51, 255, 204));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_2.setVisible(false);
				panelUpdate.setVisible(false);
				panelDelete.setVisible(false);
				panelSearch.setVisible(true);
				showStock();
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSearch.setBounds(535, 102, 97, 25);
		panel.add(btnSearch);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(238, 232, 170));
		panel_3.setBounds(0, 13,(int)width,(int)height);
		panel.add(panel_3);
		panel_3.setLayout(null);

		comboBoxdel= new JComboBox();
		comboBoxdel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxclick(comboBoxdel);
			}
		});
		comboBoxdel.setBounds(48, 40, 127, 22);
		panelDelete.add(comboBoxdel);


		comboBoxsrch= new JComboBox();
		comboBoxsrch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxclick(comboBoxsrch);
			}
		});
		comboBoxsrch.setBounds(48, 40, 127, 22);
		panelSearch.add(comboBoxsrch);
		
		JLabel lblDarkshadowMart = new JLabel("DarkShadow Mart");
		lblDarkshadowMart.setForeground(new Color(255, 0, 0));
		lblDarkshadowMart.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 55));
		lblDarkshadowMart.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarkshadowMart.setBounds(161, 13, 684, 50);
		panel_3.add(lblDarkshadowMart);
		if(optionBox.getSelectedItem()=="stock")
		{
		fillComboBoxs(comboBox);
		fillComboBoxs(comboBoxdel);
		fillComboBoxs(comboBoxsrch);
		}
		else
		{fillComboBoxe(comboBox);
		fillComboBoxe(comboBoxdel);
		fillComboBoxe(comboBoxsrch);
		
			
			
		}
		gstBox.addItem(0);
		
		gstBox.addItem(5);
		gstBox.addItem(12);

		gstBox.addItem(18);
		gstBox.addItem(28);
		
		textFieldId.setVisible(false);
		lblId.setVisible(false);
		btnUpdate_1.setVisible(false);
		

	}
}
