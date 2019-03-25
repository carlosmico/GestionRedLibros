package Pruebas;

import Pojos.Alumno;
import Pojos.Historial;
import Pojos.Matricula;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;

public class PruebaHibernate {

    public static void main(String[] args) {
        SessionFactory factory = UtilesHibernate.getSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        try {
            Alumno objeto = (Alumno) session.get(Alumno.class, "1");

            System.out.println("Objeto recuperado: " + objeto.toString());

            System.out.println("N º Matriculas: " + objeto.getMatriculas().size());

            for (int i = 0; i < objeto.getMatriculas().size(); i++) {
                objeto.getMatriculas().get(i).toString();
            }
            
            System.out.println("N º Historial: " + objeto.getHistoriales().size());

            for (int i = 0; i < objeto.getHistoriales().size(); i++) {
                Historial historial = objeto.getHistoriales().get(i);
                
                System.out.println("Historial " + i + historial.toString());
                
                System.out.println("Ejemplar: " + historial.getEjemplar().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No existe ningún objeto con ese id.");
        }
        session.getTransaction().commit();
    }
}
