package hello.exception.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApiExceptionController {
	
	@GetMapping("/api/members/{id}")
	public MemberDto getMember(@PathVariable("id")String id) {
		
		if(id.equals("ex")) {
			// 설정한 오류페이지가 그대로 JSON에 입력된다..
			// 서버to서버나 안드로이드라면 이야기가 달라진다.
			// 문제를 해결하려면 오류페이지 컨트롤러도, JSON응답을 할 수 있도록 수정해야한다.
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
	
	// @ReponseStatus는 동적 변경이 어렵다
	@GetMapping("/api/response-status-ex1")
	public String responseStatusEx1() {
		throw new BadRequestException();
	}
	
	
	// 외부라이브러리, 이미있는 예외도 변경 할 수있게된다.
	@GetMapping("/api/response-status-ex2")
	public String responseStatusEx2() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
	}
	
	
	@GetMapping("/api/default-hanlder-ex")
	public String defaultException(@RequestParam Integer data) {
		return "ok";
	}
	
	
	@Data
	@AllArgsConstructor
	static class MemberDto{
		private String memberId;
		private String name;
	}
}
