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
import Pojos.Curso;
import Pojos.Historial;
import Pojos.Matricula;
import Vistas.Main;
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
public class DaoMatricula extends DaoGenerico<Matricula, Integer> implements InterfaceDaoGenerico<Matricula, Integer> {

    /**
     * Variable de sesion para cualquier acción con la BD
     */
    public Session session;

    /**
     * Constructor del DaoMatricula que recibe una sesion
     *
     * @param s
     */
    public DaoMatricula(Session s) {
        this.session = s;
    }

    /**
     * Metodo para actualizar una lista de Matriculas en la BD
     *
     * @param matriculasCargadas
     * @throws Exception
     */
    public void actualizarMatriculas(List<Matricula> matriculasCargadas) throws Exception {
        for (int i = 0; i < matriculasCargadas.size(); i++) {
            Matricula m = matriculasCargadas.get(i);

            Matricula matricula = buscarMatricula(m);

            try {
                if (matricula == null) {

                    matricula = new Matricula(m.getCurso_escolar(),
                            m.getAlumno(), m.getEnsenanza(), m.getCurso(), m.getContenido(),
                            m.getIdioma(), m.getTipo_basico(), m.getTipo_predom(), m.getAcis(),
                            m.getFec_ini_acis(), m.getFec_fin_acis(), m.getCur_ref_acis(),
                            m.getCurso_pendiente());
                } else {
                    matricula.setCurso_escolar(m.getCurso_escolar());
                    matricula.setAlumno(m.getAlumno());
                    matricula.setEnsenanza(m.getEnsenanza());
                    matricula.setCurso(m.getCurso());
                    matricula.setContenido(m.getContenido());
                    matricula.setIdioma(m.getIdioma());
                    matricula.setTipo_basico(m.getTipo_basico());
                    matricula.setTipo_predom(m.getTipo_predom());
                    matricula.setAcis(m.getAcis());
                    matricula.setFec_ini_acis(m.getFec_ini_acis());
                    matricula.setFec_fin_acis(m.getFec_fin_acis());
                    matricula.setCur_ref_acis(m.getCur_ref_acis());
                    matricula.setCurso_pendiente(m.getCurso_pendiente());
                }

                this.session.saveOrUpdate(matricula);

            } catch (PersistenceException ex) {
                ex.printStackTrace();
                System.out.println("Error DaoMatricula-actualizar(): " + ex.getMessage());
                throw ex;
            }
        }
    }

    /**
     * Metodo para obtener una matricula mediante un objeto Matricula recibido
     *
     * @param m
     * @return
     */
    public Matricula buscarMatricula(Matricula matricula) {
        List<Matricula> lista = new ArrayList<Matricula>();

        String query = "from Matricula m where m.alumno LIKE '" + matricula.getAlumno().getNia() + "' and m.curso_escolar = " + matricula.getCurso_escolar()
                + " and m.contenido = " + matricula.getContenido().getId() + " and m.curso = " + matricula.getCurso();

        lista = this.session.createQuery(query).list();

        if (lista == null || lista.size() == 0) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    /**
     * Metodo para obtener una matricula mediante un objeto Matricula recibido
     *
     * @param m
     * @return
     */
    public Matricula buscarPorId(int id) {
        Matricula matricula = null;

        try {
            matricula = (Matricula) this.session.get(Matricula.class, id);

        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        return matricula;
    }

    /**
     * Metodo para obtener una lista de Matriculas filtrando por el Alumno
     * recibido
     *
     * @param alumno
     * @return
     */
    public List<Matricula> buscarPorAlumno(Alumno alumno) {
        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = this.session.createQuery("from Matricula where alumno=" + alumno.getNia());
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Matriculas filtrando por el Curso
     * Escolar recibido
     *
     * @param curso_escolar
     * @return
     */
    public List<Matricula> buscarPorCursoEscolar(Integer curso_escolar) {
        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = this.session.createQuery("from Matricula where curso_escolar=" + curso_escolar);
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Matriculas filtrando por el Curso
     * Escolar recibido
     *
     * @param curso_escolar
     * @return
     */
    public List<Matricula> buscarPorCursoEscolar(Integer curso_escolar, Curso curso) {
        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = this.session.createQuery("from Matricula where curso_escolar=" + curso_escolar + " and curso like '" + curso.getCodigo() + "'");
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para obtener una lista de Matriculas filtrando por el alumno,
     * cursoescolar, curso, contenido. (Pendientes de asignar libros).
     *
     * @param curso_escolar
     * @return
     */
    public List<Matricula> buscarPendientes(Alumno alumno, int curso_escolar) {
        List<Matricula> lista = new ArrayList<Matricula>();

        String hql = "FROM Matricula as m"
                + " WHERE m.alumno LIKE '" + alumno.getNia() + "'"
                + " AND m.curso_escolar = " + curso_escolar
                + " AND m.contenido NOT IN(SELECT h.ejemplar.libro.contenido_libro "
                + "FROM Historial h WHERE h.alumno LIKE '" + alumno.getNia()
                + "'AND h.curso_escolar = " + curso_escolar
                + " AND h.ejemplar.prestado = true "
                + " AND h.fecha_final IS NULL)";

        try {
            Query query = this.session.createQuery(hql);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Metodo para obtener una lista de todas las Matriculas de la BD filtradas
     * por Curso_Escolar y Curso
     */
    public List<Matricula> buscarPendientesPorCurso(int curso_escolar, Curso curso) {
        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = this.session.createQuery("from Matricula"
                + " where curso_escolar =" + curso_escolar + " and "
                + "curso LIKE '" + curso.getCodigo() + "'");
        lista = query.list();

        /*
        DaoHistorial daoHistorial = new DaoHistorial(Main.gestorSesiones.getSession());

        // ELIMINAR LAS YA MATRICULAS ENTREGADAS
        
        
        for (int i = 0; i < lista.size(); i++) {
            Matricula m = lista.get(i);

            List<Historial> historiales = daoHistorial.buscarPorAlumno(m.getAlumno());

            if (historiales.size() > 0) {
                for (int j = 0; j < historiales.size(); j++) {
                    Historial h = historiales.get(j);

                    String codContenidoHistorial = h.getEjemplar().getLibro().getContenido().getCodigo();

                    if (h.getCurso_escolar() == curso_escolar && codContenidoHistorial.equals(m.getContenido().getCodigo())) {
                        lista.remove(m);
                    }
                }
            }
        }

        try {
            daoHistorial.desconectar();
        } catch (Exception e) {
            System.out.println("Error al desconectar el daoHistorial en daoMatricula");
            e.printStackTrace();
        }*/

        return lista;
    }

    /**
     * Metodo para obtener una lista de todas las Matriculas de la BD
     */
    public List<Matricula> buscarTodos() {
        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = this.session.createQuery("from Matricula");
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
                System.out.println("Error DaoMatriucla-desconectar()");
                throw e;
            }
        }
    }
}
