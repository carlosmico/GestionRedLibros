package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilesHibernate {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration().configure();
			
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed. " + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}
}
