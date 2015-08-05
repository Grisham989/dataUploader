package com.di.dataUploader;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jboss.logging.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class XmlParserBuilder {
	Author author;
	static Logger logger = Logger.getLogger("XmlParserBuilder");
	private static File readFile(String fileName) {
		StringBuilder sb = new StringBuilder("C:\\xml\\");
		sb.append(fileName).append(".xml");
		File file = new File(sb.toString());
		logger.info("Wczytano plik");
		return file;
	}
	
	private static Document fileToDocument(File file)
	{
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbFactory.newDocumentBuilder();
			Document document = db.parse(file);
			logger.info("Przekonwertowano plik na dokument");
			return document;
		}catch(Exception e){
			return null;
		}
	}
	
	static XmlParser newXmlParser(String fileName)
	{
		File file = readFile(fileName);
		Document document = fileToDocument(file);
		XmlParser parser = new XmlParser(document);
		logger.info("Utworzono parser");
		return parser;	
	}
	

}
