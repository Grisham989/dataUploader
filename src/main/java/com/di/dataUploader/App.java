package com.di.dataUploader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jboss.logging.Logger;
import org.w3c.dom.Document;

/**
 * Hello world!
 *
 */
public class App {
	static Logger logger = Logger.getLogger("App");
	static com.di.dataUploader.SAXParser saxHandler;
	static ArrayList<Author> authors = new ArrayList<Author>();
	static ArrayList<Book> books = new ArrayList<Book>();
	static ArrayList<Customer> customers = new ArrayList<Customer>();
	static ArrayList<Order> orders = new ArrayList<Order>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Wprowadz nazwe pliku (bez rozszerzenia): ");
		String inputFile = sc.nextLine();
		
		
		//System.out.println("Wybierz typ parsera: ");
		//System.out.println("dom");
		//System.out.println("sax");
		//String parserType = sc.nextLine();
		String parserType= "sax";
		
		if (parserType.equalsIgnoreCase("sax")) {
			long timeStart = System.currentTimeMillis();
			saxParse(inputFile);
			StringBuilder sb = new StringBuilder("Parsing time: ");
			sb.append((float) (System.currentTimeMillis() - timeStart) / 1000).append(" seconds");
			logger.info(sb.toString());
		} else if (parserType.equalsIgnoreCase("dom")) {
			// XmlParser parser = XmlParserBuilder.newXmlParser(inputFile);
		}

		uploadData();

	}

	static void saxParse(String file) {
		com.di.dataUploader.SAXParser saxHandler = new com.di.dataUploader.SAXParser();
		try {
			StringBuilder sb = new StringBuilder("C:/xml/");
			sb.append(file).append(".xml");
			File inputFile = new File(sb.toString());

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxHandler = new com.di.dataUploader.SAXParser();
			saxParser.parse(inputFile, saxHandler);
		} catch (Exception e) {
			e.printStackTrace();
		}
		authors = saxHandler.getAuthors();
		books = saxHandler.getBooks();
		orders = saxHandler.getOrders();
		customers = saxHandler.getCustomers();
	}

	static void uploadData() {
		logger.info("Uploading to db");
		long timeStart = System.currentTimeMillis();
		
		Database db = new Database();
		db.openCloseSession();
		db.addList(authors.toArray(), "author");
		db.addList(books.toArray(), "book");
		db.addList(customers.toArray(), "customer");
		db.addList(orders.toArray(), "orders");
		db.openCloseSession();
		db.factory.close();
		
		StringBuilder sb = new StringBuilder("Uploading time: ");
		sb.append((float) (System.currentTimeMillis() - timeStart) / 1000).append(" seconds");
		logger.info(sb.toString());
	}
}
