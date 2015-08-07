package com.di.dataUploader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.logging.Logger;


public class App {
	static Logger logger = Logger.getLogger("App");
	static com.di.dataUploader.SAXParser saxHandler;

	public static void main(String[] args) {
		Utils.setupLogFile(logger, "log_app.xml");

		Scanner sc = new Scanner(System.in);
		System.out.println("Wprowadz nazwe pliku (bez rozszerzenia): ");
		String inputFile = sc.nextLine();
		sc.close();

		long timeStart = System.currentTimeMillis();
		saxParse(inputFile);
		logger.log(Level.INFO, "Total time: {0} seconds.", (float) ((System.currentTimeMillis() - timeStart) / 1000.0));

	}

	static void saxParse(String file) {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(file).append(".xml");
			File inputFile = new File(sb.toString());

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxHandler = new com.di.dataUploader.SAXParser();
			saxHandler.setLogger();
			logger.info("starting db conn");
			saxHandler.setDb(Utils.startDbConnection());
			logger.info("starting parsing");
			saxParser.parse(inputFile, saxHandler);
			logger.info("parsing done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Utils.stopDbConnection(saxHandler.getDb());
		}
	}
	
}
