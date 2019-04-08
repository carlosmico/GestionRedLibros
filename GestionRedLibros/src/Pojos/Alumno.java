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

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//Imports Hibernate
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.Fetch;


/**
 *
 * @author Carlos Micó
 */
@Entity
@Table(name = "alumnos")
public class Alumno implements Serializable {

    private static final long serialVersionUID = -5514760464301220827L;

    private String id;
    
    @Id
    private String nia;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String fecha_nac;
    private String municipio_nac;
    private String documento;
    private String telefono1;
    private String sexo;
    private String email1;
    
    @ManyToOne
    @JoinColumn (name = "curso")
    private Curso curso_alumno;
    
    @ManyToOne
    @JoinColumn (name = "grupo")
    private Grupo grupo;

    @OneToMany(mappedBy = "alumno", fetch=FetchType.LAZY)
    private List<Matricula> matriculas;

    @OneToMany(mappedBy = "alumno", fetch=FetchType.LAZY)
    private List<Historial> historiales;
    
    public Alumno() {

    }

    public Alumno(String nia, String nombre, String apellido1, String apellido2, String fecha_nac, String municipio_nac, String documento, String telefono1, String sexo, String email1, Curso curso_alumno, Grupo grupo) {
        this.id = nia;
        this.nia = nia;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fecha_nac = fecha_nac;
        this.municipio_nac = municipio_nac;
        this.documento = documento;
        this.telefono1 = telefono1;
        this.sexo = sexo;
        this.email1 = email1;
        this.curso_alumno = curso_alumno;
        this.grupo = grupo;
    }

    

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNia() {
        return nia;
    }

    public void setNia(String nia) {
        this.nia = nia;
        this.id = this.nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getMunicipio_nac() {
        return municipio_nac;
    }

    public void setMunicipio_nac(String municipio_nac) {
        this.municipio_nac = municipio_nac;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public Curso getCurso() {
        return curso_alumno;
    }

    public void setCurso(Curso curso) {
        this.curso_alumno = curso;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public List<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alumno other = (Alumno) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nia, other.nia)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido1, other.apellido1)) {
            return false;
        }
        if (!Objects.equals(this.apellido2, other.apellido2)) {
            return false;
        }
        if (!Objects.equals(this.fecha_nac, other.fecha_nac)) {
            return false;
        }
        if (!Objects.equals(this.municipio_nac, other.municipio_nac)) {
            return false;
        }
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.telefono1, other.telefono1)) {
            return false;
        }
        if (!Objects.equals(this.sexo, other.sexo)) {
            return false;
        }
        if (!Objects.equals(this.email1, other.email1)) {
            return false;
        }
        if (!Objects.equals(this.curso_alumno, other.curso_alumno)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.matriculas, other.matriculas)) {
            return false;
        }
        if (!Objects.equals(this.historiales, other.historiales)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", nia=" + nia + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", fecha_nac=" + fecha_nac + ", municipio_nac=" + municipio_nac + ", documento=" + documento + ", telefono1=" + telefono1 + ", sexo=" + sexo + ", email1=" + email1 + ", curso_alumno=" + curso_alumno + ", grupo=" + grupo + "}";
    }
}
