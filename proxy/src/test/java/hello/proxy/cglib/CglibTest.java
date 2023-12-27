package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CglibTest {

	
	@Test
	void cglib() {
		
		ConcreteService target = new ConcreteService();
		
		//CGLIB를 만드는 코드
		Enhancer enhancer = new Enhancer();
		
		//구체 클래스를 기반으로 ConcreteService를 상속받아서 프록시를 만든다.
		enhancer.setSuperclass(ConcreteService.class);
		enhancer.setCallback(new TimeMethodInterceptor(target));
		
		//ConcreteService 객체를 상속받았기 때문에 캐스팅이 가능하다 .
		ConcreteService proxy = (ConcreteService) enhancer.create();
		
		log.info("targetClass = {} ", target.getClass());
		log.info("proxyClass = {} ", proxy.getClass());
		
		
		proxy.call();
	}
}
