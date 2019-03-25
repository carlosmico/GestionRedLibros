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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ejemplares")
public class Ejemplar implements Serializable {
    
    @Id
    @Column (name = "codigo")
    private String codigo_ejemplar;
    
    @ManyToOne
    @JoinColumn (name = "id_libro")
    private Libro libro;
    
    private int estado;
    private boolean prestado;
    
    @OneToMany(mappedBy = "ejemplar")
    private List<Historial> historiales;
    
    public Ejemplar(){
        
    }

    public Ejemplar(String codigo, Libro libro, int estado, boolean prestado) {
        this.codigo_ejemplar = codigo;
        this.libro = libro;
        this.estado = estado;
        this.prestado = prestado;
    }

    public String getCodigo() {
        return codigo_ejemplar;
    }

    public void setCodigo(String codigo) {
        this.codigo_ejemplar = codigo;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    public List<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.codigo_ejemplar);
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
        final Ejemplar other = (Ejemplar) obj;
        if (!Objects.equals(this.codigo_ejemplar, other.codigo_ejemplar)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "codigo=" + codigo_ejemplar + ", libro=" + libro + ", estado=" + estado + ", prestado=" + prestado + '}';
    }
}
