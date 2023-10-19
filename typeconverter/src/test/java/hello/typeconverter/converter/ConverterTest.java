package hello.typeconverter.converter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import hello.typeconverter.type.IpPort;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;

public class ConverterTest {

	
	@Test
	void StringToInteger() {
		StringToIntegerConverter converter = new StringToIntegerConverter();
		Integer result = converter.convert("10");
		
		assertThat(result).isEqualTo(10);
		
	}
	
	@Test
	void IntegerToString() {
		IntegerToStringConverter converter = new IntegerToStringConverter();
		String result = converter.convert(10);
		
		assertThat(result).isEqualTo("10");
	}
	
	@Test
	void stringToIpPort() {
		IpPortToStringConverter coverter = new IpPortToStringConverter();
		IpPort source = new IpPort("127.0.0.1", 8080);
		String result = coverter.convert(source);
		assertThat(result).isEqualTo("127.0.0.1:8080");
	}
	
	@Test
	void ipPortToString() {
		StringToIpPortConverter converter = new StringToIpPortConverter();
		String source  = "127.0.0.1:8080";
		IpPort result = converter.convert(source);
	
		assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
	}
	
	
	
	
	
}
