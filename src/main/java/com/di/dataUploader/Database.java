package com.di.dataUploader;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Database {
	SessionFactory factory;
	Session session = null;
	static Logger logger = Logger.getLogger("Database");

	public Database() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Author.class);
		configuration.addClass(Book.class);
		configuration.addClass(Customer.class);
		configuration.addClass(Order.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());
	}

	public void addList(Object list[], String tableName) {
		cleanTable(tableName);
		logger.info(tableName);
		Transaction tx = null;
		int controllNumber = 0;
		tx = session.beginTransaction();
		try {
			for (Object object : list) {
				controllNumber++;
				if (tableName.equalsIgnoreCase("author")) {
					session.save((Author) object);
				} else if (tableName.equalsIgnoreCase("book")) {
					session.save((Book) object);
				} else if (tableName.equalsIgnoreCase("customer")) {
					session.save((Customer) object);
				} else if (tableName.equalsIgnoreCase("orders")) {
					session.save((Order) object);
				}
				if (controllNumber % 10000 == 0) {
					StringBuilder sb = new StringBuilder(tableName);
					sb.append(" krok: ").append(controllNumber).append(" z ").append(list.length);
					logger.info(sb.toString());
					tx.commit();
					tx = session.beginTransaction();
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}

	private void cleanTable(String nazwa) {
		StringBuilder sb = new StringBuilder("truncate table ");
		sb.append(nazwa).append(" cascade");
		session.createSQLQuery(sb.toString()).executeUpdate();
	}

	void openCloseSession() {
		if (session == null) {
			session = factory.openSession();
		} else {
			session.close();
			session = null;
		}
	}

}
