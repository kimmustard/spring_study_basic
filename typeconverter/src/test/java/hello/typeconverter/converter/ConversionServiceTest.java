package hello.typeconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import hello.typeconverter.type.IpPort;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;

public class ConversionServiceTest {

	@Test
	void conversionService() {
		//등록
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new StringToIntegerConverter());
		conversionService.addConverter(new IntegerToStringConverter());
		conversionService.addConverter(new StringToIpPortConverter());
		conversionService.addConverter(new IpPortToStringConverter());
		
		//사용
		assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
		assertThat(conversionService.convert(10, String.class)).isEqualTo("10");
		
		IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
		assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));
		String convert = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
		assertThat(convert).isEqualTo("127.0.0.1:8080");
	}
	
}
