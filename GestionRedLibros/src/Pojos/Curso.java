/*
 * Copyright (C) 2019 Carlos Micó
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
package Pojos;

import Daos.DaoCurso;
import Vistas.Main;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;

//Imports Hibernate
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Carlos Micó
 */
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

    private String id;

    @Id
    @Column(name = "codigo")
    private String codigo_curso;

    private String ensenanza;
    private String abreviatura;
    private String nombre_cas;
    private String nombre_val;
    private String idPadre;
    
    //Este atributo no persiste en la BD.
    @Transient 
    private String nombre_padre = "Bebesita, uuaaa!!!";

    @OneToMany(mappedBy = "curso_contenido", fetch = FetchType.LAZY)
    private List<Contenido> contenidos;

    @OneToMany(mappedBy = "curso_alumno", fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    public Curso() {

    }
    
    public Curso(String codigo) {
        this.codigo_curso = codigo;
        this.nombre_cas = codigo;
    }

    public Curso(String codigo, String ensenanza, String abreviatura, String nombre_cas, String nombre_val, String idPadre) {
        this.codigo_curso = codigo;
        this.id = codigo_curso;
        this.ensenanza = ensenanza;
        this.abreviatura = abreviatura;
        this.nombre_cas = nombre_cas;
        this.nombre_val = nombre_val;
        this.idPadre = idPadre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo_curso() {
        return codigo_curso;
    }

    public void setCodigo_curso(String codigo_curso) {
        this.codigo_curso = codigo_curso;
    }

    public String getCodigo() {
        return codigo_curso;
    }

    public void setCodigo(String codigo) {
        this.codigo_curso = codigo;
        this.id = codigo_curso;
    }

    public String getEnsenanza() {
        return ensenanza;
    }

    public void setEnsenanza(String ensenanza) {
        this.ensenanza = ensenanza;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNombre_cas() {
        return nombre_cas;
    }

    public void setNombre_cas(String nombre_cas) {
        this.nombre_cas = nombre_cas;
    }

    public String getNombre_val() {
        return nombre_val;
    }

    public void setNombre_val(String nombre_val) {
        this.nombre_val = nombre_val;
    }

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public List<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(List<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo_curso);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Curso other = (Curso) obj;
        if (!Objects.equals(this.codigo_curso, other.codigo_curso)) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        DaoCurso daoCurso = new DaoCurso(Main.gestorSesiones.getSession());
        Curso cursoPadre = daoCurso.buscar(idPadre);

        if (cursoPadre != null) {
            return abreviatura + " - " + cursoPadre.getNombre_cas();
        } else {
            return abreviatura + " - " + nombre_cas;
        }
    }*/

    @Override
    public String toString() {
        return abreviatura + " - " + idPadre;
        /*
        if (idPadre.equals(" ")) {
            return abreviatura + " - " + nombre_cas;
        }else{
            return abreviatura + " - " + idPadre;
        }*/
    }

}
