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
import javax.persistence.CascadeType;
import javax.persistence.Column;

//Imports Hibernate
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
@Table(name = "libros")
public class Libro implements Serializable{
    
    private String id;
    
    @Id
    @Column (name = "codigo")
    private String codigo_libro;
    
    @ManyToOne
    @JoinColumn (name = "id_contenido")
    private Contenido contenido_libro;
    
    private String nombre;
    private String ISBN;
    private int unidades;
    private boolean obsoleto;
    private double precio;
    
    @OneToMany (cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "libro")
    private List<Ejemplar> ejemplares;
    
    public Libro(){
        
    }

    public Libro(String codigo, Contenido contenido, String nombre, String ISBN, int unidades, boolean obsoleto, double precio) {
        this.codigo_libro = codigo;
        this.id = codigo;
        this.contenido_libro = contenido;
        this.nombre = nombre;
        this.ISBN = ISBN;
        this.unidades = unidades;
        this.obsoleto = obsoleto;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo_libro;
    }

    public void setCodigo(String codigo) {
        this.codigo_libro = codigo;
        this.id = codigo;
    }

    public Contenido getContenido() {
        return contenido_libro;
    }

    public void setContenido(Contenido contenido) {
        this.contenido_libro = contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public boolean getObsoleto() {
        return obsoleto;
    }

    public void setObsoleto(boolean obsoleto) {
        this.obsoleto = obsoleto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplares) {
        this.ejemplares = ejemplares;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.codigo_libro);
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
        final Libro other = (Libro) obj;
        if (!Objects.equals(this.codigo_libro, other.codigo_libro)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
