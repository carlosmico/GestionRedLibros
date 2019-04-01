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
        Libro libroOld, libroNew;

        libroNew = l;

        super.conectar();
        libroOld = (Libro) session.get(Libro.class, l.getCodigo());
        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-actualizar(): " + ex.getMessage());
        }

        comprobarEjemplares(libroNew);

        try {
            super.conectar();

            libroOld.setContenido(libroNew.getContenido());
            libroOld.setISBN(libroNew.getISBN());
            libroOld.setNombre(libroNew.getNombre());
            libroOld.setObsoleto(libroNew.getObsoleto());
            libroOld.setPrecio(libroNew.getPrecio());
            libroOld.setUnidades(libroNew.getUnidades());

            super.session.saveOrUpdate(libroOld);

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
            
            int codBase;

            try {
                List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

                Query query = super.session.createQuery("from Ejemplar where libro = '" + libroActual.getCodigo() + "'");
                ejemplares = query.list();

                String codigo = ejemplares.get(ejemplares.size() - 1).getCodigo();

                codBase = Integer.parseInt(codigo.substring(codigo.length() - 3));

                for (int i = 0; i < cantidad; i++) {
                    String codigo_ejemplar;

                    codBase++;

                    if (codBase < 10) {
                        codigo_ejemplar = libroActual.getCodigo() + "00" + codBase;
                    } else if (codBase < 100) {
                        codigo_ejemplar = libroActual.getCodigo() + "0" + codBase;
                    } else {
                        codigo_ejemplar = libroActual.getCodigo() + "" + codBase;
                    }

                    Ejemplar ejemplar = new Ejemplar(codigo_ejemplar, libroNuevo, Estado.nuevo, false);

                    super.session.save(ejemplar);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DaoLibro - comprobarEjemplares() - Error al convertir el codigo ejemplar.");
            }
        } else if (libroNuevo.getUnidades() < libroActual.getUnidades()) {
            cantidad = libroActual.getUnidades() - libroNuevo.getUnidades();
            
            List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

            Query query = super.session.createQuery("from Ejemplar where libro = '" + libroActual.getCodigo() + "'");
            ejemplares = query.list();
            
            for (int i = ejemplares.size() - 1 ; i > (ejemplares.size() - 1) - cantidad; i--) {
                session.remove(ejemplares.get(i));
            }
        }

        try {
            super.session.getTransaction().commit();

            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoLibro-generarcodigos(): " + ex.getMessage());
        }
    }
}
