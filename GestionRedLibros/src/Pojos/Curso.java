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
import java.util.Objects;

/**
 *
 * @author Carlos Micó
 */

public class Curso implements Serializable{
    private String codigo;
    private String ensenanza;
    private String abreviatura;
    private String nombre_cas;
    private String nombre_val;
    private String idPadre;
    
    public Curso(){
        
    }

    public Curso(String codigo, String ensenanza, String abreviatura, String nombre_cas, String nombre_val, String idPadre) {
        this.codigo = codigo;
        this.ensenanza = ensenanza;
        this.abreviatura = abreviatura;
        this.nombre_cas = nombre_cas;
        this.nombre_val = nombre_val;
        this.idPadre = idPadre;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codigo);
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
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso{" + "codigo=" + codigo + ", ensenanza=" + ensenanza + ", abreviatura=" + abreviatura + ", nombre_cas=" + nombre_cas + ", nombre_val=" + nombre_val + ", idPadre=" + idPadre + '}';
    }
    
    
}
