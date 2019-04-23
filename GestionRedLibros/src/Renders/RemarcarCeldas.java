/*
 * Copyright (C) 2019 Jose Sanchis
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
package Renders;

import Daos.DaoCurso;
import Pojos.Curso;
import Pojos.Ejemplar;
import Pojos.Historial;
import Pojos.Matricula;
import Utilidades.Colores;
import Vistas.FrameHistorial;
import Vistas.Main;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 *
 * @author Jose Sanchis
 */
public class RemarcarCeldas extends JLabel implements TableCellRenderer {

    public DaoCurso daoCurso;

    public RemarcarCeldas() {
        daoCurso = new DaoCurso(Main.gestorSesiones.getSession());
    }

    public Component getTableCellRendererComponent(JTable tbl, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        //TableCellRenderer renderer = tbl.getDefaultRenderer(tbl.getColumnClass(column));

        setOpaque(true);
        setFont(new Font("Dialog", Font.BOLD, 18));

        if (value instanceof Matricula) {
            Matricula matricula = (Matricula) value;

            Curso c = null;

            switch (column) {
                case 0:
                    setText(matricula.getContenido().getNombre_cas());
                    break;

                case 1:
                    c = sustituirPadresCursos(matricula);
                    setText(c.getAbreviatura() + " - " + c.getIdPadre());
                    setToolTipText(c.getAbreviatura() + " - " + c.getIdPadre());
                    break;

                case 2:
                    if (matricula.getIdioma().equals(" ")) {
                        setText("Por defecto");
                    } else {
                        setText(matricula.getIdioma());
                    }
                    break;
                case 3:
                    if (!matricula.getCurso_pendiente().equals(" ")) {
                        c = cursoPendiente(matricula);
                        setText(c.getAbreviatura() + " - " + c.getIdPadre());
                    } else {
                        setText("");
                    }
                    break;
            }
            getColor(this, matricula);
        } else if (value instanceof Historial) {
            Historial historial = (Historial) value;
            Ejemplar ejemplar = historial.getEjemplar();
            Curso curso = ejemplar.getLibro().getContenido().getCurso();

            switch (column) {
                case 0:
                    setText(ejemplar.getCodigo());
                    break;
                case 1:
                    setText(ejemplar.getLibro().getNombre());
                    break;
                case 2:
                    curso = sustituirPadre(curso);
                    setText(curso.getAbreviatura() + " - " + curso.getIdPadre());
                    break;
                case 3:
                    setText(historial.getCurso_escolar() + "/" + ((historial.getCurso_escolar() + 1) + "").substring(2));
                    break;
            }
        }
        return this;
    }

    public void desconectarDao() {
        daoCurso.desconectar();
    }

    /**
     * Colorea la fila de la tabla la cual pertenece a un curso que no se
     * corresponde a este año escolar, es decir, que el alumno esta repitiendo
     *
     * @param label El JLabel el cual vamos a colorear
     * @param matricula La matricula de la cual sacaremos los datos
     */
    public void getColor(JLabel label, Matricula matricula) {
        if (matricula.getCurso_pendiente().equals(" ")) {
            label.setForeground(Colores.accent);
            label.setBackground(Colores.fondo);
        } else {
            label.setForeground(Colores.accent);
            label.setBackground(Colores.tableCell);
        }
    }

    /**
     * Metodo para buscar el Padre de cada Curso y sustituir el atributo idPadre
     * por el nombre del Padre
     */
    private Curso sustituirPadresCursos(Matricula m) {

        Curso curso = daoCurso.buscar(m.getCurso());
        Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());

        if (cursoPadre != null) {
            curso.setIdPadre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
        }
        return curso;
    }

    /**
     * Metodo para buscar el Padre de cada Curso y sustituir el atributo idPadre
     * por el nombre del Padre
     */
    private Curso sustituirPadre(Curso curso) {
        Curso c = curso;
        Curso cursoPadre = daoCurso.buscar(c.getIdPadre());

        if (cursoPadre != null) {
            c.setIdPadre(daoCurso.buscar(c.getIdPadre()).getNombre_cas());
        }
        return c;
    }

    /**
     * Metodo para saber el nombre de los cursos pendientes de una matricula
     *
     * @param m MAtricula de la cual queremos saber los cursos pendientes
     * @return Curso pedndiente (objeto)
     */
    private Curso cursoPendiente(Matricula m) {

        Curso curso = daoCurso.buscar(m.getCurso_pendiente());
        Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());

        if (cursoPadre != null) {
            curso.setIdPadre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
        }
        return curso;
    }
}
