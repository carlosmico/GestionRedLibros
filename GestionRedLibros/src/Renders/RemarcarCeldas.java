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

import Pojos.Matricula;
import Utilidades.Colores;
import java.awt.Color;
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

    public RemarcarCeldas() {
    }

    public Component getTableCellRendererComponent(JTable tbl, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        TableCellRenderer renderer = tbl.getDefaultRenderer(tbl.getColumnClass(column));

        setOpaque(true);
        setFont(new Font("Dialog", Font.BOLD, 18));

        if (value instanceof Matricula) {
            Matricula matricula = (Matricula) value;

            switch (column) {
                case 0:
                    setText(matricula.getContenido().getNombre_cas());
                    break;

                case 1:
                    setText(matricula.getContenido().getCurso().getAbreviatura());
                    break;

                case 2:
                    if (matricula.getIdioma().equals(" ")) {
                        setText("Por defecto");
                    } else {
                        setText(matricula.getIdioma());
                    }
                    break;
            }
            getColor(this, matricula);
        }
        return this;
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
}
