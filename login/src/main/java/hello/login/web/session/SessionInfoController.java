package hello.login.web.session;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SessionInfoController {

	@GetMapping("/session-info")
	public String sessionInfo(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return "세션이 없습니다.";
		}
		
		
		//세션 데이터 출력
		session.getAttributeNames().asIterator()
				.forEachRemaining(name -> log.info("Session name = {}, value = {}", name ,session.getAttribute(name)));
	
	
		log.info("session ID = {}", session.getId());
		log.info("getMaxInactiveInterval = {}", session.getMaxInactiveInterval());	//세션 최대 유지 시간
		log.info("creationTime = {} ", new Date(session.getCreationTime()));	//세션 생성 시간
		log.info("lastAccressedTime = {}", new Date(session.getLastAccessedTime()));	//마지막 세션 접근 시간
		log.info("isNew = {}", session.isNew()); //새로 생성된 세션인가 ?
		
		return "세션 출력";
	
	}
	
}

