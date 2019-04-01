/*
 * Copyright (C) 2019 Carlos
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
package Utilidades;

import Vistas.FramePopup;
import java.awt.Frame;

/**
 *
 * @author Carlos
 */
public class Dialogo {

<<<<<<< HEAD:GestionRedLibros/src/Utilidades/MostrarError.java
    private static FramePopup frameError = null;

    public static void mostrarError(String errores) {
        if (frameError == null) {
            frameError = new FramePopup("Revise los siguientes errores:" + errores + "");
        }
        frameError.setVisible(true);
=======
    private static FramePopup popup = null;

    public static void mostrarError(String errores) {
        popup = new FramePopup("<html>Revise los siguientes errores:" + errores + "</html>");
        
        popup.setTitle("Error");
        popup.setVisible(true);
    }
    
    public static void mostrarInformacion(String informacion) {
        popup = new FramePopup(informacion);
        
        popup.setTitle("Información");
        popup.setVisible(true);
>>>>>>> master:GestionRedLibros/src/Utilidades/Dialogo.java
    }
}
