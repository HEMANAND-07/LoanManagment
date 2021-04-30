package com.loanman.loanmanagement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;

@Path("admin")
public class MyResource {

	LoanDatabase db = new LoanDatabase();
	private String username ;
	private String pass;
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DashBoard viewDashboard() {
    System.out.println(username);
    	DashBoard d = new DashBoard();
    	d = db.fetchDashBoard();
    	return d;
    }
    
    @GET
    @Path("loans-history")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Loan> viewAllLoans() {
    	List<Loan> s =  db.viewAllCurrentLoan();
    	return s;
    }
    
    @GET
    @Path("view-loan/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Loan viewLoan(@PathParam("id") int id) {
    	return db.viewLoan(id);
    }
    
    @GET
    @Path("customers-history")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Customer> viewAllCust() {
    	List<Customer> s =  db.viewAllCustomers();
    	return s;
    }
  
    @GET
    @Path("payments-history")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Payment> viewAllPayment() {
    	List<Payment> s =  db.viewAllPayment();
    	return s;
    }
    
    @POST
    @Path("Approve-Loan")
    @Produces(MediaType.APPLICATION_JSON)
    public String ProvideLoan(Loan personalLoan ) throws ParseException {
    	System.out.println(personalLoan);
    	
    	db.provideNewLoan(personalLoan);
    	return "Sucess approved";
    }
    
    @POST
    @Path("record-payment")
    @Produces(MediaType.APPLICATION_JSON)
    public int makePayment(Payment payrec ) {
    	db.recordPay(payrec);
    	return 1;
    }
    
    
    @PUT
    @Path("Update-CustAddress")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer updateAdd( Customer c) {
    	db.addressUpdate(c);
    	return c;
    }
    
    @DELETE
    @Path("del-cust/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String delCust(@PathParam("id") int id) throws SQLException {
    	Boolean flag = db.delcust(id);
    	return "customer deleted ";
    }
}
