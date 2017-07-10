import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javaGUI.sqlconnection;

public class InsertBills {

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


		//######################################################################################################

	public java.sql.Date sqlDate;

Autocomplete cmpltObj=new Autocomplete();

private java.sql.Connection connection=null;
//bill bl=new bill();

public InsertBills()
{

	connection=sqlconnection.dbConnector();
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



	//################################ OLD CUSTOMER INSERT IN PROFIT#############################################

		public void existUpdateBill()
	{
		try{
		String query="Update profit set Paid='"+(cmpltObj.pp+cmpltObj.pt)+"',Total='"+(cmpltObj.tp+Integer.parseInt(textFieldTotalBill.getText()))+ "',Remaining='"+textFieldTRemainingBill.getText()+"' where Name='"+textFieldcNameBill.getText()+"'";
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

	public void newInsertBill()
	{
		

		try{
		String query="Insert into profit (Name,city,mobile,Date,Paid,Remaining,Total) values (?,?,?,?,?,?,?)";
		
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,textFieldcNameBill.getText().toLowerCase());
		pst.setString(2,textFieldCityBill.getText().toLowerCase());
		pst.setString(3,textFieldcMobileBill.getText());
		pst.setString(4,cmpltObj.sqlDate.toString());
		pst.setString(5,textFieldPaidBill.getText());
		pst.setString(6,textFieldTRemainingBill.getText());
		pst.setString(7,textFieldTotalBill.getText());

		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "EXIST NEW INSERT =="+e);
		}


	}

	//#########################################INSERT IN RECORD FOR ALL USERS##################################
	public void commonInsertBill()
	{
		

		try{
		String query="Insert into record (Name,city,mobile,Date,Paid,Remaining,Total,tax) values (?,?,?,?,?,?,?,?)";
		float tax=(float) (Float.parseFloat(textFieldTotalBill.getText().toString())*28)/100;
		PreparedStatement pst=connection.prepareStatement(query);
		pst.setString(1,textFieldcNameBill.getText().toLowerCase());
		pst.setString(2,textFieldCityBill.getText().toLowerCase());
		pst.setString(3,textFieldcMobileBill.getText());
		pst.setString(4,cmpltObj.sqlDate.toString());
		pst.setString(5,textFieldPaidBill.getText());
		pst.setString(6,textFieldTRemainingBill.getText());
		pst.setString(7,textFieldTotalBill.getText());
JOptionPane.showMessageDialog(null, "TAX :"+tax);
		pst.setString(7,Float.toString(tax));

		pst.execute();
		pst.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "EXIST COMMON INSERT =="+e);
		}


	}
	//#########################################INSERT FUNCTION FOR CUSTOMERS DETAILS#######################################
		public void custinsfunBill()
		{	
			
			try{
					if((textFieldNameBill.getText().toLowerCase()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Name is Missing \n"+"Enter valid Data", "Failure", JOptionPane.ERROR_MESSAGE);
					}

				else if((textFieldcMobileBill.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter Mobile number...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 

				else if((textFieldCityBill.getText()).isEmpty())
					{
					JOptionPane.showMessageDialog(null, 
							  "Please Enter city...!", "Failure", JOptionPane.ERROR_MESSAGE);
					} 
				else if((textFieldPaidBill.getText()).isEmpty())
					{
					textFieldPaidBill.setText(Integer.toString(0));
					}  
				else
				{
					int ch=cmpltObj.checkCustomer(textFieldNameBill);
					if(ch==1)
					{
						commonInsertBill();
						existUpdateBill();
						//showfun();					
						
					}
					else
					{
						commonInsertBill();
						newInsertBill();
					}
					
				}
				//JOptionPane.showMessageDialog(null,"Data Saved..........!");

				
				
			}
			catch(Exception R2){
				JOptionPane.showMessageDialog(null, 
						R2.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
				R2.printStackTrace();
				
			}

			textFieldNameBill.setText(null);
			textFieldcMobileBill.setText(null);
			textFieldCityBill.setText(null);
			textFieldPaidBill.setText(null);
			textFieldRemainingBill.setText(null);
			textFieldPRemainingBill.setText(null);
			
			textFieldTRemainingBill.setText(null);
			
		}

//=================================================================================================================================================
		
}