package com.di.dataUploader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParser extends DefaultHandler {

	Author author;
	Order order;
	Customer customer;
	Book book;

	int counters[] = new int[5];
	int lastCommit = 0;
	static Logger logger = Logger.getLogger("SAXParser");
	Database db;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("author")) {
			author = new Author();
			BVars.bAuthor = true;
		} else if (qName.equalsIgnoreCase("book")) {
			book = new Book();
			BVars.bBook = true;
		} else if (qName.equalsIgnoreCase("customer")) {
			customer = new Customer();
			BVars.bCustomer = true;
		} else if (qName.equalsIgnoreCase("order")) {
			order = new Order();
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
			db.addObject((Object) author, "author");
			countUp(1);
			statusMessage(counters[1], "authors");
			BVars.bAuthor = false;
		} else if (qName.equalsIgnoreCase("book")) {
			db.addObject((Object) book, "book");
			countUp(2);
			statusMessage(counters[2], "books");
			BVars.bBook = false;
		} else if (qName.equalsIgnoreCase("customer")) {
			db.addObject((Object) customer, "customer");
			countUp(3);
			statusMessage(counters[3], "customers");
			BVars.bCustomer = false;
		} else if (qName.equalsIgnoreCase("order")) {
			db.addObject((Object) order, "orders");
			countUp(4);
			statusMessage(counters[4], "orders");
			BVars.bOrder = false;
		}
		if (counters[0] % 10000 == 0 && counters[0] > 0) {
			if (counters[0] != lastCommit) {
				lastCommit = counters[0];
				db.commitTransaction();
				db.makeTransaction();
				logger.log(Level.INFO, "Commit nr: {0}", (int) counters[0] / 10000);
			}
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
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

	}

	private void statusMessage(int number, String name) {
		if (number % 10000 == 0) {
			logger.log(Level.INFO, "{0}: {1}", new Object[] { name, number });
		}
	}

	void setDb(Database db) {
		logger.info("Setting DB");
		this.db = db;
	}

	Database getDb() {
		return db;
	}

	void setLogger() {
		Utils.setupLogFile(logger, "log_parser.xml");
	}

	void countUp(int pos) {
		counters[0]++;
		counters[pos]++;
	}
}