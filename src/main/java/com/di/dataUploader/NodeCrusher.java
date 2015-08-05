package com.di.dataUploader;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

public final class NodeCrusher {
	static Author crushAuthorNode(NodeList nodeList) {
		Author author = new Author();
		Element tempNode = (Element)nodeList;
		author.setCode(tempNode.getElementsByTagName("code").item(0).getTextContent());
		author.setFirstName(tempNode.getElementsByTagName("firstName").item(0).getTextContent());
		author.setLastName(tempNode.getElementsByTagName("lastName").item(0).getTextContent());
		author.setPseudonym(tempNode.getElementsByTagName("pseudonym").item(0).getTextContent());
		return author;
	}
	
	static Book crushBookNode(NodeList nodeList)
	{
		Book book = new Book();
		Element tempNode = (Element)nodeList;
		book.setCode(tempNode.getElementsByTagName("code").item(0).getTextContent());
		book.setTitle(tempNode.getElementsByTagName("title").item(0).getTextContent());
		book.setAuthorCode(tempNode.getElementsByTagName("authorCode").item(0).getTextContent());
		book.setPrice(tempNode.getElementsByTagName("price").item(0).getTextContent());
		book.setISBN(tempNode.getElementsByTagName("ISBN").item(0).getTextContent());
		book.setDescription(tempNode.getElementsByTagName("description").item(0).getTextContent());
		return book;
	}
}
