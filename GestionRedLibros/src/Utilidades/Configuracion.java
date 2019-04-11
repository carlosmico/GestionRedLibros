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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

    private static final Properties propiedades = new Properties();

    public static void guardarRed(String ip, int puerto, String usuario, String password) {
        propiedades.setProperty("ip", ip);
        propiedades.setProperty("puerto", puerto + "");
        propiedades.setProperty("usuario", usuario);
        propiedades.setProperty("password", password);
        
        try {
            OutputStream output = new FileOutputStream("path/to/config.properties");
            
            propiedades.store(output, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            
            JOptionPane.showMessageDialog(null, "Error al guardar la configuración", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String getIp(){
        return propiedades.getProperty("ip");
    }
    
    public static String getPuerto(){
        return propiedades.getProperty("puerto");
    }
    
    public static String getUsuario(){
        return propiedades.getProperty("usuario");
    }
    
    public static String getPassword(){
        return propiedades.getProperty("password");
    }
}
