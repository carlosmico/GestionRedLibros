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

import Pojos.Alumno;
import Pojos.Ejemplar;
import Pojos.Historial;
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
public class DaoHistorial extends DaoGenerico<Historial, Integer> implements InterfaceDaoGenerico<Historial, Integer> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoHistorial que recibe una sesion
     *
     * @param s
     */
    public DaoHistorial(Session s) {
        this.session = s;
    }

    /**
     * Metodo para crear un nuevo Historial en la BD
     */
    @Override
    public void grabar(Historial h) throws PersistenceException {
        try {
            this.session.beginTransaction();

            this.session.save(h);

            this.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            ex.printStackTrace();
            System.out.println("Error DaoHistorial-grabar(): " + ex.getMessage());
            throw ex;
        }
    }

    /**
     * Metodo para actualizar un Historial en la BD
     */
    @Override
    public void actualizar(Historial h) throws PersistenceException {
        try {
            Historial historial = (Historial) session.get(Historial.class, h.getId());

            if (historial == null) {
                historial = new Historial();

                historial.setEjemplar(h.getEjemplar());
                historial.setAlumno(h.getAlumno());
                historial.setCurso_escolar(h.getCurso_escolar());
                historial.setEstado_inicial(h.getEstado_inicial());
                historial.setEstado_final(h.getEstado_final());
                historial.setFecha_inicial(h.getFecha_inicial());
                historial.setFecha_final(h.getFecha_final());
                historial.setObservaciones(h.getObservaciones());
            }

            this.session.beginTransaction();

            this.session.saveOrUpdate(historial);

            this.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            System.out.println("Error DaoHistorial-actualizar(): " + ex.getMessage());
            throw ex;
        }
    }

    /**
     * Metodo para buscar un Historial mediante su Id en la BD
     *
     * @param id
     * @return
     * @throws PersistenceException
     */
    public Historial buscar(Integer id) throws PersistenceException {
        Historial historial;

        try {
            historial = (Historial) this.session.get(Historial.class, id);
        } catch (PersistenceException e) {
            System.out.println("Error DaoHistorial-buscarId(): " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        return historial;
    }

    /**
     * Metodo para obtener una lista de Historiales filtrando por el Alumno
     * recibido
     *
     * @param alum
     * @return
     */
    public List<Historial> buscarPorAlumno(Alumno alum) {
        List<Historial> lista = new ArrayList<Historial>();

        Query query = this.session.createQuery("from Historial where alumno = " + alum.getNia());
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Historiales pendientes de cerrar
     * filtrando por el Alumno recibido
     *
     * @param alum
     * @return
     */
    public List<Historial> buscarPorAlumnoPendientes(Alumno alum) {
        List<Historial> lista = new ArrayList<Historial>();

        Query query = this.session.createQuery("from Historial where alumno LIKE "
                + "'" + alum.getNia() + "' AND fecha_final IS NULL");
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Historiales filtrando por el Ejemplar
     * recibido
     *
     * @param ejemplar
     * @return
     */
    public List<Historial> buscarPorEjemplar(Ejemplar ejemplar) {
        List<Historial> lista = new ArrayList<Historial>();

        Query query = this.session.createQuery("from Historial where ejemplar = " + ejemplar.getCodigo());
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Historiales filtrando por el Ejemplar
     * pendiente recibido
     *
     * @param ejemplar
     * @return
     */
    public Historial buscarPorEjemplarPendiente(Ejemplar ejemplar) {
        List<Historial> lista = new ArrayList<Historial>();

        Query query = this.session.createQuery("from Historial where ejemplar LIKE '"
                + ejemplar.getCodigo() + "' AND fecha_final IS NULL");
        lista = query.list();

        return lista.get(0);
    }

    /**
     * Metodo para obtener una lista de todos los Historiales de la BD
     */
    public List<Historial> buscarTodos() {
        List<Historial> lista = new ArrayList<Historial>();

        Query query = this.session.createQuery("from Historial");
        lista = query.list();

        return lista;
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
                System.out.println("Error DaoEjemplar-desconectar()");
                throw e;
            }
        }
    }
}
