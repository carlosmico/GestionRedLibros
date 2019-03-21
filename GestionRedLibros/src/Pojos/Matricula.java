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
import java.util.Date;

/**
 *
 * @author Carlos Micó
 */
public class Matricula implements Serializable {
    
    private int id;
    private int curso_escolar;
    private Alumno alumno;
    private String ensenanza;
    private String curso;
    private Contenido contenido;
    private String idioma;
    private String tipo_basico;
    private String tipo_predom;
    private String acis;
    private Date fec_ini_acis;
    private Date fec_fin_acis;
    private Curso cur_ref_acis;
    private Curso curso_pendiente;
    
    public Matricula(){
        
    }

    public Matricula(int id, int curso_escolar, Alumno alumno, String ensenanza, String curso, Contenido contenido, String idioma, String tipo_basico, String tipo_predom, String acis, Date fec_ini_acis, Date fec_fin_acis, Curso cur_ref_acis, Curso curso_pendiente) {
        this.id = id;
        this.curso_escolar = curso_escolar;
        this.alumno = alumno;
        this.ensenanza = ensenanza;
        this.curso = curso;
        this.contenido = contenido;
        this.idioma = idioma;
        this.tipo_basico = tipo_basico;
        this.tipo_predom = tipo_predom;
        this.acis = acis;
        this.fec_ini_acis = fec_ini_acis;
        this.fec_fin_acis = fec_fin_acis;
        this.cur_ref_acis = cur_ref_acis;
        this.curso_pendiente = curso_pendiente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurso_escolar() {
        return curso_escolar;
    }

    public void setCurso_escolar(int curso_escolar) {
        this.curso_escolar = curso_escolar;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getEnsenanza() {
        return ensenanza;
    }

    public void setEnsenanza(String ensenanza) {
        this.ensenanza = ensenanza;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipo_basico() {
        return tipo_basico;
    }

    public void setTipo_basico(String tipo_basico) {
        this.tipo_basico = tipo_basico;
    }

    public String getTipo_predom() {
        return tipo_predom;
    }

    public void setTipo_predom(String tipo_predom) {
        this.tipo_predom = tipo_predom;
    }

    public String getAcis() {
        return acis;
    }

    public void setAcis(String acis) {
        this.acis = acis;
    }

    public Date getFec_ini_acis() {
        return fec_ini_acis;
    }

    public void setFec_ini_acis(Date fec_ini_acis) {
        this.fec_ini_acis = fec_ini_acis;
    }

    public Date getFec_fin_acis() {
        return fec_fin_acis;
    }

    public void setFec_fin_acis(Date fec_fin_acis) {
        this.fec_fin_acis = fec_fin_acis;
    }

    public Curso getCur_ref_acis() {
        return cur_ref_acis;
    }

    public void setCur_ref_acis(Curso cur_ref_acis) {
        this.cur_ref_acis = cur_ref_acis;
    }

    public Curso getCurso_pendiente() {
        return curso_pendiente;
    }

    public void setCurso_pendiente(Curso curso_pendiente) {
        this.curso_pendiente = curso_pendiente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Matricula other = (Matricula) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Matricula{" + "id=" + id + ", curso_escolar=" + curso_escolar + ", alumno=" + alumno + ", ensenanza=" + ensenanza + ", curso=" + curso + ", contenido=" + contenido + ", idioma=" + idioma + ", tipo_basico=" + tipo_basico + ", tipo_predom=" + tipo_predom + ", acis=" + acis + ", fec_ini_acis=" + fec_ini_acis + ", fec_fin_acis=" + fec_fin_acis + ", cur_ref_acis=" + cur_ref_acis + ", curso_pendiente=" + curso_pendiente + '}';
    }
    
    
}
