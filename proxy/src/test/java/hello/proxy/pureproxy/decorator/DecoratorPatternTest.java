package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponent;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPatternTest {

	
	@Test
	void noDecorator() {
		Component realComponent = new RealComponent();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		client.execute();
	}
	
	@Test
	void Decorator1() {
		Component realComponent = new RealComponent();
		Component messageComponent = new MessageDecorator(realComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(messageComponent);
		client.execute();
	}
	
	
	@Test
	void Decorator2() {
		Component realComponent = new RealComponent();
		Component messageComponent = new MessageDecorator(realComponent);
		Component timeComponent = new TimeDecorator(messageComponent);
		DecoratorPatternClient client = new DecoratorPatternClient(timeComponent);
		client.execute();
	}
}
