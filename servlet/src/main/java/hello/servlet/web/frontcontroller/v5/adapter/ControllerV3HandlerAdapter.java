package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{

	@Override
	public boolean supports(Object handler) {
		//Controller V3의 인스턴스를 구현한 무언가가넘어오면 true을 반환 아니면 false
		return (handler instanceof ControllerV3);


}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		
		//가져온 V3핸들러를 Object -> ControllerV3로 캐스팅함.
		//MemberFormControllerV3
		ControllerV3 controller = (ControllerV3) handler;
		
		Map<String, String> paramMap = createParamMap(request);
		ModelView mv = controller.process(paramMap);
		
		return mv;
	}
	
	
	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
		return paramMap;
	}
	
}