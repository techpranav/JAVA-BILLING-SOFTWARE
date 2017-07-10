import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javaGUI.sqlconnection;

public class InsertPayments {


	//#########################################################################
		private JTextField textFieldIdPayment;
		private JTextField textFieldTotalPayment;
		private JTextField textFieldcNamePayment;

		private JTextField textFieldPaidPayment;
		private JTextField textFieldcMobilePayment ;
		private JTextField textFieldTRemainingPayment;
		private JTextField textFieldCityPayment;
		private JTextField textFieldDatePayment;
	
		//######################################################################################################

		public java.sql.Date sqlDate;

		AutocompletePayment cmpltObj=new AutocompletePayment();

		private java.sql.Connection connection=null;
//bill bl=new bill();

public InsertPayments()
{

	connection=sqlconnection.dbConnector();
}



		
}