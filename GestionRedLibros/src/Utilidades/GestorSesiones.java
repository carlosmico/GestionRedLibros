/*
 * Copyright (C) 2019 Carlos
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Utilidades;

import com.fasterxml.classmate.AnnotationConfiguration;
import hibernate.UtilesHibernate;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Carlos
 */
public class GestorSesiones {

    private static SessionFactory sessionFactory;

    /**
     * Constructor de la clase GestorSesiones que inicializa una factory de
     * Hibernate
     */
    public GestorSesiones() {
        /*
        try {
            Configuration configuration = new Configuration().configure();

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed " + ex);
            throw new ExceptionInInitializerError(ex);
        }*/
    }

    public void configurarPropiedades(String ip, int puerto, String usuario, String password) {
        Configuration config = new Configuration();
        
        config.configure("hibernate.cfg.xml");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://" + ip + ":" + puerto + "/institut?useSSL=false");
        config.setProperty("hibernate.connection.username", usuario);
        config.setProperty("hibernate.connection.password", password);
        config.setProperty("hibernate.connection.autoReconnect", "true");
        
        sessionFactory = config.buildSessionFactory();
    }

    /**
     * Metodo que devuelve un objeto Session
     *
     * @return Session
     */
    public Session getSession() {
        Session session = sessionFactory.openSession();
        return session;
    }
}
