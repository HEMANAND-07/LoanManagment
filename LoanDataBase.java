package com.loanman.loanmanagement;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LoanDatabase {

	Connection con;
	public  LoanDatabase() 
	{
		 try {
			 Class.forName("com.mysql.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root", "xxxxxxx");
		 	 } catch (Exception e) {
		 		 		e.printStackTrace();
		 	 	}
	}

	
public DashBoard fetchDashBoard() {
		
		DashBoard d = new DashBoard();
		String query1 = "select LoanAmount from Loans";
		String query2 = "select * from Customers";
		String query3 = "  select LoanPrincpal , LoanAmount from Loans ";
		int totalcust  = 0;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query1);
			
			int acloans = 0;
			long totLend = 0;
			int activeloans=0;
			while(rs.next())
			{
				acloans++;
				totLend += rs.getInt(1);
				if(rs.getInt(1)!=0)
				{
					activeloans ++;
				}
			}
			d.setActiveLoans(activeloans);
			d.setLendAmt(totLend);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query2);
			while(rs.next())
			{
				totalcust++;
			}
			d.setTotalCust(totalcust);
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		try {
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query3);
			int amount = 0;
			long princpal = 0;
			int estimatedIntrestProfit=0;
			while(rs.next())
			{
				amount = (int) rs.getFloat(2);
				princpal = rs.getInt(1);
				estimatedIntrestProfit+=(amount-princpal);
			}
			d.setIntrestEarnings(estimatedIntrestProfit);
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		return d;
		
	}
	

public List<Loan> viewAllCurrentLoan()   //to view all the loans provided
{
		List <Loan> loans = new ArrayList();
		String query = "select *from Loans";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Loan l = new Loan();
				l.setLoanId(rs.getInt(1));
				l.setCust(getCustRef(rs.getInt(2)));
				l.setLoanAmount(rs.getInt(4));
				l.setLoanIntrest(rs.getInt(5));
				l.setLoanTenure(rs.getInt(6));
				l.setLoanType(rs.getString(7));
				l.setDateOfDisbursement(rs.getString(8));
				loans.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
		
	}
	
	private Customer getCustRef(int cid) {            // to fetch cust data by cust id
		
	    String query = "select * from Customers where CustId ="+cid;
	    Customer c = new Customer();
	    try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				c.setCustomerId(cid);
				c.setCustomerName(rs.getString(2));
				c.setCustomerMobile(rs.getString(3));
				c.setCustomerPan(rs.getString(4));
				c.setDeptAmt(rs.getInt(6));
				c.setCustomerAddress(rs.getString(5));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    return c; 
	}

	
	public Loan viewLoan(int lId )
	{
		Loan l = new Loan();
		String query = "select *from Loans where LoanId ="+lId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
			{
				l.setLoanId(rs.getInt(1));
				l.setCust(getCustRef(rs.getInt(2)));
				l.setLoanAmount(rs.getInt(4));
				l.setLoanIntrest(rs.getFloat(5));
				l.setLoanTenure(rs.getInt(6));
				l.setLoanType(rs.getString(7));
				return l;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
public List<Customer> viewAllCustomers() {
		
		List <Customer> cust = new ArrayList();
		String query = "select *from Customers";
		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				Customer c = new Customer();
				c.setCustomerId(rs.getInt(1));
				c.setCustomerName(rs.getString(2));
				c.setCustomerMobile(rs.getString(3));
				c.setCustomerPan(rs.getString(4));
				c.setCustomerAddress(rs.getString(5));
				c.setDeptAmt(rs.getInt(6));
				cust.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cust;
	}

public void recordPay(Payment payrec) {
	
	String query1 = "insert into paymenthist (LoanId, CustId, dueAmt, notes, dateOfPay )values (?,?,?,?,?)";
	String query2 = "select  deptAmt from Customers where CustId = "+payrec.getCustId();
 	String query3 = " UPDATE Customers SET  deptAmt = ? WHERE CustId = ?";
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	LocalDateTime now = LocalDateTime.now();  
	   int osd = 0;
	try {
		PreparedStatement st1 = con.prepareStatement(query1);
		st1.setInt(1, payrec.getLoanId());
		st1.setInt(2, payrec.getCustId());
		st1.setInt(3, payrec.getAmtPaid());
		st1.setString(4,payrec.getNotes());
		st1.setString(5,dtf.format(now) );
		st1.executeUpdate();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query2);
		if(rs.next())
		{
			osd = rs.getInt(1);
			System.out.println(osd);
			osd -= payrec.getAmtPaid();
			st1 = con.prepareStatement(query3);
			st1.setInt(1, osd);
			st1.setInt(2, payrec.getCustId());
			st1.executeUpdate();
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}



	public void provideNewLoan(Loan lp) throws ParseException {
		 System.out.println(lp.getCust().getCustomerPan());
		   String query1 = "insert into Loans (CustId, LoanPrincpal,LoanAmount,LoanIntrest,TimePeriod,ltype,DateOfDistribution,dueAmt,nextDueDate)values (?,?,?,?,?,?,?,?,?)"; 
		   String query2 = "insert into Customers(custName, custMob, CustAddress,PAN, deptAmt) values (?,?,?,?,?)";
		   String query3 =   "SELECT CustId FROM Customers " + "WHERE PAN = ?";
		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		   LocalDateTime now = LocalDateTime.now();  
		   String date = dtf.format(now);
		   DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		   Calendar c = Calendar.getInstance();
		   c.setTime(formatter.parse(date));
		   c.add(Calendar.MONTH, 1);
		   System.out.println(lp.getCust());
		   try {
				PreparedStatement st1 = con.prepareStatement(query2);

				st1.setString(1, lp.getCust().getCustomerName());
				st1.setString(2, lp.getCust().getCustomerMobile());
				st1.setString(3, lp.getCust().getCustomerAddress());
				st1.setString(4, lp.getCust().getCustomerPan());
				st1.setInt(5, (int) getAmount(lp.getLoanAmount(),lp.getLoanIntrest()));
				st1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}   
		   try {
			   	PreparedStatement st = con.prepareStatement(query1);
			   	Statement st1 = con.createStatement();
			   	PreparedStatement getPan = con.prepareStatement(query3);
			   	getPan.setString(1,lp.getCust().getCustomerPan() );
				ResultSet rs = getPan.executeQuery();
				rs.next();
				 System.out.println(rs.getInt(1));
				st.setInt(1, rs.getInt(1));
				st.setFloat(2, getAmount(lp.getLoanAmount(),lp.getLoanIntrest()));
				st.setInt(3, lp.getLoanAmount());
				st.setInt(5, lp.getLoanTenure());
				st.setFloat(4, lp.getLoanIntrest());
				st.setString(6, lp.getLoanType());
				st.setString(7,dtf.format(now));
				st.setInt(8,dueCalc(lp.getLoanAmount(),lp.getLoanTenure()));
				st.setString(9,formatter.format(c.getTimeInMillis()));
				st.executeUpdate();
			
		   } catch (SQLException e) {
			e.printStackTrace();
		   }
		
		
	}
	
	public float getAmount(int princpal , float intrest)
	{
		return ((princpal * intrest)/100)+princpal;
	}
    public int dueCalc(int amount ,int timeperiod)
    {
    	return amount/timeperiod;
    }
    
    
	public void addressUpdate(Customer cust) //update req for change of address
	{
		System.out.println(cust.getCustomerAddress());
		
		String query = "UPDATE Customers SET Address = ?  WHERE CustId = ?";
        try {
		
        	PreparedStatement st1 = con.prepareStatement(query);
        	st1.setString(1, cust.getCustomerAddress());
        	st1.setInt(2, cust.getCustomerId());
			st1.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public List<Payment> viewAllPayment() {
		
		List <Payment> payments = new ArrayList();
		int cid = 0;
		String sql = "select *from paymenthist";
		
		 
		try {
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				Payment p = new Payment();
			     p.setCustId(rs.getInt(3));
				 p.setLoanId(rs.getInt(2));
				 p.setAmtPaid(rs.getInt(4));
				 p.setNotes(rs.getString(5));
				 p.setDateOfPayment(rs.getString(6));
			    
			        String sql1 = " select  custName from Customers where CustId ="+rs.getInt(1);
				     Statement st1 = con.createStatement();
				     ResultSet rs1 = st1.executeQuery(sql1);
				     if(rs1.next())
				     {
				    	 System.out.println(rs1.getString(1));
				         p.setCustName(rs1.getString(1));
				     }
				
			     payments.add(p);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return payments;
		
	}

	public Boolean delcust(int id) throws SQLException {
		String sql = "delete  from Customers where CustId="+id;
		PreparedStatement st1 = con.prepareStatement(sql);
		int x = st1.executeUpdate();
		if(x!=0)
		return true;
		else 
			return false;
	}

	
}
