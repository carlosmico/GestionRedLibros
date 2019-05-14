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
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class Configuracion {

    private static final String rutaConfiguracion = Thread.currentThread().getContextClassLoader().getResource("").getPath()
            + "config.properties";
    private static final Properties propiedades = new Properties();

    /**
     * CONFIGURACIÓN DE RED
     */
    /**
     * Metodo que guarda la configuración de red del programa.
     *
     * @param ip
     * @param puerto
     * @param usuario
     * @param password
     */
    public static void guardarRed(String ip, int puerto, String usuario, String password) {
        propiedades.setProperty("ip", ip);
        propiedades.setProperty("puerto", puerto + "");
        propiedades.setProperty("usuario", usuario);
        propiedades.setProperty("password", password);

        try {
            OutputStream output = new FileOutputStream(rutaConfiguracion);

            propiedades.store(output, null);
        } catch (Exception ex) {
            new FramePopup(new JFrame(), "Error al guardar la configuración de red.",
                    new ImageIcon("/Imagenes/icons/alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    public static String getIp() throws FileNotFoundException, IOException {
        try {
            propiedades.load(new FileInputStream(rutaConfiguracion));
            return propiedades.getProperty("ip");
        } catch (Exception e) {
            return "";
        }
    }

    public static String getPuerto() throws FileNotFoundException, IOException {
        try {
            propiedades.load(new FileInputStream(rutaConfiguracion));
            return propiedades.getProperty("puerto");
        } catch (Exception e) {
            return "-1";
        }
    }

    public static String getUsuario() throws FileNotFoundException, IOException {
        try {
            propiedades.load(new FileInputStream(rutaConfiguracion));
            return propiedades.getProperty("usuario");
        } catch (Exception e) {
            return "";
        }

    }

    public static String getPassword() throws FileNotFoundException, IOException {
        try {
            propiedades.load(new FileInputStream(rutaConfiguracion));
            return propiedades.getProperty("password");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * CONFIGURACIÓN DE COLORES
     */
    public static void guardarColor(String nombreColor, Color color) {
        propiedades.setProperty(nombreColor, Integer.toString(color.getRGB()));

        try {
            OutputStream output = new FileOutputStream(rutaConfiguracion);

            propiedades.store(output, null);
        } catch (Exception ex) {
            new FramePopup(new JFrame(), "Error al guardar la configuración de colores.",
                    new ImageIcon("/Imagenes/icons/alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    public static Color getColor(String nombreColor) throws FileNotFoundException, IOException {
        propiedades.load(new FileInputStream(rutaConfiguracion));

        String colorGuardado = propiedades.getProperty(nombreColor);

        Color color = null;

        try {
            color = new Color(Integer.parseInt(colorGuardado));
        } catch (Exception e) {
            System.out.println("Error - Fallo al cargar los colores.");

            switch (nombreColor) {
                case StringsGlobales.color_fondo:
                    color = Colores.fondo;
                    break;

                case StringsGlobales.color_fondo_oscuro:
                    color = Colores.fondoOscuro;
                    break;

                case StringsGlobales.color_fondo_botones:
                    color = Colores.botones;
                    break;

                case StringsGlobales.color_acentos:
                    color = Colores.accento;
                    break;

                case StringsGlobales.color_letra_botones:
                    color = Colores.letraBotones;
                    break;

                case StringsGlobales.color_letra_general:
                    color = Colores.letraNormal;
                    break;

                case StringsGlobales.color_letra_titulos:
                    color = Colores.letraTitulo;
                    break;

                case StringsGlobales.color_letra_noseleccionada:
                    color = Colores.campoTextSinFocus;
                    break;
            }
        }

        return color;
    }

    /**
     * CONFIGURACIÓN DE WALLPAPER
     */
    public static void guardarWallpaper(JFrame frame, String wallpaper) {
        propiedades.setProperty("wallpaper", wallpaper);

        try {
            OutputStream output = new FileOutputStream(rutaConfiguracion);

            propiedades.store(output, null);
        } catch (Exception ex) {
            ex.printStackTrace();

            new FramePopup(frame, "Error al guardar la configuración del wallpaper.",
                    new ImageIcon("/Imagenes/icons/alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    public static String getWallpaper() throws FileNotFoundException, IOException {
        try {
            propiedades.load(new FileInputStream(rutaConfiguracion));
            return propiedades.getProperty("wallpaper");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Metodo para guardar la configuracion de las etiquetas
     *
     * @param fila Indica las filas de etiquetas que tendra la hoja de impresión
     * @param columna Indica las columnas de etiquetas que tendra la hoja de
     * impresion
     */
    public static void guardarLayoutHoja(int fila, int columna) {
        try {
            propiedades.setProperty("filaHoja", fila + "");
            propiedades.setProperty("columnaHoja", columna + "");
        } catch (Exception e) {
            new FramePopup(null, "Error al guardar la configuración del layout.",
                    new ImageIcon("/Imagenes/icons/alert-black.png"),
                    "Aceptar").setVisible(true);
        }
    }

    /**
     * Metodo para conseugir las filas del layout de la hoja
     */
    public static int getFilasLayoutHoja() {
        int filas = 0;
        try {
            filas = Integer.parseInt(propiedades.getProperty("filaHoja"));
        } catch (NumberFormatException e) {
            System.out.println("No se ha podido parsear la propiedad");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("No se han podido cargar las filas");
            e.printStackTrace();
        }
        return filas;
    }

    /**
     * Metodo para conseguir las columnas del layout de la hoja
     */
    public static int getColumnaLayoutHoja() {
        int columnas = 0;
        try {
            columnas = Integer.parseInt(propiedades.getProperty("columnaHoja"));
        } catch (NumberFormatException e) {
            System.out.println("No se ha podido parsear la propiedad");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("No se han podido cargar las columnas");
            e.printStackTrace();
        }
        return columnas;
    }

}
