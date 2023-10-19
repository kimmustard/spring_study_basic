package hello.typeconverter;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import hello.typeconverter.formatter.MyNumberFormatter;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;


@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addFormatters(FormatterRegistry registry) {
		//포맷터보다 컨버터가 우선순위이기 때문에 주석처리
//		registry.addConverter(new StringToIntegerConverter());
//		registry.addConverter(new IntegerToStringConverter());
		registry.addConverter(new StringToIpPortConverter());
		registry.addConverter(new IpPortToStringConverter());
		
		//추가
		registry.addFormatter(new MyNumberFormatter());
		
	}

	
	
}
