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

	static Logger logger = Logger.getLogger("SAXAutgorParser");

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("author")) {
			authors.add(new Author());
			if (authors.size() % 10000 == 0) {
				StringBuilder sb = new StringBuilder("authors: ");
				sb.append(authors.size());
				logger.info(sb.toString());
			}
			BVars.bAuthor = true;
		} else if (qName.equalsIgnoreCase("book")) {
			books.add(new Book());
			if (books.size() % 10000 == 0) {
				StringBuilder sb = new StringBuilder("books: ");
				sb.append(books.size());
				logger.info(sb.toString());
			}
			BVars.bBook = true;
		} else if (qName.equalsIgnoreCase("customer")) {
			customers.add(new Customer());
			if (customers.size() % 10000 == 0) {
				StringBuilder sb = new StringBuilder("customers: ");
				sb.append(customers.size() / 10000);
				logger.info(sb.toString());
			}
			BVars.bCustomer = true;
		} else if (qName.equalsIgnoreCase("order")) {
			orders.add(new Order());
			if (orders.size() % 10000 == 0) {
				StringBuilder sb = new StringBuilder("orders: ");
				sb.append(orders.size() / 10000);
				logger.info(sb.toString());
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
		if (qName.equalsIgnoreCase("author")) {
			BVars.bAuthor = false;
		} else if (qName.equalsIgnoreCase("book")) {
			BVars.bBook = false;
		} else if (qName.equalsIgnoreCase("customer")) {
			BVars.bCustomer = false;
		} else if (qName.equalsIgnoreCase("order")) {
			BVars.bOrder = false;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {

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

}