package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import excepciones.ConnectionException;

public class ConexionJdbc {
	//Atributo para guardar la conexion que se obtiene al conectar
	private static Connection con;
	
	//Atributos para almacenar los datos con los que se ha conectado
	private String driver, usr, url, psw;
	
	//Atributo para almacenar el nombre del fichero de configuracion con el que se ha conectado
	private static String ficheroConfiguracion;
	
	public ConexionJdbc(String driver, String url, String usr, String psw) {
		this.driver = driver;
		this.url = url;
		this.usr = usr;
		this.psw = psw;
		//conectar(driver, url, usr, psw);
	}
	
	public ConexionJdbc (String ficheroC) {
		this.ficheroConfiguracion = ficheroC;
		//conectar(ficheroC);
	}
	
	//Metodos para conectar
	public void conectar(String driver, String url, String usr, String psw) throws ConnectionException {
		try {
			Class.forName(driver).newInstance();

			con = DriverManager.getConnection(url, usr, psw);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void conectar(String ficheroC) throws ConnectionException {
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(ficheroC));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			
			con = ds.getConnection();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void conectar() {
		Properties propiedades = new Properties();
		try {
			propiedades.load(new FileInputStream(ficheroConfiguracion));
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			
			con = ds.getConnection();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Devolver la conexion establecida	
	public static Connection getConnection() {
		return con;
	}
	
	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cerrar(Statement s) {
		try {
			s.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cerrar(ResultSet r) {
		try {
			r.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
