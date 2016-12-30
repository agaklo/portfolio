package pl.system.db_examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private final String jdbcDriverStr;
	private final String jdbcURL;

	private Connection connection = null;
	
	public DbConnection(String jdbcDriverStr, String jdbcURL) {
		this.jdbcDriverStr = jdbcDriverStr;
		this.jdbcURL = jdbcURL;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException{
		if(connection == null){
			System.out.println("Acquiring new database connection");
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL);           	        
		}
		return connection;		
	}
	
	private void close(){
        try {
            if(connection!=null) connection.close();
        } catch(Exception e){}
    }	
}
