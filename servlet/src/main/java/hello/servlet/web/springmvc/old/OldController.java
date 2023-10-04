package hello.servlet.web.springmvc.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;



@Component("/springmvc/old-controller")
public class OldController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OldController.handleRequest");
		
		
		//InternalResourceViewResolver
		/*
		 * 스프링부트는 InternalResourceViewResolver 라는 뷰 리졸버를 자동으로 등록하는데, 이때
		 * application.properties에 등록한 spring.mvc.view.prefix , spring.mvc.view.suffix 설정정보를 사용해서 등록한다.
		 */
		
		
		return new ModelAndView("new-form");
	}

}
