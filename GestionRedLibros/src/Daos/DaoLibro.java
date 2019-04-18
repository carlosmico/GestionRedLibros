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

import Pojos.Contenido;
import Utilidades.Estado;
import Pojos.Ejemplar;
import Pojos.Libro;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Query;

import org.hibernate.Session;

/**
 *
 * @author Carlos
 */
public class DaoLibro extends DaoGenerico<Libro, String> implements InterfaceDaoGenerico<Libro, String> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoLibro que recibe una sesion
     *
     * @param s
     */
    public DaoLibro(Session s) {
        this.session = s;
    }

    /**
     * Metodo para crear un Libro en la BD, antes de crear el libro se crearán
     * sus ejemplares
     */
    @Override
    public void grabar(Libro libro) throws PersistenceException {
        try {
            this.session.beginTransaction();

            libro.setEjemplares(generarEjemplares(libro));

            this.session.save(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            this.session.getTransaction().commit();

            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Metodo para actualizar un Libro en la BD, antes de actualizar el libro se
     * actualizarán sus ejemplares
     *
     * @param unidadesOld
     * @param libro
     * @throws PersistenceException
     */
    public void actualizar(int unidadesOld, Libro libro) throws PersistenceException {

        try {
            this.session.beginTransaction();

            libro.setEjemplares(actualizarEjemplares(libro, unidadesOld));

            this.session.saveOrUpdate(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            this.session.getTransaction().commit();
            throw e;
        }
    }

    /**
     * Metodo para eliminar un Libro de la BD
     */
    @Override
    public void borrar(Libro libro) throws PersistenceException {
        try {
            this.session.beginTransaction();

            this.session.delete(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            this.session.getTransaction().commit();
            throw e;
        }
    }

    /**
     * Metodo para obtener un Libro mediante su Codigo
     *
     * @param codigo
     * @return
     * @throws PersistenceException
     */
    public Libro buscar(String codigo) throws PersistenceException {
        Libro libro;

        try {
            libro = (Libro) this.session.get(Libro.class, codigo);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw e;
        }

        return libro;
    }

    public List<Libro> buscarPorContenido(Contenido contenido) {
        List<Libro> lista = new ArrayList<Libro>();

        org.hibernate.query.Query query = this.session.createQuery("from Libro where contenido_libro=" + contenido.getId());
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de todos los Libros de la BD
     */
    public List<Libro> buscarTodos() {
        List<Libro> lista = new ArrayList<Libro>();

        Query query = this.session.createQuery("from Libro");
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para generar y obtener una lista de Ejemplares de un Libro
     * recibido
     */
    private List<Ejemplar> generarEjemplares(Libro libro) {
        List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

        Ejemplar ejemplar;
        String codigo_ejemplar = "";

        for (int i = 1; i < libro.getUnidades() + 1; i++) {
            if (i < 10) {
                codigo_ejemplar = libro.getCodigo() + "0000" + i;
            } else if (i < 100) {
                codigo_ejemplar = libro.getCodigo() + "000" + i;
            } else if (i < 1000) {
                codigo_ejemplar = libro.getCodigo() + "00" + i;
            } else if (i < 10000) {
                codigo_ejemplar = libro.getCodigo() + "0" + i;
            } else if (i < 100000) {
                codigo_ejemplar = libro.getCodigo() + "" + i;
            } else {
                codigo_ejemplar = libro.getCodigo() + "YOUFOUNDTHEIMPOSIBLEEASTEREGG";
            }

            ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

            ejemplares.add(ejemplar);
        }

        return ejemplares;
    }

    /**
     * Metodo para actualizar y obtener una lista de Ejemplares de un Libro
     * recibido
     */
    private List<Ejemplar> actualizarEjemplares(Libro libro, int unidadesOld) {
        int cantidad = libro.getUnidades() - unidadesOld;

        int codBase;

        List<Ejemplar> ejemplares = libro.getEjemplares();

        if (Math.signum(cantidad) > 0) {
            //Añadimos la cantidad de ejemplares que se ha incrementado.

            String codigo = ejemplares.get(ejemplares.size() - 1).getCodigo();

            codBase = Integer.parseInt(codigo.substring(codigo.length() - 5));

            for (int i = 0; i < cantidad; i++) {
                String codigo_ejemplar;

                codBase++;

                if (codBase < 10) {
                    codigo_ejemplar = libro.getCodigo() + "0000" + codBase;
                } else if (codBase < 100) {
                    codigo_ejemplar = libro.getCodigo() + "000" + codBase;
                } else if (codBase < 1000) {
                    codigo_ejemplar = libro.getCodigo() + "00" + codBase;
                } else if (codBase < 10000) {
                    codigo_ejemplar = libro.getCodigo() + "0" + codBase;
                } else if (codBase < 100000) {
                    codigo_ejemplar = libro.getCodigo() + "" + codBase;
                } else {
                    codigo_ejemplar = libro.getCodigo() + "YOUFOUNDTHEIMPOSIBLEEASTEREGG";
                }

                Ejemplar ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

                ejemplares.add(ejemplar);
            }
        } else if (Math.signum(cantidad) < 0) {
            //Eliminamos la cantidad de ejemplares que se ha reducido.
            System.out.println("Se eliminan " + Math.abs(cantidad) + " ejemplares");
        }

        return ejemplares;
    }

    /**
     * Metodo para desconectar la sesion del DAO
     */
    @Override
    public void desconectar() {
        if (this.session != null) {
            try {
                this.session.close();
            } catch (Exception e) {
                System.out.println("Error DaoLibro-desconectar()");
                throw e;
            }
        }
    }
}
