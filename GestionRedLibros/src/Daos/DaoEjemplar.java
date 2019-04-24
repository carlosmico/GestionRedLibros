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

import Pojos.Ejemplar;
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
public class DaoEjemplar extends DaoGenerico<Ejemplar, Integer> implements InterfaceDaoGenerico<Ejemplar, Integer> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoEjemplar que recibe una sesion
     *
     * @param s
     */
    public DaoEjemplar(Session s) {
        this.session = s;
    }

    /**
     * Metodo para actualizar un Ejemplar en la BD
     *
     * @param e
     */
    @Override
    public void actualizar(Ejemplar e) throws PersistenceException {

        try {
            Ejemplar ejemplar = (Ejemplar) session.get(Ejemplar.class, e.getCodigo());

            ejemplar.setEstado(e.getEstado());
            ejemplar.setPrestado(e.isPrestado());

            this.session.beginTransaction();

            this.session.saveOrUpdate(ejemplar);

            this.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            throw ex;
        }
    }

    /**
     * Metodo para obtener un Ejemplar mediante su Codigo en la BD
     *
     * @param codigo
     * @return
     * @throws PersistenceException
     */
    public Ejemplar buscar(String codigo) throws PersistenceException {
        Ejemplar ejemplar;

        ejemplar = (Ejemplar) this.session.get(Ejemplar.class, codigo);

        return ejemplar;
    }

    /**
     * Metodo para obtener una lista de todos los Ejemplares de la BD
     */
    public List<Ejemplar> buscarTodos() {
        List<Ejemplar> lista = new ArrayList<Ejemplar>();

        Query query = this.session.createQuery("from Ejemplar");
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
