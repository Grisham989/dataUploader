package com.di.dataUploader;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.di.generic.Author;
import com.di.generic.Book;
import com.di.generic.Customer;
import com.di.generic.Order;

public class Database {
	SessionFactory factory;
	Session session = null;
	Transaction tx = null;
	static Logger logger = Logger.getLogger("Database");

	public Database() {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addAnnotatedClass(Author.class);
		configuration.addAnnotatedClass(Book.class);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Order.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());
	}

	public void addObject(Object object, String tableName) {
		try {
			if (tableName.equalsIgnoreCase("author")) {
				session.save((Author) object);
			} else if (tableName.equalsIgnoreCase("book")) {
				session.save((Book) object);
			} else if (tableName.equalsIgnoreCase("customer")) {
				session.save((Customer) object);
			} else if (tableName.equalsIgnoreCase("orders")) {
				session.save((Order) object);
			}
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

	public void cleanTables() {
		logger.info("Cleaning tables");
		cleanTable("authors");
		cleanTable("customers");
		cleanTable("orders");
		cleanTable("books");
	}

	public void makeTransaction() {
		tx = session.beginTransaction();
	}

	public void commitTransaction() {
		tx.commit();
	}
}
