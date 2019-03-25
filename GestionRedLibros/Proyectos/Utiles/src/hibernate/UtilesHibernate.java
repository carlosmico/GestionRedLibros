package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UtilesHibernate {
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			Configuration configuration = new Configuration().configure();
			
			sessionFactory = configuration.buildSessionFactory();
		}catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
