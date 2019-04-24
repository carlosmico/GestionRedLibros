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

import Utilidades.Estado;
import java.io.Serializable;
import java.util.Date;

//Imports Hibernate
import javax.persistence.CascadeType;
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
@Table(name = "historial")
public class Historial implements Serializable {

    private static final long serialVersionUID = -5514760462301220827L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_ejemplar")
    private Ejemplar ejemplar;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    private int curso_escolar;
    private Integer estado_inicial;
    private Integer estado_final;
    private Date fecha_inicial;
    private Date fecha_final;
    private String observaciones;

    public Historial() {

    }

    public Historial(Ejemplar ejemplar, Alumno alumno, int curso_escolar, Integer estado_inicial, Integer estado_final, Date fecha_inicial, Date fecha_final, String observaciones) {
        this.ejemplar = ejemplar;
        this.alumno = alumno;
        this.curso_escolar = curso_escolar;
        this.estado_inicial = estado_inicial;
        this.estado_final = estado_final;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.observaciones = observaciones;
    }

    public Historial(Integer id, Ejemplar ejemplar, Alumno alumno, int curso_escolar, Integer estado_inicial, Integer estado_final, Date fecha_inicial, Date fecha_final, String observaciones) {
        this.id = id;
        this.ejemplar = ejemplar;
        this.alumno = alumno;
        this.curso_escolar = curso_escolar;
        this.estado_inicial = estado_inicial;
        this.estado_final = estado_final;
        this.fecha_inicial = fecha_inicial;
        this.fecha_final = fecha_final;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getCurso_escolar() {
        return curso_escolar;
    }

    public void setCurso_escolar(int curso_escolar) {
        this.curso_escolar = curso_escolar;
    }

    public int getEstado_inicial() {
        return estado_inicial;
    }

    public void setEstado_inicial(int estado_inicial) {
        this.estado_inicial = estado_inicial;
    }

    public int getEstado_final() {
        return estado_final;
    }

    public void setEstado_final(int estado_final) {
        this.estado_final = estado_final;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return ejemplar.getCodigo().toUpperCase() + " - " + ejemplar.getLibro().getNombre() + " - " + ejemplar.getLibro().getContenido().getNombre_cas();
    }
}
