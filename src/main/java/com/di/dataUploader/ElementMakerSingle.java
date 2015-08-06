package com.di.dataUploader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class ElementMakerSingle {
	static void authorMaker(String str, Author author) {
		if (BVars.bFirstName) {
			author.setFirstName(str);
			BVars.bFirstName = false;
		} else if (BVars.bLastName) {
			author.setLastName(str);
			BVars.bLastName = false;
		} else if (BVars.bPseudonym) {
			author.setPseudonym(str);
			BVars.bPseudonym = false;
		} else if (BVars.bCode) {
			author.setCode(str);
			BVars.bCode = false;
		}
	}

	static void bookMaker(String str, Book book ) {
		if (BVars.bTitle) {
			book.setTitle(str);
			BVars.bTitle = false;
		} else if (BVars.bCode) {
			book.setCode(str);
			BVars.bCode = false;
		} else if (BVars.bDescription) {
			book.setDescription(str);
			BVars.bDescription = false;
		} else if (BVars.bISBN) {
			book.setISBN(str);
			BVars.bISBN = false;
		} else if (BVars.bPrice) {
			book.setPrice(str);
			BVars.bPrice = false;
		} else if (BVars.bAuthorCode) {
			book.setAuthorCode(str);
			BVars.bAuthorCode = false;
		}
	}

	static void customerMaker(String str, Customer customer) {
		if (BVars.bBarcode) {
			customer.setBarcode(str);
			BVars.bBarcode = false;
		} else if (BVars.bCode) {
			customer.setCode(str);
			BVars.bCode = false;
		} else if (BVars.bFirstName) {
			customer.setFirstName(str);
			BVars.bFirstName = false;
		} else if (BVars.bLastName) {
			customer.setLastName(str);
			BVars.bLastName = false;
		} else if (BVars.bPhone) {
			customer.setPhone(str);
			BVars.bPhone = false;
		} else if (BVars.bPesel) {
			customer.setPesel(str);
			BVars.bPesel = false;
		} else if (BVars.bAddress) {
			customer.setAddress(str);
			BVars.bAddress = false;
		}
	}

	static void orderMaker(String str, Order order) {
		if (BVars.bStatus) {
			order.setStatus(str);
			BVars.bStatus = false;
		} else if (BVars.bCustomerCode) {
			order.setCustomerCode(str);
			BVars.bCustomerCode = false;
		} else if (BVars.bBookcode) {
			order.setBookCode(str);
			BVars.bBookcode = false;
		} else if (BVars.bOrderDate) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8,10)), Integer.parseInt(str.substring(11,13)), Integer.parseInt(str.substring(14,16)), Integer.parseInt(str.substring(17,19)));
			Date date = new Date(gc.getTimeInMillis());
			order.setOrderDate(date);
			BVars.bOrderDate = false;
		}
	}

}
