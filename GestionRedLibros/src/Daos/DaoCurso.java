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

import Pojos.Curso;
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
public class DaoCurso extends DaoGenerico<Curso, String> implements InterfaceDaoGenerico<Curso, String> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoCurso que recibe una sesion
     *
     * @param s
     */
    public DaoCurso(Session s) {
        this.session = s;
    }

    /**
     * Metodo para actualizar una lista de Cursos en la BD
     *
     * @param cursos
     * @throws Exception
     */
    public void actualizarCurso(Curso c) throws Exception {
        Curso curso = buscar(c.getCodigo());

        try {
            this.session.beginTransaction();

            if (curso == null) {
                curso = new Curso(
                        c.getCodigo(),
                        c.getEnsenanza(),
                        c.getAbreviatura(),
                        c.getNombre_cas(),
                        c.getNombre_val(),
                        c.getIdPadre()
                );
            } else {
                curso.setEnsenanza(c.getEnsenanza());
                curso.setAbreviatura(c.getAbreviatura());
                curso.setNombre_cas(c.getNombre_cas());
                curso.setNombre_val(c.getNombre_val());
                curso.setIdPadre(c.getIdPadre());
            }

            this.session.saveOrUpdate(curso);

            this.session.getTransaction().commit();

        } catch (Exception e) {
            this.session.getTransaction().commit();
            e.printStackTrace();
            System.out.println("Error DaoCurso-actualizar(): " + e.getMessage());
            throw e;
        }

    }

    /**
     * Metodo para actualizar una lista de Cursos en la BD
     *
     * @param cursos
     * @throws Exception
     */
    public void actualizarCursos(List<Curso> cursos) throws Exception {
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);

            Curso curso = buscar(c.getCodigo());
            try {
                this.session.beginTransaction();

                if (curso == null) {
                    curso = new Curso(
                            c.getCodigo(),
                            c.getEnsenanza(),
                            c.getAbreviatura(),
                            c.getNombre_cas(),
                            c.getNombre_val(),
                            c.getIdPadre()
                    );
                } else {
                    curso.setEnsenanza(c.getEnsenanza());
                    curso.setAbreviatura(c.getAbreviatura());
                    curso.setNombre_cas(c.getNombre_cas());
                    curso.setNombre_val(c.getNombre_val());
                    curso.setIdPadre(c.getIdPadre());
                }

                this.session.saveOrUpdate(curso);

                this.session.getTransaction().commit();

            } catch (Exception e) {
                this.session.getTransaction().commit();
                e.printStackTrace();
                System.out.println("Error DaoCurso-actualizar(): " + e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Metodo para buscar un Curso mediante su Codigo en la BD
     *
     * @param codigo
     * @return
     * @throws PersistenceException
     */
    public Curso buscar(String codigo) throws PersistenceException {
        Curso curso;
        
        curso = (Curso) this.session.get(Curso.class, codigo);

        return curso;
    }

    /**
     * Metodo para obtener una lista de todos los Cursos de la BD
     */
    public List<Curso> buscarTodos() {
        List<Curso> lista = new ArrayList<Curso>();

        Query query = this.session.createQuery("from Curso where codigo_curso not"
                + " in(select idPadre FROM Curso) and (ensenanza = 3 or ensenanza = 5) ORDER BY ensenanza, abreviatura");
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
                System.out.println("Error DaoCurso-desconectar()");
                throw e;
            }
        }
    }
}
