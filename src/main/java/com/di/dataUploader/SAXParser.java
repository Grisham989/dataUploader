package com.di.dataUploader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jboss.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParser extends DefaultHandler {

	ArrayList<Author> authors = new ArrayList<Author>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	ArrayList<Book> books = new ArrayList<Book>();
	ArrayList<Order> orders = new ArrayList<Order>();
	Author author;
	Order order;
	Customer customer;
	Book book;
	int counter = 0;
	static Logger logger = Logger.getLogger("SAXAutgorParser");
	static Database db = new Database();
	static boolean methodDB = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("author")) {
			if (methodDB) {
				author = new Author();
			} else {
				authors.add(new Author());
				statusMessage(authors.size(), "authors");
			}
			BVars.bAuthor = true;
		} else if (qName.equalsIgnoreCase("book")) {
			if (methodDB) {
				book = new Book();
			} else {
				books.add(new Book());
				statusMessage(books.size(), "books");
			}
			BVars.bBook = true;
		} else if (qName.equalsIgnoreCase("customer")) {
			if (methodDB) {
				customer = new Customer();
			} else {
				customers.add(new Customer());
				statusMessage(customers.size(), "customers");
			}
			BVars.bCustomer = true;
		} else if (qName.equalsIgnoreCase("order")) {
			if (methodDB) {
				order = new Order();
			} else {
				orders.add(new Order());
				statusMessage(orders.size(), "orders");
			}

			BVars.bOrder = true;
		}

		if (BVars.bAuthor) {
			ElementScanner.elementAuthor(qName);
		} else if (BVars.bBook) {
			ElementScanner.elementBook(qName);
		} else if (BVars.bCustomer) {
			ElementScanner.elementCustomer(qName);
		} else if (BVars.bOrder) {
			ElementScanner.elementOrder(qName);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		counter++;
		if (qName.equalsIgnoreCase("author")) {
			if (methodDB) {
				db.addObject((Object) author, "author");
			}
			BVars.bAuthor = false;
		} else if (qName.equalsIgnoreCase("book")) {
			if (methodDB) {
				db.addObject((Object) book, "book");
			}
			BVars.bBook = false;
		} else if (qName.equalsIgnoreCase("customer")) {
			if (methodDB) {
				db.addObject((Object) customer, "customer");
			}
			BVars.bCustomer = false;
		} else if (qName.equalsIgnoreCase("order")) {
			if (methodDB) {
				db.addObject((Object) order, "orders");
			}
			BVars.bOrder = false;
		}
		if (methodDB) {
			if (counter % 1000 == 0) {
				if (counter > 0) {
					//db.commitTransaction();
				}
			}
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (methodDB) {
			if (BVars.bAuthor) {
				ElementMakerSingle.authorMaker(new String(ch, start, length), author);
				return;
			}
			if (BVars.bBook) {
				ElementMakerSingle.bookMaker(new String(ch, start, length), book);
				return;
			}
			if (BVars.bCustomer) {
				ElementMakerSingle.customerMaker(new String(ch, start, length), customer);
				return;
			}
			if (BVars.bOrder) {
				ElementMakerSingle.orderMaker(new String(ch, start, length), order);
				return;
			}
		} else {
			if (BVars.bAuthor) {
				ElementMaker.authorMaker(new String(ch, start, length), authors);
				return;
			}
			if (BVars.bBook) {
				ElementMaker.bookMaker(new String(ch, start, length), books);
				return;
			}
			if (BVars.bCustomer) {
				ElementMaker.customerMaker(new String(ch, start, length), customers);
				return;
			}
			if (BVars.bOrder) {
				ElementMaker.orderMaker(new String(ch, start, length), orders);
				return;
			}
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

	private void statusMessage(int number, String name) {
		if (number % 10000 == 0) {
			StringBuilder sb = new StringBuilder(name);
			sb.append(": ").append(number);
			logger.info(sb.toString());
		}
	}

}