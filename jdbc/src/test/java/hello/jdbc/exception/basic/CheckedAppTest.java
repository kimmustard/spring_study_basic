package hello.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedAppTest {
	
	@Test
	void unchecked() {
		Controller controller = new Controller();
		assertThatThrownBy(() -> controller.request())
							.isInstanceOf(Exception.class);
	}
	
	@Test
	void printEx() {
		Controller controller = new Controller();
		try {
			controller.request();
		} catch (Exception e) {
//			e.printStackTrace(); 사용을 자제하자
			log.info("ex", e);
		}
	}
	
	
	static class Controller{
		Service service = new Service();
		
		public void request(){
			service.logic();
		}
	}

	static class Service{	
		
		Repository repository = new Repository();
		NetworkClient networkClient = new NetworkClient();
		
		public void logic(){
			repository.call();
			networkClient.call();
		}
		
	}
	
	static class NetworkClient{
		public void call(){
			throw new RuntimeConnectException("연결 실패");
		}
		
	}
	
	static class Repository{
		public void call(){
			try {
				runSQL();
			} catch (SQLException e) {
				//내부에서 체크예외를 잡아서 런타임으로 바꿔준다.
				throw new RuntimeSQLException();
			}
		}
																															
		public void runSQL() throws SQLException {
			throw new SQLException("ex");
		}
	}
	
	
	
	
	static class RuntimeConnectException extends RuntimeException{
		public RuntimeConnectException(String message) {
			super(message);
		}
	}
	
	static class RuntimeSQLException extends RuntimeException{
		public RuntimeSQLException() {
			
		}
		
		// cause ? 이전 예외까지 같이포함해서 예외를 발생시켜준다.
		public RuntimeSQLException(Throwable cause) {
			super(cause);
		}
	}
	
}
