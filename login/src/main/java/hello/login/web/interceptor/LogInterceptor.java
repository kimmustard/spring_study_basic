package hello.login.web.interceptor;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LogInterceptor implements HandlerInterceptor{
	
	private static final String LOG_ID = "logId";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		String uuid = UUID.randomUUID().toString();
		
		request.setAttribute(LOG_ID, uuid);	// 예외가 발생하더라도 uuid를 afterCompletion로 넘길 수 있다.
		
		//@RequestMapping : HandlerMethod
		//정적 리소스 : ResourceHttpRequestHandler
		
		//위에 두가지가 종류에 따라 핸들러 정보로 넘어오기 때문에 "타입"에 따라서 분기 처리가 필요하다.
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
		
		}
		
		log.info("Request [{}][{}][{}]", uuid, requestURI, handler);
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		log.info("postHandler [{}]", modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		String requestURI = request.getRequestURI();
		Object logId = (String) request.getAttribute(LOG_ID);
		
		log.info("Response [{}][{}][{}]", logId, requestURI, handler);
		
		if(ex != null) {
			log.error("afterCompletion error!!", ex);
		}
	}
	
	
	
}
