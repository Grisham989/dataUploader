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
public class App 
{
	static Logger logger = Logger.getLogger("App");
    public static void main( String[] args )
    {
    	System.out.println("Wprowadz nazwe pliku (bez rozszerzenia): ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		
		XmlParser parser = XmlParserBuilder.newXmlParser(input);
    	ArrayList<Author> authors = new ArrayList<Author>();
    	ArrayList<Book> books = new ArrayList<Book>();
    	ArrayList<Customer> customers = new ArrayList<Customer>();
    	ArrayList<Order> orders = new ArrayList<Order>();
    	//authors = parser.parseAuthors();
    	com.di.dataUploader.SAXAuthorParser saxHandler = null;
        try {	
            File inputFile = new File("C:/xml/"+input);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxHandler = new com.di.dataUploader.SAXAuthorParser();
            saxParser.parse(inputFile, saxHandler);     
         } catch (Exception e) {
            e.printStackTrace();
         }
        authors = saxHandler.getAuthors();
        books = saxHandler.getBooks();
        orders = saxHandler.getOrders();
        customers = saxHandler.getCustomers(); 
        logger.info(authors.size());
        for(int i =0; i<3; i++)
        {
        	logger.info(authors.get(i).printAuthor());	
        }
    	//Database baza = new Database();
    	//baza.addAuthor(nowy);
    }
}
