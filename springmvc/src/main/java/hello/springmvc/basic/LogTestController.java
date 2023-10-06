package hello.springmvc.basic;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LogTestController {

//	private final Logger log = LoggerFactory.getLogger(LogTestController.class);
	
	
	@GetMapping("/log-test")
	public String logTest() {
		String name = "Spring";
		
		System.out.println("name = " + name);
		
		// 로그 상태 레벨
		log.trace("trace log = {}" , name);
		log.debug("debug log = {}" , name);
		log.info("info log = {}", name);
		log.warn("warn log = {}", name);
		log.error("error log = {}", name);
		
		return "Ok";
	}
	
}
