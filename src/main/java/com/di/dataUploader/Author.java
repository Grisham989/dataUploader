package com.di.dataUploader;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

public class Author implements Serializable{
	public String firstName;
	private String lastName;
	private String pseudonym;
	private String code;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
	
	public String printAuthor()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("code: ").append(code).append(" firstName: ").append(firstName).append(" lastName: ").append(lastName);
		return sb.toString();
	}
}
