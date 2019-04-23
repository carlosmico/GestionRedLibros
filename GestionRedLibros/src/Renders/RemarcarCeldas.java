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
import Pojos.Matricula;
import Utilidades.Colores;
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

    public RemarcarCeldas(DaoCurso dao) {
        daoCurso = dao;
    }

    public Component getTableCellRendererComponent(JTable tbl, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer renderer = tbl.getDefaultRenderer(tbl.getColumnClass(column));

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
                        c = cursosPendientes(matricula);
                        setText(c.getAbreviatura() + " - " + c.getIdPadre());
                    } else {
                        setText("");
                    }
                    break;
            }
            getColor(this, matricula);
        }
        return this;
    }

    public void desconectarDao() {
        daoCurso.desconectar();
    }

    public void getColor(JLabel label, Matricula matricula) {
        if (matricula.getCurso_pendiente().equals(" ")) {
            label.setForeground(Colores.accent);
            label.setBackground(Colores.fondo);
        } else {
            label.setForeground(Colores.accent);
            label.setBackground(Colores.tableCell);
        }
    }

    private Curso sustituirPadresCursos(Matricula m) {

        Curso curso = daoCurso.buscar(m.getCurso());
        Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());

        if (cursoPadre != null) {
            curso.setIdPadre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
        }
        return curso;
    }

    private Curso cursosPendientes(Matricula m) {

        Curso curso = daoCurso.buscar(m.getCurso_pendiente());
        Curso cursoPadre = daoCurso.buscar(curso.getIdPadre());

        if (cursoPadre != null) {
            curso.setIdPadre(daoCurso.buscar(curso.getIdPadre()).getNombre_cas());
        }
        return curso;
    }
}
