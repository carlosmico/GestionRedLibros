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
import javax.persistence.Column;

//Imports Hibernate
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Carlos Micó
 */

@Entity
@Table(name = "grupos")
public class Grupo implements Serializable{
    
    @Id
    @Column (name = "codigo")
    private String codigo_grupo;
    
    private String nombre;
    private String ensenanza;
    private String turno;
    private String tutor_ppal;
    
    @OneToMany (mappedBy = "grupo")
    private List<Alumno> alumnos;
    
    public Grupo(){
        
    }

    public Grupo(String codigo, String nombre, String ensenanza, String turno, String tutor_ppal) {
        this.codigo_grupo = codigo;
        this.nombre = nombre;
        this.ensenanza = ensenanza;
        this.turno = turno;
        this.tutor_ppal = tutor_ppal;
    }

    public String getCodigo() {
        return codigo_grupo;
    }

    public void setCodigo(String codigo) {
        this.codigo_grupo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEnsenanza() {
        return ensenanza;
    }

    public void setEnsenanza(String ensenanza) {
        this.ensenanza = ensenanza;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTutor_ppal() {
        return tutor_ppal;
    }

    public void setTutor_ppal(String tutor_ppal) {
        this.tutor_ppal = tutor_ppal;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.codigo_grupo);
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
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.codigo_grupo, other.codigo_grupo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grupo{" + "codigo=" + codigo_grupo + ", nombre=" + nombre + ", ensenanza=" + ensenanza + ", turno=" + turno + ", tutor_ppal=" + tutor_ppal + '}';
    }
}
