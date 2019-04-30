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
package Vistas;

import Utilidades.Colores;
import Utilidades.Configuracion;
import Utilidades.Imagenes.Imagenes;
import Utilidades.StringsGlobales;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class CargaInicial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cargaColores();
        
        try {
            new Main().setVisible(true);
        } catch (IOException ex) {
            ex.printStackTrace();
            new FramePopup("Error al iniciar el programa.", Imagenes.getImagen("alert-black.png"), "Aceptar").setVisible(true);
        }
    }
    
    private static void cargaColores() {
        try {
            Colores.fondo = Configuracion.getColor(StringsGlobales.color_fondo);
            Colores.fondoOscuro = Configuracion.getColor(StringsGlobales.color_fondo_oscuro);
            Colores.botones = Configuracion.getColor(StringsGlobales.color_fondo_botones);
            Colores.accento = Configuracion.getColor(StringsGlobales.color_acentos);
            Colores.letraNormal = Configuracion.getColor(StringsGlobales.color_letra_general);
            Colores.letraBotones = Configuracion.getColor(StringsGlobales.color_letra_botones);
            Colores.letraTitulo = Configuracion.getColor(StringsGlobales.color_letra_titulos);
            Colores.campoTextSinFocus = Configuracion.getColor(StringsGlobales.color_letra_noseleccionada);
        } catch (IOException ex) {
            new FramePopup("Error al cargar los colores del programa.",
                    Imagenes.getImagen("alert-black.png"), "Aceptar").setVisible(true);
        }
    }
}
