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
package Daos;

import Pojos.Alumno;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.Query;

/**
 *
 * @author Carlos
 */
public class DaoAlumno extends DaoGenerico<Alumno, String> implements InterfaceDaoGenerico<Alumno, String> {
    
    public Alumno buscar(String nia) throws PersistenceException {
        super.conectar();
        
        Alumno alumno;

        try {
            alumno = (Alumno) super.session.get(Alumno.class, nia);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoAlumno-buscarNIA(): " + ex.getMessage());
        }
        
        return alumno;
    }
    
    public List<Alumno> buscarTodos(){
        super.conectar();
        
        List<Alumno> lista = new ArrayList<Alumno>();
        
        Query query = super.session.createQuery("from Alumno");
        lista = query.list();
        
        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoAlumno-buscarTodos(): " + ex.getMessage());
        }
        
        return lista;
    }
}
