package com.loanman.loanmanagement;


public class Loan {

	private int loanId ;
	private int loanAmount;
	private int loanTenure ;
	private float loanIntrest ;
	private String dateOfDisbursement;
	private String loanType;
	private Customer cust;
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(int loanTenure) {
		this.loanTenure = loanTenure;
	}
	public float getLoanIntrest() {
		return loanIntrest;
	}
	public void setLoanIntrest(float loanIntrest) {
		this.loanIntrest = loanIntrest;
	}
	public String getDateOfDisbursement() {
		return dateOfDisbursement;
	}
	public void setDateOfDisbursement(String dateOfDisbursement) {
		this.dateOfDisbursement = dateOfDisbursement;
	}
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanAmount=" + loanAmount + ", loanTenure=" + loanTenure + ", loanIntrest="
				+ loanIntrest + ", dateOfDisbursement=" + dateOfDisbursement + ", cust=" + cust + "]";
	}
	
	
}
