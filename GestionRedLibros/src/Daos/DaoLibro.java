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

    public Session session;

    public DaoLibro(Session s) {
        this.session = s;
    }

    @Override
    public void grabar(Libro libro) throws PersistenceException {
        try {
            this.session.beginTransaction();

            libro.setEjemplares(generarEjemplares(libro));

            this.session.save(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }
    }

    public void actualizar(int unidadesOld, Libro libro) throws PersistenceException {
        
        try {
            this.session.beginTransaction();

            libro.setEjemplares(actualizarEjemplares(libro, unidadesOld));

            this.session.saveOrUpdate(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }
    }

    @Override
    public void borrar(Libro libro) throws PersistenceException {
        try {
            this.session.beginTransaction();

            this.session.delete(libro);

            this.session.getTransaction().commit();
        } catch (PersistenceException e) {
            throw new PersistenceException();
        }
    }

    public Libro buscar(String codigo) throws PersistenceException {
        Libro libro;

        try {
            libro = (Libro) this.session.get(Libro.class, codigo);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        return libro;
    }

    public List<Libro> buscarTodos() {
        List<Libro> lista = new ArrayList<Libro>();

        Query query = this.session.createQuery("from Libro");
        lista = query.list();

        return lista;
    }

    private List<Ejemplar> generarEjemplares(Libro libro) {
        List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

        Ejemplar ejemplar;
        String codigo_ejemplar = "";

        for (int i = 1; i < libro.getUnidades() + 1; i++) {
            if (i < 10) {
                codigo_ejemplar = libro.getCodigo() + "00" + i;
            } else if (i < 100) {
                codigo_ejemplar = libro.getCodigo() + "0" + i;
            } else {
                codigo_ejemplar = libro.getCodigo() + "" + i;
            }

            ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

            ejemplares.add(ejemplar);
        }

        return ejemplares;
    }

    /* Comprobamos los ejemplares del libro actual para saber si hemos de borrar 
     *  o añadir nuevos ejemplares
     */
    private List<Ejemplar> actualizarEjemplares(Libro libro, int unidadesOld) {
        int cantidad = libro.getUnidades() - unidadesOld;

        int codBase;

        List<Ejemplar> ejemplares = libro.getEjemplares();

        if (Math.signum(cantidad) > 0) {
            //Añadimos la cantidad de ejemplares que se ha incrementado.

            String codigo = ejemplares.get(ejemplares.size() - 1).getCodigo();

            codBase = Integer.parseInt(codigo.substring(codigo.length() - 3));

            for (int i = 0; i < cantidad; i++) {
                String codigo_ejemplar;

                codBase++;

                if (codBase < 10) {
                    codigo_ejemplar = libro.getCodigo() + "00" + codBase;
                } else if (codBase < 100) {
                    codigo_ejemplar = libro.getCodigo() + "0" + codBase;
                } else {
                    codigo_ejemplar = libro.getCodigo() + "" + codBase;
                }

                Ejemplar ejemplar = new Ejemplar(codigo_ejemplar, libro, Estado.nuevo, false);

                ejemplares.add(ejemplar);
            }
        } else if (Math.signum(cantidad) < 0) {
            //Eliminamos la cantidad de ejemplares que se ha reducido.
            System.out.println("Se eliminan " + Math.abs(cantidad) + " ejemplares");
        }

        return ejemplares;

        /*
        int cantidad;

        if (libroNuevo.getUnidades() > libroActual.getUnidades()) {
            cantidad = libroNuevo.getUnidades() - libroActual.getUnidades();

            int codBase;

            try {
                List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

                Query query = this.session.createQuery("from Ejemplar where libro = '" + libroActual.getCodigo() + "'");
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

                    this.session.save(ejemplar);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DaoLibro - comprobarEjemplares() - Error al convertir el codigo ejemplar.");
            }
        } else if (libroNuevo.getUnidades() < libroActual.getUnidades()) {
            cantidad = libroActual.getUnidades() - libroNuevo.getUnidades();

            List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();

            Query query = this.session.createQuery("from Ejemplar where libro = '" + libroActual.getCodigo() + "'");
            ejemplares = query.list();

            for (int i = ejemplares.size() - 1; i > (ejemplares.size() - 1) - cantidad; i--) {
                this.session.remove(ejemplares.get(i));
            }
        }*/
    }

    @Override
    public void desconectar() {
        if (this.session != null) {
            try {
                this.session.close();
            } catch (Exception e) {
                System.out.println("Error DaoLibro-desconectar()");
            }
        }
    }
}
