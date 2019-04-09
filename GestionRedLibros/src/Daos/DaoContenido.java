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
import Pojos.Matricula;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class DaoContenido extends DaoGenerico<Contenido, Integer> implements InterfaceDaoGenerico<Contenido, Integer> {

    /**
     *  Variable de sesion para cualquier accion con la BD
     */
    public Session session;

    /**
     *  Constructor el DaoContenido que recibe una sesion
     * @param s
     */
    public DaoContenido(Session s) {
        this.session = s;
    }

    /**
     *  Metodo para actualizar una lista de Contenidos en la BD
     * @param contenidos
     * @throws Exception
     */
    public void actualizarContenidos(List<Contenido> contenidos) throws Exception {
        for (int i = 0; i < contenidos.size(); i++) {
            Contenido c = contenidos.get(i);

            Contenido contenido = buscarContenido(c);

            try {
                this.session.beginTransaction();
                
                if (contenido == null) {
                    contenido = new Contenido(c.getCurso(),
                            c.getCodigo(), c.getEnsenanza(), c.getNombre_cas(),
                            c.getNombre_val());
                } else {
                    contenido.setCurso(c.getCurso());
                    contenido.setEnsenanza(c.getEnsenanza());
                    contenido.setNombre_cas(c.getNombre_cas());
                    contenido.setNombre_val(c.getNombre_val());
                    
                }

                this.session.saveOrUpdate(contenido);
                
                this.session.getTransaction().commit();

            } catch (Exception e) {
                this.session.getTransaction().commit();

                e.printStackTrace();
                System.out.println("Error DaoContenido-actualizar(): " + e.getMessage());
                throw new Exception();
            }
        }
    }

    /**
     *  Metodo para buscar un Contenido a través de su ID en la BD
     * @param id
     * @return
     * @throws PersistenceException
     */
    public Contenido buscar(int id) throws PersistenceException {
        Contenido contenido;

        try {
            contenido = (Contenido) this.session.get(Contenido.class, id);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        return contenido;
    }
    
    /**
     *  Metodo para buscar un Contenido a través de su Codigo en la BD
     * @param codigo
     * @return
     */
    public Contenido buscarPorCodigo(String codigo) {
        List<Contenido> lista = new ArrayList<Contenido>();

        String query = "from Contenido c where c.codigo_contenido = '" + codigo + "'";

        lista = this.session.createQuery(query).list();

        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     *  Metodo para buscar un Contenido a través de un objeto Contenido en la BD
     * @param c
     * @return
     */
    public Contenido buscarContenido(Contenido c) {
        List<Contenido> lista = new ArrayList<Contenido>();

        String query = "from Contenido c where c.codigo_contenido = '" + c.getCodigo() + "'"
                + " and c.curso_contenido = " + c.getCurso().getCodigo();

        lista = this.session.createQuery(query).list();

        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     *  Metodo para obtener una lista de todos los Contenidos de la BD
     */
    public List<Contenido> buscarTodos() {
        List<Contenido> lista = new ArrayList<Contenido>();

        Query query = this.session.createQuery("from Contenido where ensenanza = 3 or ensenanza = 5");
        lista = query.list();

        return lista;
    }

    /**
     *  Metodo para desconectar la sesion del DAO
     */
    @Override
    public void desconectar() {
        if (this.session != null) {
            try {
                this.session.close();
            } catch (Exception e) {
                System.out.println("Error DaoContenido-desconectar()");
            }
        }
    }
}
