import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.Constants;

 class DateCombo extends JDialog implements ItemListener, ActionListener  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* These are dropdown for the start date */
    private JComboBox startYear, startMonth, startDay;
    /* These are the dropdowns for the end date */
    private JComboBox endYear, endMonth, endDay;
    /* These are all explicit text fields */
    private JTextField usernameText, sourceFileText, newFileText;
    private JLabel sourceFileLabel;
    /* These are password fields for the administrator login and password */
    private Calendar startDate = Calendar.getInstance();
    /* This is the end date used for the date dropdowns */
    private Calendar endDate = Calendar.getInstance();

    public void historyComponents(JComboBox startYear1,JComboBox startMonth1,JComboBox startDay1,JComboBox endYear1,JComboBox endMonth1,JComboBox endDay1)
    {
    	startYear=startYear1;
    	startMonth=startMonth1;
     	startDay=startDay1;
    
    	endYear=endYear1;
    	endMonth=endMonth1;
     	endDay=endDay1;
    
    }
    /**
     * This method sets up the New File Dialog window
     */
    
    
    public DateCombo() {

      
                setResizable(false);

                setVisible(true);
    }

    public void set()
    {
    	 buildYearsList(startYear);
         startYear.setSelectedIndex(5);
         buildMonthsList(startMonth);
         startMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
         buildDaysList(startDate, startDay, startMonth);
         startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
         startYear.addItemListener(this);
         startMonth.addItemListener(this);
         startDay.addItemListener(this);
         buildYearsList(endYear);
         endYear.setSelectedIndex(5);
         buildMonthsList(endMonth);
         endMonth.setSelectedIndex(endDate.get(Calendar.MONTH));
         buildDaysList(endDate, endDay, endMonth);
         endDay.setSelectedItem(Integer.toString(endDate.get(Calendar.DATE)));
         endYear.addItemListener(this);
         endMonth.addItemListener(this);
         endDay.addItemListener(this);
        
    }
    /**
     * This method builds the list of years for the start
     * date and end date of the semester
     * @param yearsList The combo box containing the years
     */
    private void buildYearsList(JComboBox yearsList) {

        int currentYear = startDate.get(Calendar.YEAR);

        for (int yearCount = 2017 - 5; yearCount <= 2017 + 5; yearCount++)
            yearsList.addItem(Integer.toString(yearCount));
    }

    /**
     * This method builds the list of months for the start
     * date and end date of the semester
     * @param monthsList The combo box containing the months
     */
    private void buildMonthsList(JComboBox monthsList) {

        monthsList.removeAllItems();
        for (int monthCount = 1; monthCount < 13; monthCount++)
            monthsList.addItem(monthCount);
    }

    /**
     * This method builds the list of years for the start
     * date and end date of the semester
     * @param dateIn The current date, which will be used for
     * the initial date of the lists
     * @param daysList The combo box that will contain the days
     * @param monthsList The combo box that will contain the months
     */
    private void buildDaysList(Calendar dateIn, JComboBox daysList, JComboBox monthsList) {

        daysList.removeAllItems();
        dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
        int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int dayCount = 1; dayCount <= lastDay; dayCount++)
            daysList.addItem(Integer.toString(dayCount));
    }

    /**
     * This method is called when a dropdown selection
     * changes
     * @param event This occurs when a dropdown changes values
     */
    public void itemStateChanged(ItemEvent event) {

        if (event.getSource() == startYear &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int year = Integer.parseInt((String)startYear.getSelectedItem());
            startDate.set(Calendar.YEAR, year);
            startMonth.setSelectedIndex(0);
            startDate.set(Calendar.MONTH, 0);
            buildDaysList(startDate, startDay, startMonth);
            startDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == startMonth &&
            event.getStateChange() == ItemEvent.SELECTED) {

            startDate.set(Calendar.MONTH, startMonth.getSelectedIndex());
            buildDaysList(startDate, startDay, startMonth);
            startDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == startDay &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int day = Integer.parseInt((String)startDay.getSelectedItem());
            startDate.set(Calendar.DATE, day);
        }
        else if (event.getSource() == endYear &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int year = Integer.parseInt((String)endYear.getSelectedItem());
            endDate.set(Calendar.YEAR, year);
            endMonth.setSelectedIndex(0);
            endDate.set(Calendar.MONTH, 0);
            buildDaysList(endDate, endDay, endMonth);
            endDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == endMonth &&
            event.getStateChange() == ItemEvent.SELECTED) {

            endDate.set(Calendar.MONTH, endMonth.getSelectedIndex());
            buildDaysList(endDate, endDay, endMonth);
            endDate.set(Calendar.DATE, 1);
        }
        else if (event.getSource() == endDay &&
            event.getStateChange() == ItemEvent.SELECTED) {

            int day = Integer.parseInt((String)endDay.getSelectedItem());
            endDate.set(Calendar.DATE, day);
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
 }

	