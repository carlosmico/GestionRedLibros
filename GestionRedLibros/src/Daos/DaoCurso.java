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

import Pojos.Curso;
import dao.DaoGenerico;
import dao.InterfaceDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class DaoCurso extends DaoGenerico<Curso, String> implements InterfaceDaoGenerico<Curso, String> {
    
    public Curso buscar(String codigo) throws PersistenceException {
        super.conectar();
        
        Curso curso;

        try {
            curso = (Curso) super.session.get(Curso.class, codigo);
        } catch (PersistenceException e) {
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoCurso-buscarCodigo(): " + ex.getMessage());
        }
        
        return curso;
    }
    
    public List<Curso> buscarTodos(){
        super.conectar();
        
        List<Curso> lista = new ArrayList<Curso>();
        
        Query query = super.session.createQuery("from Curso where ensenanza = 3 or ensenanza = 5");
        lista = query.list();
        
        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoCurso-buscarTodos(): " + ex.getMessage());
        }
        
        return lista;
    }
}

