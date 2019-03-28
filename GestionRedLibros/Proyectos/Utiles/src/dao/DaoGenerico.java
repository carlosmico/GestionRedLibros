package dao;

import java.io.Serializable;
import java.util.List;

import excepciones.BusinessException;
import hibernate.UtilesHibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DaoGenerico<T, ID extends Serializable> implements InterfaceDaoGenerico<T, ID> {

    SessionFactory factory;
    public Session session;

    @Override
    public Session conectar() {
        factory = UtilesHibernate.getSessionFactory();
        session = factory.getCurrentSession();
        session.beginTransaction();
        return session;
    }
    
    @Override
    public void desconectar() throws Exception {
        try {
            if (session != null) {
                session.close();
                session = null;
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
    

    @Override
    public void grabar(T objeto) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public void actualizar(T objeto) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public void grabarOActualizar(T objeto) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public void borrar(T objeto) throws BusinessException {
        throw new RuntimeException();
    }

    public void borrar(ID id) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public T buscarPorId(ID id) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public T buscarPorNombre(String nombre) throws BusinessException {
        throw new RuntimeException();
    }

    @Override
    public List<T> buscarTodos() throws BusinessException {
        throw new RuntimeException();
    }

}
