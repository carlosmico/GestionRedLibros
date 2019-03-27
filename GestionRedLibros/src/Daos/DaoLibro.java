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
package Daos;

import Comunes.Estado;
import Pojos.Ejemplar;
import Pojos.Libro;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import hibernate.UtilesHibernate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Carlos
 */
public class DaoLibro extends DaoGenerico<Libro, Integer> implements InterfaceDaoGenerico<Libro, Integer> {

    SessionFactory factory;
    Session session;

    private void conectar() {
        factory = UtilesHibernate.getSessionFactory();
        session = factory.getCurrentSession();
        session.beginTransaction();
    }

    private void desconectar() throws Exception {
        try {
            if (session != null) {
                session.close();
                session = null;
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }

    @Override
    public void grabar(Libro libro) throws PersistenceException {
        conectar();

        try {
            session.save(libro);

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-grabar(): " + ex.getMessage());
        }
        
        generarEjemplares(libro);
    }

    @Override
    public void actualizar(Libro l) throws PersistenceException {
        conectar();

        try {
            Libro libro = (Libro) session.get(Libro.class, l.getCodigo());

            libro.setContenido(l.getContenido());
            libro.setISBN(l.getISBN());
            libro.setNombre(l.getNombre());
            libro.setObsoleto(l.getObsoleto());
            libro.setPrecio(l.getPrecio());
            libro.setUnidades(l.getUnidades());

            session.saveOrUpdate(libro);

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }

        try {
            desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-actualizar(): " + ex.getMessage());
        }
    }
    
    @Override
    public void borrar(Libro libro) throws PersistenceException {
        conectar();

        try {
            session.delete(libro);

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-grabar(): " + ex.getMessage());
        }
    }
    
    public Libro buscar(String codigo) throws PersistenceException {
        conectar();
        
        Libro libro;

        try {
            libro = (Libro) session.get(Libro.class, codigo);

            session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-buscarCodigo(): " + ex.getMessage());
        }
        
        return libro;
    }
    
    public List<Libro> buscarTodos(){
        conectar();
        
        List<Libro> lista = new ArrayList<Libro>();
        
        Query query = session.createQuery("from Libro");
        lista = query.list();
        
        try {
            desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-buscarTodos(): " + ex.getMessage());
        }
        
        return lista;
    }

    
    private void generarEjemplares(Libro libro) {
        Ejemplar ejemplar;
        String codigo_ejemplar = "";

        conectar();

        for (int i = 1; i < libro.getUnidades() + 1; i++) {
            if (i < 10) {
                codigo_ejemplar = libro.getCodigo() + "00" + i;
            } else if (i < 100) {
                codigo_ejemplar = libro.getCodigo() + "0" + i;
            } else {
                codigo_ejemplar = libro.getCodigo() + "" + i;
            }

            ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

            session.save(ejemplar);
        }

        try {
            session.getTransaction().commit();

            desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-generarcodigos(): " + ex.getMessage());
        }
    }
}
