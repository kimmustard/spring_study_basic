package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/*
 * @Controller
 * 스프링이 자동으로 스프링빈으로 등록한다 
 * ( @Controller 내부에 @Component 애노테이션이 있어서 컴포넌트 스캔 대상이 됨. )
 * 스프링 MVC에서 애노테이션 기반 컨트롤러로 인식한다. <- RequestMappingHandlerMappin이 매핑정보로 인식한다.
 */


@Controller
public class SpringMemberFormControllerV1 {

	@RequestMapping("/springmvc/v1/members/new-form")
	public ModelAndView process() {
		
		return new ModelAndView("new-form");
	}
	
	
}
