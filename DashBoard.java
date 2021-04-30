package com.loanman.loanmanagement;

public class DashBoard {

	private long lendAmt;
	private long intrestEarnings;
	private int  totalCust;
	private int activeLoans;
	public long getLendAmt() {
		return lendAmt;
	}
	public void setLendAmt(long lendAmt) {
		this.lendAmt = lendAmt;
	}
	public long getIntrestEarnings() {
		return intrestEarnings;
	}
	public void setIntrestEarnings(long intrestEarnings) {
		this.intrestEarnings = intrestEarnings;
	}
	public int getTotalCust() {
		return totalCust;
	}
	public void setTotalCust(int totalCust) {
		this.totalCust = totalCust;
	}
	public int getActiveLoans() {
		return activeLoans;
	}
	public void setActiveLoans(int activeLoans) {
		this.activeLoans = activeLoans;
	}
}
