package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionV2Controller {
	
	//항상 스프링의 우선순위는 자세한것이 우선권을 가진다. 부모클래스 자식클래스도 마찬가지 
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public ErrorResult illegalExHandler(IllegalArgumentException e) {
		log.error("[exceptionHanlder] ex", e);
		return new ErrorResult("BAD", e.getMessage());
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResult> userExHandler(UserException e){
		log.error("[exceptionHanlder] ex", e);
		ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
		return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ErrorResult exHandler(Exception e) {
		log.error("[exceptionHandler] ex", e);
		return new ErrorResult("EX", "내부오류");
	}
	
	
	@GetMapping("/api2/members/{id}")
	public MemberDto getMember(@PathVariable("id")String id) {
		
		if(id.equals("ex")) {
			throw new RuntimeException("잘못된 사용자");
		}
		if(id.equals("bad")) {
			throw new IllegalArgumentException("잘못된 입력 값");
		}
		if(id.equals("user-ex")) {
			throw new UserException("사용자 오류");
		}
		
		
		
		return new MemberDto(id, "hello " + id);
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDto{
		private String memberId;
		private String name;
	}
	
}
