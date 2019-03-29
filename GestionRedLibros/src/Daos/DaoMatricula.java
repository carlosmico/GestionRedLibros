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
import Pojos.Matricula;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class DaoMatricula extends DaoGenerico<Matricula, Integer> implements InterfaceDaoGenerico<Matricula, Integer> {

    @Override
    public void actualizar(Matricula m) throws PersistenceException {
        super.conectar();

        try {
            Matricula matricula = (Matricula) session.get(Matricula.class, m.getId());

            if (matricula == null) {
                matricula = new Matricula(m.getId(), m.getCurso_escolar(),
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

            super.session.saveOrUpdate(matricula);

            super.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            System.out.println("Error DaoMatricula-actualizar(): " + ex.getMessage());
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoMatricula-actualizar(): " + ex.getMessage());
        }
    }

    public void actualizarMatriculas(List<Matricula> matriculasCargadas) throws Exception {
        for (int i = 0; i < matriculasCargadas.size(); i++) {
            Matricula m = matriculasCargadas.get(i);
            
            super.conectar();

            try {
                Matricula matricula = (Matricula) session.get(Matricula.class, m.getId());

                if (matricula == null) {
                    matricula = new Matricula(m.getId(), m.getCurso_escolar(),
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

                super.session.saveOrUpdate(matricula);

                super.session.getTransaction().commit();
            } catch (PersistenceException ex) {
                System.out.println("Error DaoMatricula-actualizar(): " + ex.getMessage());
                throw new Exception();
            }

            try {
                super.desconectar();
            } catch (Exception ex) {
                System.out.println("Error DaoMatricula-actualizar(): " + ex.getMessage());
            }
        }
    }

    public List<Matricula> buscarPorAlumno(Alumno alumno) {
        super.conectar();

        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = super.session.createQuery("from Matricula where alumno=" + alumno.getNia());
        lista = query.list();

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoMatricula-buscarPorAlumno(): " + ex.getMessage());
        }

        return lista;
    }

    public List<Matricula> buscarPorCursoEscolar(Integer curso_escolar) {
        super.conectar();

        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = super.session.createQuery("from Matricula where curso_escolar=" + curso_escolar);
        lista = query.list();

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoMatricula-buscarPorCursoEscolar(): " + ex.getMessage());
        }

        return lista;
    }

    public List<Matricula> buscarTodos() {
        super.conectar();

        List<Matricula> lista = new ArrayList<Matricula>();

        Query query = super.session.createQuery("from Matricula");
        lista = query.list();

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoMatricula-buscarTodos(): " + ex.getMessage());
        }

        return lista;
    }
}