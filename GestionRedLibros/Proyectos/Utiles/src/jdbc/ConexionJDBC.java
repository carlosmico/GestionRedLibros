package jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;


public class ConexionJDBC {
	private static Connection con;
	
	private String driver;
	private String url;
	private String usr;
	private String pwd;
	
	private String ficheroConfiguracion;
	
	public ConexionJDBC(String driver, String url, String usr, String pwd){
		this.driver = driver;
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}
	
	public ConexionJDBC(String ficheroConf) {
		this.ficheroConfiguracion = ficheroConf;
	}

	
	private void conectar(String driver, String url, String usr, String pwd) {
		this.driver = driver;
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}
	
	private void conectar(String ficheroConf) {
		this.ficheroConfiguracion = ficheroConf;
	}
	
	public void conectar() throws Exception {
		if(this.ficheroConfiguracion != null){
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(this.ficheroConfiguracion));
			
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
		}else {
			Class.forName(driver).newInstance();
			System.out.println("Registrado");
			
			con = DriverManager.getConnection(url, usr, pwd);
			System.out.println("Conectado");
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public void desconectar() {
		try {
			if(con != null)con.close();
		}catch(Exception e) {
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public void cerrar(ResultSet rs) {
		try {
			if(rs != null)rs.close();
		}catch(Exception e) {
			Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
public static void cerrar(Statement stmt) {
	try {
		if(stmt != null)stmt.close();
	}catch(Exception e) {
		Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, e.getMessage(), e);
	}
	}
}
