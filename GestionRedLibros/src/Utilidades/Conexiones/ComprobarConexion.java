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
package Utilidades.Conexiones;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.sun.org.apache.xerces.internal.impl.io.MalformedByteSequenceException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jose Sanchis
 */
public class ComprobarConexion {

    /**
     * Metodo para comprobar la conexión a una URL, si existe conexión devolverá
     * True, en caso contrario False
     *
     * @return
     */
    public static boolean comprobarConexion(String ip, int puerto, String nombreBD, String usuario, String password) throws Exception  {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = null;
        con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + puerto + "/" + nombreBD, usuario, password);
        
        if(con.isValid(10000)){
            final PreparedStatement statement = con.prepareStatement("SELECT 1");
            
            if(statement != null){
                return true;
            }else{
                return false;
            }
            
        }else{
            con.close();
            return false;
        }
    }
}
