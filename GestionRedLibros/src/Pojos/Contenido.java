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

/**
 *
 * @author Carlos Micó
 */

public class Contenido implements Serializable{
    
    private int id;
    private String curso;
    private String codigo;
    private String ensenanza;
    private String nombre_cas;
    private String nombre_val;
    
    public Contenido(){
        
    }

    public Contenido(int id, String curso, String codigo, String ensenanza, String nombre_cas, String nombre_val) {
        this.id = id;
        this.curso = curso;
        this.codigo = codigo;
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

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contenido{" + "id=" + id + ", curso=" + curso + ", codigo=" + codigo + ", ensenanza=" + ensenanza + ", nombre_cas=" + nombre_cas + ", nombre_val=" + nombre_val + '}';
    }
}
