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
import Pojos.Ejemplar;
import Pojos.Historial;
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
public class DaoHistorial extends DaoGenerico<Historial, Integer> implements InterfaceDaoGenerico<Historial, Integer>{
    
    @Override
    public void grabar(Historial h) throws PersistenceException {
        super.conectar();

        try {
            super.session.save(h);

            super.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            System.out.println("Error DaoHistorial-grabar(): " + ex.getMessage());
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-grabar(): " + ex.getMessage());
        }
    }
    
    @Override
    public void actualizar(Historial h) throws PersistenceException {
        super.conectar();

        try {
            Historial historial = (Historial) session.get(Historial.class, h.getId());

            historial.setEjemplar(h.getEjemplar());
            historial.setAlumno(h.getAlumno());
            historial.setCurso_escolar(h.getCurso_escolar());
            historial.setEstado_inicial(h.getEstado_inicial());
            historial.setEstado_final(h.getEstado_final());
            historial.setFecha_inicial(h.getFecha_inicial());
            historial.setFecha_final(h.getFecha_final());
            historial.setObservaciones(h.getObservaciones());
            
            super.session.saveOrUpdate(historial);

            super.session.getTransaction().commit();
        } catch (PersistenceException ex) {
            System.out.println("Error DaoHistorial-actualizar(): " + ex.getMessage());
            throw new PersistenceException();
        }

        try {
            super.desconectar();
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-actualizar(): " + ex.getMessage());
        }
    }
    
    public Historial buscar(Integer id) throws PersistenceException {
        super.conectar();
        
        Historial historial;

        try {
            historial = (Historial) super.session.get(Historial.class, id);
        } catch (PersistenceException e) {
            System.out.println("Error DaoHistorial-buscarId(): " + e.getMessage());
            e.printStackTrace();
            throw new PersistenceException();
        }

        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-buscarId(): " + ex.getMessage());
        }
        
        return historial;
    }
    
    public List<Historial> buscarPorAlumno(Alumno alum){
        super.conectar();
        
        List<Historial> lista = new ArrayList<Historial>();
        
        Query query = super.session.createQuery("from Historial where alumno = " + alum.getNia());
        lista = query.list();
        
        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-buscarPorAlumno(): " + ex.getMessage());
        }
        
        return lista;
    }
    
    public List<Historial> buscarPorEjemplar(Ejemplar ejemplar){
        super.conectar();
        
        List<Historial> lista = new ArrayList<Historial>();
        
        Query query = super.session.createQuery("from Historial where ejemplar = " + ejemplar.getCodigo());
        lista = query.list();
        
        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-buscarPorEjemplar(): " + ex.getMessage());
        }
        
        return lista;
    }
    
    public List<Historial> buscarTodos(){
        super.conectar();
        
        List<Historial> lista = new ArrayList<Historial>();
        
        Query query = super.session.createQuery("from Historial");
        lista = query.list();
        
        try {
            super.desconectar();  
        } catch (Exception ex) {
            System.out.println("Error DaoHistorial-buscarTodos(): " + ex.getMessage());
        }
        
        return lista;
    }
    
}
