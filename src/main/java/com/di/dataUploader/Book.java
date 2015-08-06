package com.di.dataUploader;

public class Book {
	private String code;
	private String title;
	private String description;
	private String ISBN;
	private String price;
	private String authorCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String autorCode) {
		this.authorCode = autorCode;
	}

}
