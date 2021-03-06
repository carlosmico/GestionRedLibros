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

/**
 *
 * @author Jose Sanchis
 */
import Utilidades.Colores;
import Utilidades.Configuracion;
import Utilidades.StringsGlobales;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class comboBoxRender extends BasicComboBoxUI {

    public static ComboBoxUI createUI(JComponent c) {
        return new comboBoxRender();
    }

    protected JButton createArrowButton() {
        Color fondo, letraNormal;
        try {
            fondo = Configuracion.getColor(StringsGlobales.color_fondo);
            letraNormal = Configuracion.getColor(StringsGlobales.color_letra_general);

        } catch (IOException e) {
            fondo = Colores.fondo;
            letraNormal = Colores.letraNormal;
        }
        BasicArrowButton bab = new BasicArrowButton(BasicArrowButton.SOUTH, fondo, fondo, letraNormal, fondo);
        return bab;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        return null;
    }

    public JTextField isEnabled(JTextField text) {
        text.setEditable(false);
        return text;
    }
}
