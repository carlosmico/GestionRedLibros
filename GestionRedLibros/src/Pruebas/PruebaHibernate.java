package Pruebas;

import Pojos.Alumno;
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

            System.out.println("N º Matriculas: " + objeto.getMatriculas());

            for (int i = 0; i < objeto.getMatriculas().size(); i++) {
                objeto.getMatriculas().get(i).toString();
            }
            
            System.out.println("N º Historial: " + objeto.getHistoriales());

            for (int i = 0; i < objeto.getHistoriales().size(); i++) {
                objeto.getHistoriales().get(i).toString();
            }

            //System.out.println("Alumno de la matricula: " + m.getAlumno().toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No existe ningún objeto con ese id.");
        }
        session.getTransaction().commit();
    }
}
