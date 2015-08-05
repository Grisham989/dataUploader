package com.di.dataUploader;

public class Customer {
	private String code;
	private String barcode;
	private String firstName;
	private String lastName;
	private String pesel;
	private String address;
	private String phone;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String printCustomer()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("code: ").append(code).append(" firstName: ").append(firstName).append(" lastName: ").append(lastName);
		return sb.toString();
	}
}
