package br.com.samuel.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.samuel.exceptions.ConnectionErrorException;

/**
 * @author Samuel Oliveira Chaves
 * @since 30-05-2019
 * Pattern Factory and Singleton
 */
public class ConnectionFactory {
	private static Connection connection;
	private static Log log = LogFactory.getLog(ConnectionFactory.class);
	private static final String SCHEMA_DEFAULT="maxPago";
	private static final Integer PORT=3306;

	public static Connection getConection() {
		createConnection(PORT,SCHEMA_DEFAULT);
		return connection;
	}
	public static Connection getConection(Integer port,String schema) {
		createConnection(port,schema);
		return connection;
	}
	
	public static void createConnection(Integer port,String schema) {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //String url = "jdbc:mysql:127.0.0.1:"+port+"/"+schema;
	        String url = "jdbc:mysql://localhost/"+schema;
	        String username = "root"; 
	        String password = StringUtils.EMPTY;
	        connection = DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {   
	    	log.error("Driver com.mysql.jdbc.Driver not found.");
	        throw new ConnectionErrorException("Driver com.mysql.jdbc.Driver not found.");
	    } catch (SQLException e) {
	    	log.error("Unable to connect to database.");
	    	 throw new ConnectionErrorException("Unable to connect to database.");
	    }
	}
	
}
