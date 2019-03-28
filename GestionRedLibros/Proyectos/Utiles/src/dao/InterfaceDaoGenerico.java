package dao;

import java.io.Serializable;
import java.util.List;

import excepciones.BusinessException;
import org.hibernate.Session;

public interface InterfaceDaoGenerico <T, ID extends Serializable> {
    
        /**
         * Realiza la conexión con la BD.
         * @return Devuelve un objeto de tipo Session.
         */
        public Session conectar();
        
        /**
         * Realiza la desconexión con la BD.
         * @throws Puede lanzar una excepción si la desconexión falla.
         */
        public void desconectar() throws Exception;
	
	/**
	 * Persiste el objeto dado.
	 * Tras la inseci�n el objeto tendr� actualizado su identificador. 
	 * @param objeto Objeto a insertar.
	 * @throws BussinessException si el objeto ya existia
	 */
	public void grabar (T objeto) throws BusinessException;
	
	/**
	 * Actualiza los datos persistidos de un objeto ya existente en la BD con los datos 
	 * contenidos en el pojo.
	 * @param objeto Objeto del que se toman los datos.
	 * @throws BusinessException si el objeto no existe.
	 */
	public void actualizar (T objeto) throws BusinessException;
	
	/**
	 * Graba o actualiza el objeto seg�n convenga: Si existe lo actualiza y si 
	 * no existe lo graba.
	 * @param objeto Objeto del que se toman los datos
	 * @throws BusinessException
	 */
	public void grabarOActualizar (T objeto) throws BusinessException;
	
	/**
	 * Elimina el objeto indicado de la BD
	 * @param objeto Objeto a eliminar 
	 * @throws BusinessException si el objeto no existe o no se puede eliminar
	 */
	public void borrar (T objeto) throws BusinessException;
	
	/**
	 * Elimina el objeto indicado de la BD
	 * @param id Identificador del objeto a eliminar 
	 * @throws BusinessException si el objeto no existe o no se puede eliminar
	 */
	public void borrar (ID id) throws BusinessException;
	
	/**
	 * Devuelve el objeto cuyo id se indica-
	 * @param id identificador del objeto buscado
	 * @return el objeto buscado o null si no existe
	 */
	public T buscarPorId (ID id)  throws BusinessException;
	
	public T buscarPorNombre (String nombre)  throws BusinessException;
	
	/**
	 * Devuelve una lista con todos los objetos de la base de datos
	 * @return lista con todos los objetos o una lista vac�a si no hay ninguno.
	 */
	public List<T> buscarTodos()  throws BusinessException;
	
}
