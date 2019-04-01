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

import Utilidades.Estado;
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
public class DaoLibro extends DaoGenerico<Libro, String> implements InterfaceDaoGenerico<Libro, String> {

    @Override
    public void grabar(Libro libro) throws PersistenceException {
        super.conectar();

        try {
            super.session.save(libro);

            super.session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-grabar(): " + ex.getMessage());
        }

        generarEjemplares(libro);
    }

    @Override
    public void actualizar(Libro l) throws PersistenceException {
        super.conectar();

        try {
            Libro libro = (Libro) session.get(Libro.class, l.getCodigo());

            if (libro != null) {
                libro.setContenido(l.getContenido());
                libro.setISBN(l.getISBN());
                libro.setNombre(l.getNombre());
                libro.setObsoleto(l.getObsoleto());
                libro.setPrecio(l.getPrecio());
                libro.setUnidades(l.getUnidades());

                //comprobarEjemplares(libro);

                super.session.saveOrUpdate(libro);
            } else {
                super.session.saveOrUpdate(l);
            }

            super.session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-actualizar(): " + ex.getMessage());
        }
    }

    @Override
    public void borrar(Libro libro) throws PersistenceException {
        super.conectar();

        try {
            super.session.delete(libro);

            super.session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-grabar(): " + ex.getMessage());
        }
    }

    public Libro buscar(String codigo) throws PersistenceException {
        super.conectar();

        Libro libro;

        try {
            libro = (Libro) super.session.get(Libro.class, codigo);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-buscarCodigo(): " + ex.getMessage());
        }

        return libro;
    }

    public List<Libro> buscarTodos() {
        super.conectar();

        List<Libro> lista = new ArrayList<Libro>();

        Query query = super.session.createQuery("from Libro");
        lista = query.list();

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-buscarTodos(): " + ex.getMessage());
        }

        return lista;
    }

    private void generarEjemplares(Libro libro) {
        Ejemplar ejemplar;
        String codigo_ejemplar = "";

        super.conectar();

        for (int i = 1; i < libro.getUnidades() + 1; i++) {
            if (i < 10) {
                codigo_ejemplar = libro.getCodigo() + "00" + i;
            } else if (i < 100) {
                codigo_ejemplar = libro.getCodigo() + "0" + i;
            } else {
                codigo_ejemplar = libro.getCodigo() + "" + i;
            }

            ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

            super.session.save(ejemplar);
        }

        try {
            super.session.getTransaction().commit();

            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-generarcodigos(): " + ex.getMessage());
        }
    }

    /* Comprobamos los ejemplares del libro actual para saber si hemos de borrar 
    *  o añadir nuevos ejemplares
     */
    private void comprobarEjemplares(Libro libroNuevo) {
        super.conectar();

        Libro libroActual = (Libro) super.session.get(Libro.class, libroNuevo.getCodigo());

        int cantidad;

        if (libroNuevo.getUnidades() > libroActual.getUnidades()) {
            cantidad = libroNuevo.getUnidades() - libroActual.getUnidades();

            int codEjemplar;

            try {
                List<Libro> ejemplares = new ArrayList<Libro>();

                Query query = super.session.createQuery("from Ejemplares where id_libro = '" + libroActual + "'");
                ejemplares = query.list();
                
                System.out.println(ejemplares.get(ejemplares.size() - 1).toString());
            } catch (Exception e) {
                System.out.println("DaoLibro - comprobarEjemplares() - Error al convertir el codigo ejemplar.");
            }

            for (int i = 0; i < cantidad; i++) {

            }
        } else if (libroNuevo.getUnidades() < libroActual.getUnidades()) {

        }

        try {
            super.session.getTransaction().commit();

            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-generarcodigos(): " + ex.getMessage());
        }
    }
}
