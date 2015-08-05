package com.di.dataUploader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXAuthorParser extends DefaultHandler {

	boolean bAuthor = false;
	boolean bBook = false;
	boolean bOrder = false;
	boolean bCustomer = false;

	boolean bFirstName = false;
	boolean bLastName = false;
	boolean bCode = false;
	boolean bPseudonym = false;
	boolean bBarcode = false;
	boolean bPesel = false;
	boolean bPhone = false;
	boolean bTitle = false;
	boolean bDescription = false;
	boolean bAddress = false;
	boolean bPrice = false;
	boolean bAuthorCode = false;
	boolean bCustomerCode = false;
	boolean bStatus = false;
	boolean bOrderDate = false;
	boolean bISBN = false;
	boolean bBookcode = false;

	Author author;
	Book book;
	Customer customer;
	Order order;

	ArrayList<Author> authors = new ArrayList<Author>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Order> orders = new ArrayList<Order>();
	static Logger logger = Logger.getLogger("SAXAutgorParser");

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (qName.equalsIgnoreCase("author") || bAuthor) {
			elementAuthor(qName);
			author = new Author();
			bAuthor = true;
		} else if ((qName.equalsIgnoreCase("book") || bBook)) {
			elementBook(qName);
			book = new Book();
			bBook = true;
		} else if ((qName.equalsIgnoreCase("customer") || bCustomer)) {
			elementCustomer(qName);
			customer = new Customer();
			bCustomer = true;
		} else if ((qName.equalsIgnoreCase("order") || bOrder)) {
			elementOrder(qName);
			order = new Order();
			bOrder = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("author")) {
			authors.add(author);
			bAuthor = false;
		} else if (qName.equalsIgnoreCase("book")) {
			books.add(book);
			bBook = false;
		} else if (qName.equalsIgnoreCase("customer")) {
			bCustomer = false;
			customers.add(customer);
		} else if (qName.equalsIgnoreCase("order")) {
			bOrder = false;
			orders.add(order);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (bAuthor) {
			if (bFirstName) {
				//author.setFirstName(new String(ch, start, length));
				String str = new String(ch, start, length);
				author.firstName = str;
				bFirstName = false;
			} else if (bLastName) {
				author.setLastName(new String(ch, start, length));
				bLastName = false;
			} else if (bPseudonym) {
				author.setPseudonym(new String(ch, start, length));
				bPseudonym = false;
			} else if (bCode) {
				author.setCode(new String(ch, start, length));
				bCode = false;
			}
			bAuthor = false;
			return;
		}

		if (bBook) {
			if (bTitle) {
				book.setTitle(new String(ch, start, length));
				bTitle = false;
			} else if (bCode) {
				book.setCode(new String(ch, start, length));
				bCode = false;
			} else if (bDescription) {
				book.setDescription(new String(ch, start, length));
				bDescription = false;
			} else if (bISBN) {
				book.setISBN(new String(ch, start, length));
				bISBN = false;
			} else if (bPrice) {
				book.setPrice(new String(ch, start, length));
				bPrice = false;
			} else if (bAuthorCode) {
				book.setAuthorCode(new String(ch, start, length));
				bAuthorCode = false;
			}
		}

		if (bCustomer) {
			if (bBarcode) {
				customer.setBarcode(new String(ch, start, length));
				bBarcode = false;
			} else if (bCode) {
				customer.setCode(new String(ch, start, length));
				bCode = false;
			} else if (bFirstName) {
				customer.setFirstName(new String(ch, start, length));
				bFirstName = false;
			} else if (bLastName) {
				customer.setLastName(new String(ch, start, length));
				bLastName = false;
			} else if (bPhone) {
				customer.setPhone(new String(ch, start, length));
				bPhone = false;
			} else if (bPesel) {
				customer.setPesel(new String(ch, start, length));
				bPesel = false;
			} else if (bAddress) {
				customer.setAddress(new String(ch, start, length));
				bAddress = false;
			}
		}

		if (bOrder) {
			if (bStatus) {
				order.setStatus(new String(ch, start, length));
				bStatus = false;
			} else if (bCustomerCode) {
				order.setCustomerCode(new String(ch, start, length));
				bCustomerCode = false;
			} else if (bBookcode) {
				order.setBookCode(new String(ch, start, length));
				bBookcode = false;
			} else if (bOrderDate) {
				order.setOrderDate(new String(ch, start, length));
				bOrderDate = false;
			}
			bAuthor = false;
			return;
		}

	}

	public ArrayList<Author> getAuthors() {
		return authors;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	private void elementAuthor(String qName) {
		//logger.info("Nowy autor" + qName);
		if (qName.equalsIgnoreCase("firstName")) {
			//logger.info("1");
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastname")) {
			bLastName = true;
			logger.info("2");
		} else if (qName.equalsIgnoreCase("pseudonym")) {
			bPseudonym = true;
		} else if (qName.equalsIgnoreCase("code")) {
			bCode = true;
		}
	}

	private void elementCustomer(String qName) {
		if (qName.equalsIgnoreCase("barcode")) {
			bBarcode = true;
		} else if (qName.equalsIgnoreCase("firstName")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("lastName")) {
			bLastName = true;
		} else if (qName.equalsIgnoreCase("code")) {
			bCode = true;
		} else if (qName.equalsIgnoreCase("phone")) {
			bPhone = true;
		} else if (qName.equalsIgnoreCase("address")) {
			bAddress = true;
		} else if (qName.equalsIgnoreCase("pesel")) {
			bPesel = true;
		}
	}

	private void elementBook(String qName) {
		if (qName.equalsIgnoreCase("title")) {
			bTitle = true;
		} else if (qName.equalsIgnoreCase("description")) {
			bDescription = true;
		} else if (qName.equalsIgnoreCase("isbn")) {
			bISBN = true;
		} else if (qName.equalsIgnoreCase("code")) {
			bCode = true;
		} else if (qName.equalsIgnoreCase("price")) {
			bPrice = true;
		} else if (qName.equalsIgnoreCase("authorCode")) {
			bAuthorCode = true;
		}
	}
	

	private void elementOrder(String qName) {
		if (qName.equalsIgnoreCase("bookcode")) {
			bBookcode = true;
		} else if (qName.equalsIgnoreCase("customercode")) {
			bCustomerCode = true;
		} else if (qName.equalsIgnoreCase("status")) {
			bStatus = true;
		} else if (qName.equalsIgnoreCase("orderdate")) {
			bOrderDate = true;
		}
	}


}