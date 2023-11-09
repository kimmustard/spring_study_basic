package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping	//스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식
@ResponseBody
public interface OrderControllerV1 {
 
	
	//인터페이스에서는 @RequestParam 이름을 명시해줘야한다.
	@GetMapping("/v1/request")
	String request(@RequestParam("itemId")String itemId);
	
	
	@GetMapping("/v1/no-log")
	String noLog();
}
