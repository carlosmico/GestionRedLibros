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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Configuracion {

    private static final String rutaConfiguracion = Thread.currentThread().getContextClassLoader().getResource("").getPath()
            + "configuracion.properties";
    private static final Properties propiedades = new Properties();

    public static void guardarRed(String ip, int puerto, String usuario, String password) {
        propiedades.setProperty("ip", ip);
        propiedades.setProperty("puerto", puerto + "");
        propiedades.setProperty("usuario", usuario);
        propiedades.setProperty("password", password);

        try {
            OutputStream output = new FileOutputStream(rutaConfiguracion);

            propiedades.store(output, null);
        } catch (Exception ex) {
            ex.printStackTrace();

            new FramePopup("Error al guardar la configuración de red.",
                    new ImageIcon("/Imagenes/icons/alert-black.png"),
                     "Aceptar").setVisible(true);
        }
    }

    public static String getIp() throws FileNotFoundException, IOException {
        propiedades.load(new FileInputStream(rutaConfiguracion));
        return propiedades.getProperty("ip");
    }

    public static String getPuerto() throws FileNotFoundException, IOException {
        propiedades.load(new FileInputStream(rutaConfiguracion));
        return propiedades.getProperty("puerto");
    }

    public static String getUsuario() throws FileNotFoundException, IOException {
        propiedades.load(new FileInputStream(rutaConfiguracion));
        return propiedades.getProperty("usuario");
    }

    public static String getPassword() throws FileNotFoundException, IOException {
        propiedades.load(new FileInputStream(rutaConfiguracion));
        return propiedades.getProperty("password");
    }
}