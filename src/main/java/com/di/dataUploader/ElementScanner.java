package com.di.dataUploader;

public final class ElementScanner {
	static void elementAuthor(String qName) {
		if (qName.equalsIgnoreCase("firstName")) {
			BVars.bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastname")) {
			BVars.bLastName = true;
		} else if (qName.equalsIgnoreCase("pseudonym")) {
			BVars.bPseudonym = true;
		} else if (qName.equalsIgnoreCase("code")) {
			BVars.bCode = true;
		}
	}

	static void elementCustomer(String qName) {
		if (qName.equalsIgnoreCase("barcode")) {
			BVars.bBarcode = true;
		} else if (qName.equalsIgnoreCase("firstName")) {
			BVars.bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastName")) {
			BVars.bLastName = true;
		} else if (qName.equalsIgnoreCase("code")) {
			BVars.bCode = true;
		} else if (qName.equalsIgnoreCase("phone")) {
			BVars.bPhone = true;
		} else if (qName.equalsIgnoreCase("address")) {
			BVars.bAddress = true;
		} else if (qName.equalsIgnoreCase("pesel")) {
			BVars.bPesel = true;
		}
	}

	static void elementBook(String qName) {
		if (qName.equalsIgnoreCase("title")) {
			BVars.bTitle = true;
		} else if (qName.equalsIgnoreCase("description")) {
			BVars.bDescription = true;
		} else if (qName.equalsIgnoreCase("isbn")) {
			BVars.bISBN = true;
		} else if (qName.equalsIgnoreCase("code")) {
			BVars.bCode = true;
		} else if (qName.equalsIgnoreCase("price")) {
			BVars.bPrice = true;
		} else if (qName.equalsIgnoreCase("authorCode")) {
			BVars.bAuthorCode = true;
		}
	}

	static void elementOrder(String qName) {
		if (qName.equalsIgnoreCase("bookcode")) {
			BVars.bBookcode = true;
		} else if (qName.equalsIgnoreCase("customercode")) {
			BVars.bCustomerCode = true;
		} else if (qName.equalsIgnoreCase("status")) {
			BVars.bStatus = true;
		} else if (qName.equalsIgnoreCase("orderdate")) {
			BVars.bOrderDate = true;
		}
	}
}
