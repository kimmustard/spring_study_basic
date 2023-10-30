package hello.jdbc.exception.translator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import hello.jdbc.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringExceptionTranslatorTest {

	DataSource dataSource;
	
	@BeforeEach
	void init() {
		dataSource = new DriverManagerDataSource(ConnectionConst.URL,ConnectionConst.USERNAME,ConnectionConst.PASSWORD);
		
	}
	
	@Test
	void sqlExceptionErrorCode() {
		String sql = "select bad grammer";
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeQuery();
		} catch (SQLException e) {
			assertThat(e.getErrorCode()).isEqualTo(1054);
			int errorCode = e.getErrorCode();
			log.info("errorCode =  {}", errorCode);
			log.info("error", e);
			
		}
		
	}
	
	
	@Test
	void exceptionTranslator() {
		
		String sql = "select bad grammer";
		
		try {
			Connection con = dataSource.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.executeQuery();
		} catch (SQLException e) {
			assertThat(e.getErrorCode()).isEqualTo(1054);
			
			SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
			
			
			DataAccessException resultEx = exTranslator.translate("select", sql, e);
			log.info("resultEx = {}" , resultEx);
			assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
			
		}
	
	}
	
}
