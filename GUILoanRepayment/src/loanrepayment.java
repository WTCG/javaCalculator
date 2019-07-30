import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;



public class loanrepayment implements ActionListener {

	//field usages, storing variable names and making of table.
	private JFrame frame;
	private JTextField loanAmount;
	private JTextField interestRate;
	private JTextField lPeriod;
	private static double  loan, months, irate, interestRate1;
	double paymentAmount;
	double pAP;
	double interestDue;
	double oB1;
	DefaultTableModel listTableModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loanrepayment window = new loanrepayment();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loanrepayment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 724, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		loanAmount = new JTextField();
		loanAmount.setBounds(262, 162, 153, 19);
		frame.getContentPane().add(loanAmount);
		loanAmount.setColumns(10);
		
		JLabel lblLoanAmount = new JLabel("Loan Amount");
		lblLoanAmount.setBounds(262, 123, 153, 28);
		frame.getContentPane().add(lblLoanAmount);
		
		JButton btnCalculate = new JButton("Calculate");
		//all methods are done under calculate button. 
		//table format
		String [] columnNames = {"Payment No.",
				"Payment Amount",
				"Principal Amount Paid",
				"Interest Amount Paid",
				"Loan Outstanding Balance"};
		Object [][] rowData = {};
		//creation of table
		listTableModel = new DefaultTableModel(rowData, columnNames);
		
		btnCalculate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (loanAmount.getText().equals("")) {
					loan = 0.0;
					loanAmount.setText("0");
				} else {
				loan = (Double.parseDouble(loanAmount.getText())); 
				}
				if (lPeriod.getText().equals("")) {
					lPeriod.setText("0");
				}else
				{
				months = (Double.parseDouble(lPeriod.getText()));
				}
				if (interestRate.getText().equals("")) { 
					interestRate.setText("0");
				}else
				{
				irate = (Double.parseDouble(interestRate.getText()));
				}
				//interest rate divide by 100
				double interestRate1 = (irate/100)/12; //make interest rate into 0.05 /12 (yearl interest)
				int i; 
				//loop to show list of values. 
				for ( i=1; i <= months; i++) {
				//private double  loan, years, irate;
				//Payment Amount = (Rate x Amount of loan )/[1-[1+Rn]-N]
				//paymentAmount= Math.pow((interestRate*loan)/(1-(1+interestRate)-months), -months);
				paymentAmount = (interestRate1*loan)/ (1-Math.pow((1+interestRate1), -months));
				//PPn = Payment X (1+R)^-(1+N-n)
				pAP = paymentAmount * Math.pow((1+interestRate1),-(1 + months - i)) ;
				//INT = Payment - PP
				interestDue = paymentAmount- pAP;
				//OutstandingBalance = (INT/R) - PaP = OB^(n-1) - PP
				oB1 = (interestDue/interestRate1) - pAP;
				//set maximum numbers
				DecimalFormat df = new DecimalFormat();
				df.setMaximumFractionDigits(2);
				//print values to check if they are correct.
				System.out.println(df.format(paymentAmount));
				System.out.println(df.format(pAP));
				System.out.println(df.format(interestDue));
				System.out.println(df.format(oB1));
				//use table to list values and format decimal and currency
			    listTableModel.addRow(new Object[] {df.format(i),
			    		"£"+ df.format(paymentAmount),
			    		"£"+ df.format(pAP),
			    		"£"+ df.format(interestDue), 
			    		"£"+ df.format(oB1)
			    		});
				}	
			}
		});
		
		btnCalculate.setBounds(279, 405, 117, 25);
		frame.getContentPane().add(btnCalculate);
		
		JLabel lblInterestRate = new JLabel("Interest Rate");
		lblInterestRate.setBounds(262, 193, 153, 29);
		frame.getContentPane().add(lblInterestRate);
		
		interestRate = new JTextField();
		interestRate.setBounds(262, 222, 153, 19);
		frame.getContentPane().add(interestRate);
		interestRate.setColumns(10);
		
		JLabel lblLoanPeriod = new JLabel("Loan Period (Months)");
		lblLoanPeriod.setBounds(262, 253, 153, 26);
		frame.getContentPane().add(lblLoanPeriod);
		
		lPeriod = new JTextField();
		lPeriod.setBounds(262, 281, 153, 19);
		frame.getContentPane().add(lPeriod);
		lPeriod.setColumns(10);
		
		JLabel lblLoans = new JLabel("Loans");
		lblLoans.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 14));
		lblLoans.setBounds(322, 48, 93, 27);
		frame.getContentPane().add(lblLoans);
		
		//table format
		JTable listTable;
	    listTable = new JTable(listTableModel);
	    //listTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    listTable.setCellEditor(null);
	    listTable.setBounds(60, 143, 397, 183);
	    
	    //create new window for calculations
	    JFrame frame = new JFrame();
	    frame.getContentPane().add(new JScrollPane(listTable));
	    frame.setVisible(true);
	    frame.pack();	  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
