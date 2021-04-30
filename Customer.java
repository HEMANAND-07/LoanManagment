package com.loanman.loanmanagement;

import java.util.List;

public class Customer {

	private int customerId ;
	private String customerName;
	private String customerMobile;
	private String customerPan;
	private int deptAmt;
	private String customerAddress;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String  i) {
		this.customerMobile = i;
	}
	public String getCustomerPan() {
		return customerPan;
	}
	public void setCustomerPan(String customerPan) {
		this.customerPan = customerPan;
	}
	public int getDeptAmt() {
		return deptAmt;
	}
	public void setDeptAmt(int deptAmt) {
		this.deptAmt = deptAmt;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerMobile="
				+ customerMobile + ", customerPan=" + customerPan + ", deptAmt=" + deptAmt + ", customerAddress="
				+ customerAddress + "]";
	}
	
	
}
