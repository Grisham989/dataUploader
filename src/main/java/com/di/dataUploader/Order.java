package com.di.dataUploader;

public class Order {
	private String customerCode;
	private String bookCode;
	private String status;
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	private String orderDate;
	
	public String printOrder()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("data: ").append(orderDate);
		return sb.toString();
	}
}
