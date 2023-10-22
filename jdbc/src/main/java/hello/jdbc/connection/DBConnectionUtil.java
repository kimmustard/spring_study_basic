package hello.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBConnectionUtil {
	
	public static Connection getConnection() {
		try {
			Connection connection = 
					DriverManager.getConnection("jdbc:mysql://localhost:3306/springtoy", "springUser", "mysql");
			log.info("get connection = {}, class= {}", connection, connection.getClass());
			return connection;
			
		} catch (SQLException e) {
			throw new IllegalArgumentException(e);
		}
		
		
	}
	
}
