package com.di.dataUploader;

import java.io.IOException;
import java.util.logging.FileHandler;

import java.util.logging.Logger;

public final class Utils {
	static Logger logger = Logger.getLogger("Utils");
	static Database startDbConnection()
	{
		Database db = new Database();
		db.openCloseSession();
		db.cleanTables();
		db.makeTransaction();
		logger.info("connection started");
		return db;
	}
	
	static void stopDbConnection(Database db)
	{
		db.commitTransaction();
		db.openCloseSession();
		db.factory.close();
	}
	
	static void setupLogFile(Logger logg, String fileName) {
		try {
			FileHandler logHandler = new FileHandler(fileName);
			logg.addHandler(logHandler);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
