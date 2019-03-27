package Pruebas;

import Daos.DaoLibro;
import Pojos.Alumno;
import Pojos.Contenido;
import Pojos.Curso;
import Pojos.Historial;
import Pojos.Libro;
import Pojos.Matricula;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernate.UtilesHibernate;
import net.bytebuddy.asm.Advice;

public class PruebaHibernate {

    public static void main(String[] args) {

        Curso curso = new Curso("a", "a", "a", "a", "a", "a");
        Contenido contenido = new Contenido(5, curso, "a", "a", "a", "a");
        Libro l = new Libro("99999999", contenido, "C", "nombre", 40, true, 0);
        
        try {
            
            //SessionFactory factory = UtilesHibernate.getSessionFactory();
            //Session session = factory.getCurrentSession();
            //session.beginTransaction();
             
            //session.save(curso);
            
            //session.save(contenido);
            
            //session.getTransaction().commit();
            
            //session.close();
            
            DaoLibro dao = new DaoLibro();
            
            for (int i = 0; i < dao.buscarTodos().size(); i++) {
                System.out.println(dao.buscarTodos().get(i).toString());
            }
            //dao.generarEjemplares(l);
            

            /*
            
            Alumno objeto = (Alumno) session.get(Alumno.class, "71230212");

            System.out.println("===================");
            System.out.println("ALUMNO");
            System.out.println("===================");
            
            System.out.println( objeto.toString());
            
            System.out.println("Nombre: " + objeto.getNombre() + " " + objeto.getApellido1() + " " + objeto.getApellido2());

            System.out.println("===================");
            System.out.println("MATRICULAS");
            System.out.println("===================");
            
            System.out.println("N º Matriculas: " + objeto.getMatriculas().size());

            for (int i = 0; i < objeto.getMatriculas().size(); i++) {
                objeto.getMatriculas().get(i).toString();
            }
            
            System.out.println("===================");
            System.out.println("HISTORIAL");
            System.out.println("===================");
            
            System.out.println("N º Historial: " + objeto.getHistoriales().size());

            for (int i = 0; i < objeto.getHistoriales().size(); i++) {
                Historial historial = objeto.getHistoriales().get(i);
                
                System.out.println("Historial " + i + historial.toString());
                
                System.out.println("Ejemplar: " + historial.getEjemplar().getCodigo());
                
                System.out.println("Libro del ejemplar: " + historial.getEjemplar().getLibro().getCodigo());
                
                System.out.println("Contenido del libro: " + historial.getEjemplar().getLibro().getContenido().getNombre_cas());
                
                System.out.println("Curso del contenido: " + historial.getEjemplar().getLibro().getContenido().getCurso().getNombre_cas());
            }

             */
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No existe ningún objeto con ese id.");
        }

    }
}
