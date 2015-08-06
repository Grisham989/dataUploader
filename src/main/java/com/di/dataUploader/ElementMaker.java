package com.di.dataUploader;

import java.util.ArrayList;

public final class ElementMaker {
	static void authorMaker(String str, ArrayList<Author> authors) {
		if (BVars.bFirstName) {
			authors.get(authors.size() - 1).setFirstName(str);
			BVars.bFirstName = false;
		} else if (BVars.bLastName) {
			authors.get(authors.size() - 1).setLastName(str);
			BVars.bLastName = false;
		} else if (BVars.bPseudonym) {
			authors.get(authors.size() - 1).setPseudonym(str);
			BVars.bPseudonym = false;
		} else if (BVars.bCode) {
			authors.get(authors.size() - 1).setCode(str);
			BVars.bCode = false;
		}
	}

	static void bookMaker(String str, ArrayList<Book> books) {
		if (BVars.bTitle) {
			books.get(books.size() - 1).setTitle(str);
			BVars.bTitle = false;
		} else if (BVars.bCode) {
			books.get(books.size() - 1).setCode(str);
			BVars.bCode = false;
		} else if (BVars.bDescription) {
			books.get(books.size() - 1).setDescription(str);
			BVars.bDescription = false;
		} else if (BVars.bISBN) {
			books.get(books.size() - 1).setISBN(str);
			BVars.bISBN = false;
		} else if (BVars.bPrice) {
			books.get(books.size() - 1).setPrice(str);
			BVars.bPrice = false;
		} else if (BVars.bAuthorCode) {
			books.get(books.size() - 1).setAuthorCode(str);
			BVars.bAuthorCode = false;
		}
	}

	static void customerMaker(String str, ArrayList<Customer> customers) {
		if (BVars.bBarcode) {
			customers.get(customers.size() - 1).setBarcode(str);
			BVars.bBarcode = false;
		} else if (BVars.bCode) {
			customers.get(customers.size() - 1).setCode(str);
			BVars.bCode = false;
		} else if (BVars.bFirstName) {
			customers.get(customers.size() - 1).setFirstName(str);
			BVars.bFirstName = false;
		} else if (BVars.bLastName) {
			customers.get(customers.size() - 1).setLastName(str);
			BVars.bLastName = false;
		} else if (BVars.bPhone) {
			customers.get(customers.size() - 1).setPhone(str);
			BVars.bPhone = false;
		} else if (BVars.bPesel) {
			customers.get(customers.size() - 1).setPesel(str);
			BVars.bPesel = false;
		} else if (BVars.bAddress) {
			customers.get(customers.size() - 1).setAddress(str);
			BVars.bAddress = false;
		}
	}

	static void orderMaker(String str, ArrayList<Order> orders) {
		if (BVars.bStatus) {
			orders.get(orders.size() - 1).setStatus(str);
			BVars.bStatus = false;
		} else if (BVars.bCustomerCode) {
			orders.get(orders.size() - 1).setCustomerCode(str);
			BVars.bCustomerCode = false;
		} else if (BVars.bBookcode) {
			orders.get(orders.size() - 1).setBookCode(str);
			BVars.bBookcode = false;
		} else if (BVars.bOrderDate) {
			orders.get(orders.size() - 1).setOrderDate(str);
			BVars.bOrderDate = false;
		}
	}

}
