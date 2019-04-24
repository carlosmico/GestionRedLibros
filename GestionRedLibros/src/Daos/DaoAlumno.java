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
public class DaoAlumno extends DaoGenerico<Alumno, String> implements InterfaceDaoGenerico<Alumno, String> {

    /**
     * Variable de sesion utilizada para cualquier accion con la BD
     */
    public Session session;

    /**
     * Constructor del DaoAlumno que recibe una sesion
     *
     * @param s
     */
    public DaoAlumno(Session s) {
        this.session = s;
    }

    /**
     * Metodo para actualizar una lista de Alumnos en la BD
     *
     * @param alumnos
     * @throws Exception
     */
    public void actualizarAlumnos(List<Alumno> alumnos) throws Exception {
        for (int i = 0; i < alumnos.size(); i++) {
            Alumno a = alumnos.get(i);

            Alumno alumno = buscar(a.getNia());
            try {
                this.session.beginTransaction();

                if (alumno == null) {
                    alumno = new Alumno(
                            a.getNia(),
                            a.getNombre(),
                            a.getApellido1(),
                            a.getApellido2(),
                            a.getFecha_nac(),
                            a.getMunicipio_nac(),
                            a.getDocumento(),
                            a.getTelefono1(),
                            a.getSexo(),
                            a.getEmail1(),
                            a.getCurso(),
                            a.getGrupo()
                    );
                } else {
                    alumno.setNombre(a.getNombre());
                    alumno.setApellido1(a.getApellido1());
                    alumno.setApellido2(a.getApellido2());
                    alumno.setFecha_nac(a.getFecha_nac());
                    alumno.setMunicipio_nac(a.getMunicipio_nac());
                    alumno.setDocumento(a.getDocumento());
                    alumno.setTelefono1(a.getTelefono1());
                    alumno.setSexo(a.getSexo());
                    alumno.setEmail1(a.getEmail1());
                    alumno.setCurso(a.getCurso());
                    alumno.setGrupo(a.getGrupo());
                }

                this.session.saveOrUpdate(alumno);

                this.session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error DaoAlumno-actualizar(): " + e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Metodo para buscar un Alumno mediante su NIA en la BD
     *
     * @param nia
     * @return
     * @throws PersistenceException
     */
    public Alumno buscar(String nia) throws PersistenceException {
        Alumno alumno = null;

        alumno = (Alumno) this.session.get(Alumno.class, nia);

        return alumno;
    }

    /**
     * Metodo para obtener una lista de todos los Alumnos de la BD
     */
    public List<Alumno> buscarTodos() {
        List<Alumno> lista = new ArrayList<Alumno>();

        Query query = this.session.createQuery("from Alumno");
        lista = query.list();

        return lista;
    }

    /**
     * Metodo para desconectar la sesion del DAO
     */
    public void desconectar(){
        if (this.session != null) {
            try {
                this.session.close();
            } catch (Exception e) {
                System.out.println("Error DaoAlumno-desconectar()");
                throw e;
            }
        }
    }
}
