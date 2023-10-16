package hello.login.web.argumentresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("supportParameter 실행");
		
		boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);	// 파라미터의 @Login 애노테이션이 있는지 ?
		boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
		
		//애노테이션의 존재여부와 맞는 타입의 객체가 들어 왔는지 확인
		return hasLoginAnnotation && hasMemberType;
		
		//true면 아래 resolveArgument 메서드가 실행 false면 중단
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		log.info("resolveArgument 실행");
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return null;
		}
		
		Object member = session.getAttribute(SessionConst.LOGIN_MEMBER);
		return member;
	}

	
	
}
