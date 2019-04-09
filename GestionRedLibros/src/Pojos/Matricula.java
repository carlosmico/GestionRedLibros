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
import java.sql.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Carlos Micó
 */
@Entity
@Table(name = "matricula")
public class Matricula implements Serializable {

    private static final long serialVersionUID = -5514760464301120887L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private int curso_escolar;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Alumno alumno;

    private String ensenanza;
    private String curso;
    
    @ManyToOne
    @JoinColumn(name = "contenido")
    private Contenido contenido;
    
    private String idioma;
    private String tipo_basico;
    private String tipo_predom;
    private String acis;
    private Date fec_ini_acis;
    private Date fec_fin_acis;
    private String cur_ref_acis;
    private String curso_pendiente;

    public Matricula() {

    }

    public Matricula(int curso_escolar, Alumno alumno, String ensenanza, String curso, Contenido contenido, String idioma, String tipo_basico, String tipo_predom, String acis, Date fec_ini_acis, Date fec_fin_acis, String cur_ref_acis, String curso_pendiente) {
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

    public Matricula(Integer id, int curso_escolar, Alumno alumno, String ensenanza, String curso, Contenido contenido, String idioma, String tipo_basico, String tipo_predom, String acis, Date fec_ini_acis, Date fec_fin_acis, String cur_ref_acis, String curso_pendiente) {
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

    public void setId(Integer id) {
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

    public String getCur_ref_acis() {
        return cur_ref_acis;
    }

    public void setCur_ref_acis(String cur_ref_acis) {
        this.cur_ref_acis = cur_ref_acis;
    }

    public String getCurso_pendiente() {
        return curso_pendiente;
    }

    public void setCurso_pendiente(String curso_pendiente) {
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
        return "Matricula{" + "id=" + id + ", curso_escolar=" + curso_escolar + ", ensenanza=" + ensenanza + ", curso=" + curso + ", contenido=" + contenido + ", idioma=" + idioma + ", tipo_basico=" + tipo_basico + ", tipo_predom=" + tipo_predom + ", acis=" + acis + ", fec_ini_acis=" + fec_ini_acis + ", fec_fin_acis=" + fec_fin_acis + ", cur_ref_acis=" + cur_ref_acis + ", curso_pendiente=" + curso_pendiente + '}';
    }

    

}
