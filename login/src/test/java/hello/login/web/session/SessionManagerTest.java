package hello.login.web.session;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import hello.login.domain.member.Member;

public class SessionManagerTest {

	SessionManager sessionManager = new SessionManager();
	
	
	@Test
	void sessionTest() {
		
		//HttpServletResponse는 톰캣이 생성해서 사용하는것이라.. 테스트가 불가능하다 그래서
		//스프링이 가짜 response 객체를 지원해준다.
		
		//세션 생성
		MockHttpServletResponse response = new MockHttpServletResponse();
		Member member = new Member();
		sessionManager.createSession(member, response);
		
		//요청에 응답 쿠키 저장 
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(response.getCookies()); //mysessionId = 123213213-123123123-123123 UUID가 들어갔다.
		
		
		//세션 조회
		Object result = sessionManager.getSession(request);
		assertThat(result).isEqualTo(member);
		
		//세션 만료
		sessionManager.expire(request);
		Object expired = sessionManager.getSession(request);
		assertThat(expired).isNull();
	}
	
}
