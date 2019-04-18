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

import Pojos.Grupo;
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
public class DaoGrupo extends DaoGenerico<Grupo, String> implements InterfaceDaoGenerico<Grupo, String> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoGrupo
     *
     * @param s
     */
    public DaoGrupo(Session s) {
        this.session = s;
    }

    /**
     * Metodo para actualizar una lista de Grupos en la BD
     *
     * @param grupos
     * @throws Exception
     */
    public void actualizarGrupos(List<Grupo> grupos) throws Exception {
        for (int i = 0; i < grupos.size(); i++) {
            Grupo g = grupos.get(i);

            Grupo grupo = buscar(g.getCodigo());

            try {
                this.session.beginTransaction();

                if (grupo == null) {
                    grupo = new Grupo(
                            g.getCodigo(),
                            g.getNombre(),
                            g.getEnsenanza(),
                            g.getLinea(),
                            g.getTurno(),
                            g.getModalidad(),
                            g.getAula(),
                            g.getCapacidad(),
                            g.getTutor_ppal(),
                            g.getTutor_sec(),
                            g.getOficial()
                    );
                } else {
                    grupo.setNombre(g.getNombre());
                    grupo.setEnsenanza(g.getEnsenanza());
                    grupo.setLinea(g.getLinea());
                    grupo.setTurno(g.getTurno());
                    grupo.setModalidad(g.getModalidad());
                    grupo.setAula(g.getAula());
                    grupo.setCapacidad(g.getCapacidad());
                    grupo.setTutor_ppal(g.getTutor_ppal());
                    grupo.setTutor_sec(g.getTutor_sec());
                    grupo.setOficial(g.getOficial());
                }

                this.session.saveOrUpdate(grupo);

                this.session.getTransaction().commit();

            } catch (Exception e) {
                this.session.getTransaction().commit();

                e.printStackTrace();
                System.out.println("Error DaoGrupo-actualizar(): " + e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Metodo para buscar un Grupo mediante su Codigo en la BD
     *
     * @param codigo
     * @return
     * @throws PersistenceException
     */
    public Grupo buscar(String codigo) throws PersistenceException {
        Grupo grupo;

        grupo = (Grupo) this.session.get(Grupo.class, codigo);

        return grupo;
    }

    /**
     * Metodo para buscar una lista de todos los Grupos de la BD
     */
    public List<Grupo> buscarTodos() {
        List<Grupo> lista = new ArrayList<Grupo>();

        Query query = this.session.createQuery("from Grupo where ensenanza = 3 or ensenanza = 5");
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
                System.out.println("Error DaoGrupo-desconectar()");
                throw e;
            }
        }
    }
}
