package com.di.dataUploader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
	Document document;
	static Logger logger = Logger.getLogger("XmlParser");
	public XmlParser(Document document)
	{
		this.document = document;
	}
	
	ArrayList<Author> parseAuthors()
	{
		logger.info("Rozpoczynam parsowanie autorow");
		NodeList nodeList = document.getElementsByTagName("author");
		logger.info("Zbudowano Node autorow");
		if(nodeList.getLength() == 0){
			logger.info("Brak autorow - koncze wykonywanie programu");
			return null;
		}
		ArrayList<Author> authors = new ArrayList<Author>();
		for(int authorIt = 0; authorIt < nodeList.getLength(); authorIt++)
		{
			Node node = nodeList.item(authorIt);
			NodeList childNode = node.getChildNodes();
			Author author = NodeCrusher.crushAuthorNode(childNode);
			authors.add(author);
			logger.info(Integer.toString(authorIt));
		}
		return authors;
	}
	

}
