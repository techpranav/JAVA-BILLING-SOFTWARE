import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.DatabaseMetaData;
//import com.mxrck.autocompleter.TextAutoCompleter;
import com.mysql.jdbc.Statement;

import javaGUI.sqlconnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;


//import org.controlsfx.control.textfield.TextFields;
import javafx.scene.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
public class bill extends JFrame {

	
	static GraphicsEnvironment ge ;
	static GraphicsDevice gs ;
    static GraphicsConfiguration gc ;
    AutocompletePayment objPayment=new AutocompletePayment();
	
	Autocomplete cmpltObj=new Autocomplete();
	InsertBills insert=new InsertBills();
	private JPanel contentPane;
	public JTextField textFieldName;
	public JTextField textFieldQuantity;
	public ArrayList billNamesArray,quantArry,purchaseList;
	static ArrayList purchase=new ArrayList();
	
	public JTextField textFieldLeft;
	//private java.sql.Date sqlDate;
	private JComboBox<String> comboBoxName ;
	public java.sql.Connection connection=null;
	private JTable table;
	private JTable table2;
	private static double width,currentResolutionWidth;
	private static double height,referenceResolutionHeight,currentResolutionHeight ,referenceResolutionWidth;
	private String s;
	home hm;
	static bill frame;
	private JPanel panelBill ;
	private JLabel lblTotal;
	private JScrollPane scrollPane,scrollPane2 ;
	private static JButton btnAdd;
	private JLabel lblBillno;
	/**
	 * Launch the application.
	 */
	//stock stk=new stock();
	private String query,query2,query3,query4;
	PreparedStatement pst1,pst2,pst3,pst4;
	ResultSet rs1,rs2,rs3,rs4;
	public JTextField textFieldId;
	public JTextField textFieldPrice;
	public JTextField textFieldTotal;
	public JTextField textFieldcName;
	public JTextField textFieldPaid;
	public JTextField textFieldRemaining;
	public JTextField textFieldcMobile ;
	public JTextField textFieldPRemaining;
	public JTextField textFieldTRemaining;
	float saleProfitFinder,purchaseProfitFinder,stockpurchase,clsStk,opnStk,total;
	public JTextField textFieldCity;
	//bill frame ;
	public JTextField textFieldDate;
	float sale_price,purchase_price,abnormal,stockpurchase1,opening_stk,closing_stk,new_closing_stk,newStockPurchase,previous_closing_stk,new_purchase_price;
	float tax,taxProfitFinder;
	//==============================================================
	public static synchronized int getWidthForCurrentResolution(int width1) {
	    return (int)((currentResolutionWidth / referenceResolutionWidth) * width1);
	}
	
	public static synchronized int getHeightForCurrentResolution(int height1) {
	    return (int)((currentResolutionHeight/ referenceResolutionHeight) * height1);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					purchase.clear();
					int n=1;
					ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
					gs = ge.getDefaultScreenDevice();
			        gc = gs.getDefaultConfiguration();
			  	  Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
					referenceResolutionWidth=1920;
					referenceResolutionHeight=1080;
			        currentResolutionWidth=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
			        currentResolutionHeight=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
			        width = screenSize.getWidth();
					height = screenSize.getHeight();
					double width1=getWidthForCurrentResolution((int)width);
					double height1=getHeightForCurrentResolution((int)height);
					
					frame = new bill(n,gc);
				
					frame.setSize((int)width,(int)height);
					frame.setResizable(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
					//frame.setUndecorated(true);
					
					frame.getRootPane().setDefaultButton(btnAdd);
					//frame.setLocation((int)(dimension.getWidth()/ 2 - width / 2));
					         
					frame.showCart();
					frame.setLocationRelativeTo(null);  // Explicit JFrame if outside JFrame constructor.
						
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
	
	//##########################################################################################################
	

	
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
				abnormal=rs.getFloat("abnormal");;
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
	//###############################################CALCULATE CLOSING STK FUNCTION###################################################################
	public void calculateClosingStk(Date date)
	{
		getProfitFinderComponents(cmpltObj.sqlDate);
		new_closing_stk=opening_stk+stockpurchase-new_purchase_price;
	}
	
	//#################################SELECT PREVIOUS CLOSING STK FUNCTION #################################################
	public void selectPreviousClosingStk()
	{
		
		try{
			
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


	
	//############################################################################################################
	
	public void getTotalPurchasePrice()
	{
		int count=table2.getRowCount();
		billNamesArray=new ArrayList();
		billNamesArray.clear();
		billNamesArray=cmpltObj.ItemlistArray("bill");
		
		billQuantity();
		purchaseList=new ArrayList();
		purchaseList.clear();
		try{
			
			if(billNamesArray.size()!=0)
			{
				for(int i=0;i<billNamesArray.size();i++)
				{
					String query="select purchase_price from stock where name='"+billNamesArray.get(i)+"'";
					PreparedStatement pst2=connection.prepareStatement(query);
					if(!billNamesArray.isEmpty())
					{
						ResultSet rs2=pst2.executeQuery();
						while(rs2.next())
						{
							String qua=rs2.getString(1);
							purchaseList.add(Integer.parseInt(qua));
					
						}
						rs2.close();
					}

					pst2.close();

				}			
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "error in billQuantity :"+e);
			e.printStackTrace();
		}

		System.out.println(billNamesArray);
		System.out.println(purchaseList);
		System.out.println(quantArry);
		
	//	System.out.println(name);
	}
	
	
	public void billQuantity()
	{
		quantArry=new ArrayList();
		quantArry.clear();
		try{
		String query="select Quantity from bill";

		PreparedStatement pst2=connection.prepareStatement(query);
		ResultSet rs2=pst2.executeQuery();
		String qua;
		while(rs2.next())
		{
			qua=rs2.getString(1);
			quantArry.add(Integer.parseInt(qua));

		}
		pst2.close();
			
		rs2.close();		
		
		
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, "error in billQuantity :"+e);
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
	public void getDateProfitFinder(Date date2)
	{
		try{
			String query="select * from profitfinder where date='"+date2+"' ;";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				purchaseProfitFinder=rs.getInt("purchase_price");
				saleProfitFinder=rs.getFloat("sale_price");
				stockpurchase=rs.getFloat("stockpurchase");
				taxProfitFinder=rs.getFloat("tax");
			}
			pst.close();
			rs.close();
			}
			catch(Exception e)
			{	
				JOptionPane.showMessageDialog(null, "EXIST EXIST INSERT =="+e);
			}

	}
	public void updateProfitFinder()
	{
		try
		{
			cmpltObj.date();
			getDateProfitFinder(cmpltObj.sqlDate);
			calculateClosingStk(cmpltObj.sqlDate);

		String query="Update profitfinder set purchase_price='"+(purchaseProfitFinder+total)+"',sale_price='"+(saleProfitFinder+Integer.parseInt(textFieldTotal.getText()))+"' where date='"+cmpltObj.sqlDate+"';";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();

		pst.close();
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, "update profit finder =="+e);
		}

	}
	//#########################################################################################################################
	public void profitFinderInsert()
	{

	getTotalPurchasePrice();
	int size=billNamesArray.size();
	total=0;
	int ch;
	for(int i=0;i<size;i++)
	{
		float p=Integer.parseInt(quantArry.get(i).toString())*Integer.parseInt(purchaseList.get(i).toString());
		total+=p;
	}
	
	try{
		cmpltObj.date();
		ch=checkDate();
		selectPreviousClosingStk();
		new_purchase_price=total;
		calculateClosingStk(cmpltObj.sqlDate);
		String query1="select * from profitfinder where date='"+cmpltObj.sqlDate+"';";
		PreparedStatement pst1=connection.prepareStatement(query1);
		ResultSet rs=pst1.executeQuery();
		while(rs.next())
		{
		taxProfitFinder=Float.parseFloat(rs.getString("tax"));
		}
	
		String query="Insert into profitfinder (date,sale_price,purchase_price,opening_stk,tax) values (?,?,?,?,?)";
		
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,cmpltObj.sqlDate.toString());
		pst.setString(2,textFieldTotal.getText());
		pst.setString(3,Float.toString(total));
		pst.setString(4,Float.toString(previous_closing_stk));
		pst.setString(5,Float.toString(taxProfitFinder+tax));
		
		
		
		if(ch!=1)
			{
			pst.execute();
			pst.close();
			}
		else
		{
			pst.close();
			updateProfitFinder();
		}
		
		objPayment.updateClosingStk();
	
	}
	catch(Exception e)
	{
		
	}
	System.out.println(total);
	
	
	}
	//################################ OLD CUSTOMER INSERT IN PROFIT#############################################

	
		public void existUpdate()
	{
		try{
		String query="Update profit set Paid='"+(cmpltObj.pp+cmpltObj.pt)+"',Total='"+(cmpltObj.tp+Integer.parseInt(textFieldTotal.getText()))+ "',Remaining='"+textFieldTRemaining.getText()+"' where Name='"+textFieldcName.getText()+"'";
		PreparedStatement pst=connection.prepareStatement(query);
		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{	
			JOptionPane.showMessageDialog(null, "EXIST EXIST INSERT =="+e);
		}

	}
	//################################NEW CUSTOMER INSERT IN PROFIT#############################################

	public void newInsert()
	{
		

		try{
		String query="Insert into profit (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
		
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,textFieldcName.getText().toLowerCase());
		pst.setString(2,textFieldCity.getText().toLowerCase());
		pst.setString(3,textFieldcMobile.getText());
		pst.setString(4,cmpltObj.sqlDate.toString());
		pst.setString(5,textFieldPaid.getText());
		pst.setString(6,textFieldRemaining.getText());
		pst.setString(7,textFieldTotal.getText());

		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "EXIST NEW INSERT =="+e);
		}


	}

	//#########################################INSERT IN RECORD FOR ALL USERS##################################
	public void commonInsert()
	{
		

		try{
		String query="Insert into record (Name,city,mobile,Date,Paid,Remaining,Total,tax) values (?,?,?,?,?,?,?,?)";
		
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,textFieldcName.getText().toLowerCase());
		pst.setString(2,textFieldCity.getText().toLowerCase());
		pst.setString(3,textFieldcMobile.getText());
		pst.setString(4,cmpltObj.sqlDate.toString());
		pst.setString(5,textFieldPaid.getText());
		pst.setString(6,textFieldRemaining.getText());
		pst.setString(7,textFieldTotal.getText());
		pst.setString(8,Float.toString(tax));

		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "EXIST COMMON INSERT =="+e);
		}


	}
	//#########################################INSERT FUNCTION FOR CUSTOMERS DETAILS#######################################
		public void custinsfun()
		{	
			
			try{
					if((textFieldName.getText().toLowerCase()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}

				else if((textFieldcMobile.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter Mobile number...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldCity.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter city...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 
				else
				{
					if((textFieldPaid.getText()).isEmpty())
					{
					textFieldPaid.setText(Integer.toString(0));
					}  
				
					int ch=cmpltObj.checkCustomer(textFieldcName);
					if(ch==1)
					{
						commonInsert();
						existUpdate();
						//showfun();					
						profitFinderInsert();
						
					}
					else
					{


						commonInsert();
						newInsert();
						profitFinderInsert();
					}
					
				}
				//JOptionPane.showMessageDialog(null,"Data Saved..........!");

				
				
			}
			catch(Exception R2){
				JOptionPane.showMessageDialog(null, 
						R2.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
				R2.printStackTrace();
			}

			clearBill();
			showCart();

		}





	//################################################UPDATE STOCK FUNCTION######################################################
	

	public void stkupdatefun(int q)
	{
		String total;
		total=Integer.toString(q);
		try{

		
			//String query="Update stock set ID= '"+textFieldId.getText()+"' , Name= '"+textFieldName.getText()+"' ,Quantity='"+textFieldQuantity.getText()+"', Price='"+textFieldPrice+"' where ID='"+textFieldIDS.getText()+"'";
			String query="Update stock set Quantity='"+total+"' where Name='"+textFieldName.getText()+"'";
	
			PreparedStatement pst=connection.prepareStatement(query);
				

			if(Integer.parseInt(textFieldQuantity.getText())<0)
			{
			JOptionPane.showMessageDialog(null, 
					  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
			pst.close();
			
			}
			else{
			pst.execute();
			pst.close();
			
			showStock();					
			//JOptionPane.showMessageDialog(null,"Data Updated Successfully..........!");
			}
			
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}

		
	}
		//########################################DELETE  BILL FUNCTION##############################################################
	public void deletefun()
	{
		
		try{
			String query="delete from bill where lower(`name`)='"+textFieldName.getText().toLowerCase()+"'";
			
			PreparedStatement pst=connection.prepareStatement(query);
			if((textFieldName.getText()).isEmpty())
				{
				JOptionPane.showMessageDialog(null, 
						  "Name is missing \n"+"Please enter Item name to be deleted", "Failure", JOptionPane.ERROR_MESSAGE);
				}
			else
			{
				cmpltObj.quantity();
				if(cmpltObj.ic==cmpltObj.cartSize)
				{
					JOptionPane.showMessageDialog(null, 
							  "Item not found", "Failure", JOptionPane.ERROR_MESSAGE);
				}
				else{
				
					pst.execute();
					pst.close();
					
					if(textFieldQuantity.getText().isEmpty())
					{
						textFieldQuantity.setText(Integer.toString(0));
						cmpltObj.qt=1;
					}
					
					stkupdatefun(cmpltObj.qc+cmpltObj.qs);
					showCart();
				}
			}
	
			
		}
		catch(Exception R){
			JOptionPane.showMessageDialog(null, 
					"Duplicate Entry Or ID may be wrong", "Failure", JOptionPane.ERROR_MESSAGE);
			R.printStackTrace();
		}

		}


	//################################################UPDATE BILL FUNCTION######################################################
	
	public void billupdatefun()
	{
		try{
			
			
			
			//String query="Update stock set ID= '"+textFieldId.getText()+"' , Name= '"+textFieldName.getText()+"' ,Quantity='"+textFieldQuantity.getText()+"', Price='"+textFieldPrice+"' where ID='"+textFieldIDS.getText()+"'";
			String query="Update bill set Name='"+textFieldName.getText()+"',Quantity='"+textFieldQuantity.getText()+"',Price='"+textFieldPrice.getText()+"' where name='"+textFieldName.getText()+"'";
	
			PreparedStatement pst=connection.prepareStatement(query);
			cmpltObj.quantity();
			if(cmpltObj.ic==cmpltObj.cartSize)
			{
				JOptionPane.showMessageDialog(null, 
						  "Item not found", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(Integer.parseInt(textFieldQuantity.getText())<0)
				{
				JOptionPane.showMessageDialog(null, 
						  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(cmpltObj.qs+cmpltObj.qc-cmpltObj.qt >0)
					{

						pst.execute();
						stkupdatefun(cmpltObj.qs+cmpltObj.qc-cmpltObj.qt);
						showCart();						
					}
					else
						JOptionPane.showMessageDialog(null, 
								"Stock not available", "Failure", JOptionPane.ERROR_MESSAGE);
				}
			}
			pst.close();
			
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}

		
	}
//###############################################Insert Update Function#######################################################

	public void insbillupdatefun()
	{
		try{

			int totalint;
			
			totalint=cmpltObj.qt+cmpltObj.qc;
			

			if(cmpltObj.qt>cmpltObj.qs)
				JOptionPane.showMessageDialog(null, "Out of Stock");
			else
			{
			String totalstr=Integer.toString(totalint);
			String query="Update bill set Name='"+textFieldName.getText()+"',Quantity='"+totalstr+"',Price='"+textFieldPrice.getText()+"' where name='"+textFieldName.getText()+"'";
	
			PreparedStatement pst=connection.prepareStatement(query);
				
			pst.execute();
			pst.close();
			
			stkupdatefun(cmpltObj.qs-cmpltObj.qt);
			showCart();					
			
			}
		}
		catch(Exception R1){
			JOptionPane.showMessageDialog(null, 
					R1, "Failure", JOptionPane.ERROR_MESSAGE);
			
			R1.printStackTrace();
		}

		
	}
	//#########################################################################################################

	
	public void fieldSet()
	{
		PreparedStatement pst3;
		String query3;
		ResultSet rs3;
		try{
		query3="select * from stock where name= ?";
		pst3=connection.prepareStatement(query3);
		pst3.setString(1,textFieldName.getText());
		rs3=pst3.executeQuery();
		while(rs3.next())
		{
			textFieldName.setText(rs3.getString("Name"));
			textFieldId.setText(rs3.getString("ID"));

			textFieldLeft.setText(rs3.getString("Quantity"));
			textFieldPrice.setText(rs3.getString("Price"));
			textFieldPrice.setEditable(false);
		}
		pst3.close();		//JOptionPane.showMessageDialog(null,"Data Saved..........!");
		rs3.close();
		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
		
	//#########################################################################################################
	public void insfun()
	{	try{
			
			fieldSet();
			cmpltObj.quantity();
			if(cmpltObj.is==cmpltObj.stockSize)
			{
				JOptionPane.showMessageDialog(null, 
						  "Item not found", "Failure", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
			
			if(cmpltObj.qt > cmpltObj.qs )
				JOptionPane.showMessageDialog(null," Out of Stock");
			else
			{
				String query="insert into bill (name,Quantity,Price) values (?,?,?)";
				PreparedStatement pst=connection.prepareStatement(query);

				pst.setString(1,textFieldName.getText());
				pst.setString(2,textFieldQuantity.getText());
				pst.setString(3,textFieldPrice.getText());
				
				
			if((textFieldId.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "ID is missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}
				else if((textFieldName.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}

				else if((textFieldQuantity.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Quantity is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldPrice.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Price is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					} 
				else if(Integer.parseInt(textFieldQuantity.getText())<=0)
				{
				JOptionPane.showMessageDialog(null, 
						  "Quantity must be more than 0", "Failure", JOptionPane.ERROR_MESSAGE);
				} 
				else
					{
					
						if(cmpltObj.ic==cmpltObj.cartSize)
						{
							
						pst.execute();
						pst.close();
						stkupdatefun(cmpltObj.qs-cmpltObj.qt);
						fieldSet();
						
						showStock();
						showCart();	
						}
						else
						{
							pst.close();
							insbillupdatefun();
							showStock();
							showCart();
						}				
					}
			
			}
			
			}
				
			
			
		}
		catch(Exception R2){

			JOptionPane.showMessageDialog(null,"Failed..=="+R2);
			showStock();
			showCart();
		}


	
	}
	
	//####################################SHOW STOCK####################################################	
	//#######################################DISPLAY FUNCTION##########################################################
	
	public void showStock()
	{
		try{
			String query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from stock order by name asc";
			//String query="select * from stock";

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
	  public int getSum(){
	        int rowsCount = table2.getRowCount();
	        int sum = 0;
	        for(int i = 0; i < rowsCount; i++){
	            sum = sum+Integer.parseInt(table2.getValueAt(i, 4).toString());
	        }
	        
	        getTotalPurchasePrice();
	    	int size=billNamesArray.size();
	    	total=0;
	    	int ch;
	    	if(size!=0)
	    	{
	    		for(int i=0;i<size;i++)
	    		{
	    		float p=Integer.parseInt(quantArry.get(i).toString())*Integer.parseInt(purchaseList.get(i).toString());
	    		total+=p;
	    		}
	    		if(textFieldTotal.getText().isEmpty())
	    			textFieldTotal.setText("0");
	    		tax=(Float.parseFloat(textFieldTotal.getText())-total)*28/100;
	    		}
	    	
	        return sum;
	    }
		
	public void showCart()
	{
		try{
			
			Dimension d = table2.getPreferredSize();
			
			
			scrollPane2.setBounds((int)(32*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(159*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)(450*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), table2.getRowHeight()*(table2.getRowCount()+1));
			textFieldTotal.setBounds((int)(380*(currentResolutionWidth/referenceResolutionWidth)*(8/5)),(int)((100+ table2.getRowHeight()*(table2.getRowCount()+1)+60)*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 86*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
			lblTotal.setBounds((int)(298*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)((100+table2.getRowHeight()*(table2.getRowCount()+1)+60)*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)(86*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)(20*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
			


			String query="select ID,name,Quantity,Price,Quantity*Price as TOTAL from bill";

			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			table2.setModel(DbUtils.resultSetToTableModel(rs));
			    int t=getSum();
			    
			    
			    textFieldTotal.setText(Integer.toString(t));

			pst.close();
			rs.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "Total =="+e);
		    
			e.printStackTrace();
		}

	}
	
	public void clearBill()
	{
		
		String query="DELETE FROM bill";
		
		try
	    {
			PreparedStatement pst=connection.prepareStatement(query);
			
			pst.executeUpdate();
			pst.close();
			showCart();
	    }
		catch(Exception e2)
		{
		JOptionPane.showMessageDialog(null, "ERROR in Truncating="+e2);
		}
	}
	public void fillComboBox()
	{

		try{
			String query="select * from stock";

			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				comboBoxName.addItem(rs.getString("Name"));
			}
			pst.close();
			rs.close();
		}
		catch(Exception e){
			
			e.printStackTrace();
		}

		
	}
	//########################################################################################################
	public void printPanel(JPanel panel)
	{
		
		Document document = new Document();
		//document.open();
		try {
		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\temp\\"+textFieldcName.getText()+".pdf"));
		    document.open();
		    PdfContentByte contentByte = writer.getDirectContent();
		    PdfTemplate template = contentByte.createTemplate(600, 200);
		    Graphics2D g2 = template.createGraphics(600, 200);
		    panel.print(g2);
		    g2.dispose();
		    contentByte.addTemplate(template, 10, 10);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		finally{
		    if(document.isOpen()){
		        document.close();
		    }
		}
	}
	
	
	//########################################################################################################
	public bill(int n,GraphicsConfiguration gc) {

		//gc.getDevice().
		super("myao",gc);
		gc.getDevice().setFullScreenWindow(this);
		System.out.println();
	  	  Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

				referenceResolutionWidth=1920;
				referenceResolutionHeight=1080;
		        currentResolutionWidth=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
		        currentResolutionHeight=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
		        width = screenSize.getWidth();
				height = screenSize.getHeight();
				double width1 = screenSize.getWidth();
				double height1 = screenSize.getHeight();
				
				System.out.println("referenceResolutionWidth:"+referenceResolutionWidth+ " referenceResolutionHeight: "+referenceResolutionHeight+"  currentResolutionWidth:"+currentResolutionWidth+" currentResolutionHeight"+currentResolutionHeight+ " Width :"+width+" Height:"+height);
				width1=getWidthForCurrentResolution((int)width1);
				height1=getHeightForCurrentResolution((int)height1);
				System.out.println(" Width :"+width+" Height:"+height);


				connection=sqlconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)(100*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(100*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)width, (int)height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds((int)(0*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(0), (int)width, (int)height);
		contentPane.add(panel);
		panel.setLayout(null);
		
	
		JLabel lblDarkshadowMartBill = new JLabel("DarkShadow Mart Bill ");
		lblDarkshadowMartBill.setFont(new Font("Tahoma", Font.BOLD, (int)(28*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblDarkshadowMartBill.setHorizontalAlignment(SwingConstants.CENTER);
		lblDarkshadowMartBill.setBounds((int)(62*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(29*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(1197*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(43*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panel.add(lblDarkshadowMartBill);


		JPanel panelTable = new JPanel();
		panelTable.setBackground(new Color(154, 205, 50));
		panelTable.setBounds((int)(0*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(100*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)width, (int)height-100);
		panel.add(panelTable);
		panelTable.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new BevelBorder(BevelBorder.RAISED, new Color(153, 102, 204), new Color(153, 255, 0), new Color(255, 0, 0), new Color(255, 204, 102)));
		scrollPane.setToolTipText("STOCK");
		scrollPane.setBounds((int)(43*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(333*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(618*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(572*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelTable.add(scrollPane);
		
		table = new JTable();
		table.setRowHeight(25);
		table.setToolTipText("STOCK\r\n");
		table.setFillsViewportHeight(true);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setFont(new Font("Tahoma", Font.BOLD, (int)(16*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		table.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(139, 0, 139), new Color(124, 252, 0), new Color(255, 69, 0), new Color(255, 255, 0)));
		table.setBackground(new Color(255, 239, 213));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int row=table.getSelectedRow();
					String id=(table.getModel().getValueAt(row, 0)).toString();
					
					String query="select * from stock where ID='"+id+"'";
					PreparedStatement pst=connection.prepareStatement(query);

					ResultSet rs=pst.executeQuery();
					s=Integer.toString(cmpltObj.qs);
					while(rs.next())
					{

						textFieldLeft.setEditable(true);
						textFieldName.setText(rs.getString("Name"));
						textFieldId.setText(rs.getString("ID"));
						
						textFieldPrice.setText(rs.getString("Price"));
						textFieldLeft.setText(rs.getString("Quantity"));

						textFieldLeft.setEditable(false);	
					}
					pst.close();
					rs.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(table);

		
		//=============================================================================================
		JPanel panelAdd = new JPanel();
		panelAdd.setBounds((int)(73*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(35*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(1210*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(202*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelTable.add(panelAdd);
		panelAdd.setBackground(new Color(216, 191, 216));
		panelAdd.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblName.setBounds((int)(655*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(55*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(89*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			
				public void keyPressed(java.awt.event.KeyEvent evt) {
						
				cmpltObj.keypress(evt,textFieldName);
			}
				}
			
		);
		textFieldName.setBounds((int)(655*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(92*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(159*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
		textFieldQuantity.setBounds((int)(847*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(92*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(159*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(textFieldQuantity);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblQuantity.setBounds((int)(847*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(55*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 89*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(lblQuantity);
		
		btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmpltObj.quantityArray();
				
				
				try{
					String cost="";
				String query1="select Price from stock where Name='"+textFieldName.getText()+"'";
				PreparedStatement pst1=connection.prepareStatement(query1);

				ResultSet rs1=pst1.executeQuery();
				//int cost=(Int)((textFieldPrice.getText())*(textFieldQuantity.getText()));
			//	System.out.println(rs1.getString(0));
				//JOptionPane.showMessageDialog(null, rs1.getString(0));
			//	int i=0;
				//System.out.println(rs1.getFetchSize());
				
				while(rs1.next())
				{
				cost=rs1.getString("Price");	
				}
				
				
				textFieldPrice.setText(cost);
				rs1.close();
				pst1.close();
				
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "ADD Action ==="+e);
				}
				scrollPane2.setVisible(true);
				insfun();
				showCart();
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		btnAdd.setBackground(new Color(32, 178, 170));
		btnAdd.setBounds((int)(317*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(157*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(btnAdd);		
		comboBoxName = new JComboBox<String>();
		comboBoxName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					scrollPane.setVisible(true);
					scrollPane2.setVisible(true);
					
					String query="select * from stock where name= ?";
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, (String)comboBoxName.getSelectedItem());
					ResultSet rs=pst.executeQuery();
					s=Integer.toString(cmpltObj.qs);
					while(rs.next())
					{
						textFieldName.setText(rs.getString("Name"));
						textFieldId.setText(rs.getString("ID"));
						
						textFieldPrice.setText(rs.getString("Price"));
						textFieldLeft.setText(rs.getString("Quantity"));
						textFieldPrice.setEditable(false);

						textFieldLeft.setEditable(false);
					}
					rs.close();
					pst.close();
				}
				catch(Exception e){
					
					e.printStackTrace();
				}

			}
		});

		comboBoxName.setBounds((int)(60*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(92*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(127*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(comboBoxName);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblId.setBounds((int)(453*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(55*(currentResolutionHeight/referenceResolutionHeight)*(8/5)), (int)(89*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		//
		panelAdd.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				cmpltObj.keypress(evt,textFieldId);
				
			}
		});
		textFieldId.setColumns(10);
		textFieldId.setBounds((int)(453*(currentResolutionWidth/referenceResolutionWidth)*(8/5)),(int)(92*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(159*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		//
		panelAdd.add(textFieldId);
		
		//+===========================================
		

		JLabel lblLeft = new JLabel("Quantity Left");
		lblLeft.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblLeft.setBounds((int)(244*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(55*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(127*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		//
		panelAdd.add(lblLeft);
		
		textFieldLeft = new JTextField();
		textFieldLeft.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				cmpltObj.keypress(evt,textFieldLeft);
				
			}
		});
		textFieldLeft.setColumns(10);
		textFieldLeft.setBounds((int)(244*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(92*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(159*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		
		panelAdd.add(textFieldLeft);
		//+==================================================
		textFieldPrice = new JTextField();
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds((int)(1036*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(90*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(159*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(textFieldPrice);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblPrice.setBounds((int)(1036*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(55*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 89*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(lblPrice);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmpltObj.quantityArray();
				
				billupdatefun();
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		btnEdit.setBackground(new Color(32, 178, 170));
		btnEdit.setBounds((int)(500*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(157*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmpltObj.quantityArray();
							
				deletefun();
				
				showCart();
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		btnDelete.setBackground(new Color(32, 178, 170));
		btnDelete.setBounds((int)(699*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(157*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(btnDelete);
		
		JLabel lblAvailableStock = new JLabel("AVAILABLE STOCK");
		lblAvailableStock.setFont(new Font("Verdana", Font.BOLD, (int)(26*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblAvailableStock.setBounds((int)(175*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(270*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 424*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(44*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelTable.add(lblAvailableStock);
		
		JLabel label = new JLabel("");
		label.setBounds((int)(684*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(557*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),142, (int)(128*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelTable.add(label);

		Image img5=new ImageIcon(this.getClass().getResource("/arw.png")).getImage();
		label.setIcon(new ImageIcon(img5));

		
		cmpltObj.quantityArray();
		textFieldPrice.setDisabledTextColor(Color.WHITE);
		//========================================================

		
		//=========================================================
		JLabel lblSelect = new JLabel("Select Item");
		lblSelect.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		lblSelect.setBounds((int)(60*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(56*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(127*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelAdd.add(lblSelect);
		
		panelBill = new JPanel();
		panelBill.setBounds((int)(1357*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(0*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(514*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(905*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelTable.add(panelBill);
		panelBill.setLayout(null);
		//=============================================================================================

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds((int)(34*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(158*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 574*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(143*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
		panelBill.add(scrollPane2);
		scrollPane2.setViewportBorder(new BevelBorder(BevelBorder.RAISED, new Color(204, 255, 204), new Color(153, 255, 0), new Color(255, 0, 0), new Color(255, 204, 102)));
		scrollPane2.setToolTipText("CART");
		
		table2 = new JTable();
		table2.setFont(new Font("Tahoma", Font.BOLD, (int)(16*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
		
				table2.setRowHeight(25);
				table2.setBackground(new Color(204, 255, 204));
				table2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try{
							int row=table2.getSelectedRow();
							String Name=(table2.getModel().getValueAt(row, 1)).toString();
							s=Integer.toString(cmpltObj.qs);
							
							String query="select * from bill where Name='"+Name+"'";
							PreparedStatement pst=connection.prepareStatement(query);

							ResultSet rs=pst.executeQuery();
							
							while(rs.next())
							{
								textFieldName.setText(rs.getString("Name"));
								textFieldId.setText(rs.getString("ID"));
								
								textFieldPrice.setText(rs.getString("Price"));
								textFieldLeft.setText(rs.getString("Quantity"));

								textFieldLeft.setEditable(false);
							}
							pst.close();
							rs.close();
						}
						catch(Exception e){
							
							e.printStackTrace();
						}

					}
				});
				scrollPane2.setViewportView(table2);
				
				textFieldTotal = new JTextField();
				textFieldTotal.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldTotal.setBackground(new Color(255, 215, 0));
				textFieldTotal.setBounds((int)(396*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(84*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 116*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(29*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldTotal);
				textFieldTotal.setHorizontalAlignment(SwingConstants.TRAILING);
				textFieldTotal.setColumns(10);
				
				lblTotal = new JLabel("Total");
				lblTotal.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblTotal.setBounds((int)(299*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(86*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 89*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(20*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblTotal);
				
				JLabel lblDarkshadowMart = new JLabel("DarkShadow Mart ");
				lblDarkshadowMart.setBounds((int)(198*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(13*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(133*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblDarkshadowMart);
				
				JLabel label_1 = new JLabel("=================================================");
				label_1.setBounds((int)(32*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(42*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 467*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(label_1);
				
				JLabel lblcName = new JLabel("Name");
				lblcName.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblcName.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(71*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(56*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblcName);
				
				textFieldcName = new JTextField();
				textFieldcName.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldcName.setBounds((int)(110*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(71*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldcName);
				textFieldcName.setColumns(10);
				
				textFieldcMobile = new JTextField();
				textFieldcMobile.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldcMobile.setColumns(10);
				textFieldcMobile.setBounds((int)(110*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(110*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldcMobile);
				
				JLabel lblcMobile = new JLabel("Mobile No.");
				lblcMobile.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblcMobile.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)),(int)( 110*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(86*(currentResolutionWidth/referenceResolutionWidth)*(8/5)),(int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblcMobile);
				
				JLabel labelcPaid = new JLabel("Paid");
				labelcPaid.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				labelcPaid.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(734*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(56*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(labelcPaid);
				
				textFieldPaid = new JTextField();
				textFieldPaid.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldPaid.setColumns(10);
				textFieldPaid.setBounds((int)(221*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(723*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldPaid);
				
				JLabel lblcRemaining = new JLabel("Remaining");
				lblcRemaining.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblcRemaining.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(773*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 86*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblcRemaining);
				
				textFieldRemaining = new JTextField();
				textFieldRemaining.setFont(new Font("Tahoma", Font.BOLD,(int)( 13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldRemaining.setColumns(10);
				textFieldRemaining.setBounds((int)(221*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(762*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldRemaining);
				
				JLabel lblSignature = new JLabel("Signature");
				lblSignature.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblSignature.setBounds((int)(409*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(857*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(90*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblSignature);
				
				lblBillno = new JLabel("1");
				lblBillno.setForeground(new Color(255, 0, 0));
				lblBillno.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblBillno.setBounds((int)(443*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(12*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(56*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblBillno);
				
				JLabel lblPreviousRemaining = new JLabel("Previous Remaining");
				lblPreviousRemaining.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblPreviousRemaining.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(803*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 161*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblPreviousRemaining);
				
				textFieldPRemaining = new JTextField();
				textFieldPRemaining.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldPRemaining.setColumns(10);
				textFieldPRemaining.setBounds((int)(221*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(803*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldPRemaining);
				
				textFieldTRemaining = new JTextField();
				textFieldTRemaining.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldTRemaining.setColumns(10);
				textFieldTRemaining.setBounds((int)(221*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(838*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(154*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldTRemaining);
				
				JLabel lblTRemaining = new JLabel("Total");
				lblTRemaining.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblTRemaining.setBounds((int)(12*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(839*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(161*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblTRemaining);
				
				JLabel lblCity = new JLabel("City");
				lblCity.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblCity.setBounds((int)(301*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(71*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(56*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblCity);
				
				textFieldCity = new JTextField();
				textFieldCity.setFont(new Font("Tahoma", Font.BOLD, (int)(13*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				textFieldCity.setColumns(10);
				textFieldCity.setBounds((int)(369*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(71*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(133*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldCity);
				
				JLabel lblDate = new JLabel("Date");
				lblDate.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				lblDate.setBounds((int)(301*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(113*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 56*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(16*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(lblDate);
				
				textFieldDate = new JTextField();
				textFieldDate.setBounds((int)(380*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(110*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 116*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(22*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelBill.add(textFieldDate);
				textFieldDate.setColumns(10);
				textFieldRemaining.setEditable(false);
				textFieldTRemaining.setEditable(false);
				textFieldPRemaining.setEditable(false);
				textFieldDate.setEditable(false);
				JButton btnPrint = new JButton("Print");


				JButton btnBack = new JButton("Back");
				btnBack.setForeground(new Color(255, 255, 255));
				btnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hm=new home();
						
						hm.setVisible(true);

						hm.setExtendedState(JFrame.MAXIMIZED_BOTH); 

						dispose();

						frame.setVisible(false);
					}
				});
				btnBack.setFont(new Font("Segoe UI", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				btnBack.setBackground(new Color(0, 0, 0));
				btnBack.setBounds((int)(1174*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(866*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(109*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(44*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelTable.add(btnBack);

				
				btnPrint.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						printPanel(panelBill);

					}
				});
				//table2.setEnabled(false);
				//table.setEnabled(false);
				//table2.setFocusTraversalKeysEnabled(true);
				//table.setFocusTraversalKeysEnabled(true);
				btnPrint.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				btnPrint.setBounds((int)(790*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(285*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelTable.add(btnPrint);
				
				JButton btnSave = new JButton("Save");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					custinsfun();
					}
				});
				btnSave.setFont(new Font("Tahoma", Font.BOLD, (int)(15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				btnSave.setBounds((int)(1185*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(285*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)(97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelTable.add(btnSave);
				scrollPane2.setVisible(true);
				cmpltObj.billcomponents(textFieldQuantity,textFieldName,textFieldId,textFieldPrice,textFieldTotal,textFieldcName,textFieldPaid,textFieldRemaining,textFieldcMobile,textFieldPRemaining,textFieldTRemaining,textFieldCity,textFieldDate);
				insert.billcomponents(textFieldQuantity,textFieldName,textFieldId,textFieldPrice,textFieldTotal,textFieldcName,textFieldPaid,textFieldRemaining,textFieldcMobile,textFieldPRemaining,textFieldTRemaining,textFieldCity,textFieldDate);
				
				JButton btnClear = new JButton("Clear");
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					    String query="DELETE FROM bill";
						
							try
						    {
								PreparedStatement pst=connection.prepareStatement(query);
								
								pst.executeUpdate();
								pst.close();
								showCart();
						    }
						catch(Exception e2)
						{
							JOptionPane.showMessageDialog(null, "ERROR in Truncating="+e2);
						}

					}
				});
				btnClear.setFont(new Font("Tahoma", Font.BOLD,(int)( 15*(Math.min(currentResolutionHeight / referenceResolutionHeight, currentResolutionWidth / referenceResolutionWidth)))));
				btnClear.setBounds((int)(994*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(285*(currentResolutionHeight/referenceResolutionHeight)*(8/5)),(int)( 97*(currentResolutionWidth/referenceResolutionWidth)*(8/5)), (int)(25*(currentResolutionHeight/referenceResolutionHeight)*(8/5)));
				panelTable.add(btnClear);
				lblBillno.setText(Integer.toString(n));
				

				showStock();
		fillComboBox();
		showCart();
//		auto(textFieldName);
		purchase.clear();
		clearBill();
		showCart();
	
	}
}
