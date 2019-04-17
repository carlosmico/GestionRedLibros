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
import Utilidades.Imagenes;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Jose Sanchis
 */
public class tableRender extends JLabel implements TableCellRenderer {

    Action buttonAction;

    public tableRender(Action action) {
        this.buttonAction = action;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Matricula matricula = (Matricula) value;

        if (matricula.getCurso_pendiente().equals(" ")) {
            setBackground(Colores.fondo);
        } else {
            setBackground(Colores.tableCell);
        }

        // Va a mostrar el botón solo en la última fila.
        // de otra forma muestra un espacio en blanco.
        if (column == table.getModel().getColumnCount() - 1) {
            //Cuando es la última columna creamos el botón
            JButton button = new JButton(buttonAction);
            button.setIcon(Imagenes.getImagen(new JFrame(), "plus.png"));
            return button;
        } else {
            return this;
        }
    }
}
