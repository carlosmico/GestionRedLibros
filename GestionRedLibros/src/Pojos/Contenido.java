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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "contenido")
public class Contenido implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column (name = "codigo")
    private String codigo_contenido;
    
    @ManyToOne
    @JoinColumn (name = "curso")
    private Curso curso_contenido;
    
    private String ensenanza;
    private String nombre_cas;
    private String nombre_val;
    
    @OneToMany (mappedBy = "contenido_libro", fetch=FetchType.LAZY)
    private List<Libro> libros;
    
    public Contenido(){
        
    }
    
    public Contenido(Curso curso, String codigo, String ensenanza, String nombre_cas, String nombre_val) {
        this.curso_contenido = curso;
        this.codigo_contenido = codigo;
        this.ensenanza = ensenanza;
        this.nombre_cas = nombre_cas;
        this.nombre_val = nombre_val;
    }
    

    public Contenido(int id, Curso curso, String codigo, String ensenanza, String nombre_cas, String nombre_val) {
        this.id = id;
        this.curso_contenido = curso;
        this.codigo_contenido = codigo;
        this.ensenanza = ensenanza;
        this.nombre_cas = nombre_cas;
        this.nombre_val = nombre_val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso_contenido;
    }

    public void setCurso(Curso curso) {
        this.curso_contenido = curso;
    }

    public String getCodigo() {
        return codigo_contenido;
    }

    public void setCodigo(String codigo) {
        this.codigo_contenido = codigo;
    }

    public String getEnsenanza() {
        return ensenanza;
    }

    public void setEnsenanza(String ensenanza) {
        this.ensenanza = ensenanza;
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

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Contenido other = (Contenido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return codigo_contenido + " - " + nombre_cas;
    }
}
