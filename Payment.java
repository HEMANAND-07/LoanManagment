package com.loanman.loanmanagement;

public class Payment {

	private int loanId;
	private int custId;
	private int amtPaid;
	private String notes;
	private String dateOfPayment;
	private String CustName ;
	
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(String dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public int getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(int amtPaid) {
		this.amtPaid = amtPaid;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	@Override
	public String toString() {
		return "Payment [loanId=" + loanId + ", custId=" + custId + ", amtPaid=" + amtPaid + ", notes=" + notes
				+ ", dateOfPayment=" + dateOfPayment + ", CustName=" + CustName + "]";
	}
	
}
