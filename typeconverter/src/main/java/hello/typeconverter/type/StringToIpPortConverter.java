package hello.typeconverter.type;

import org.springframework.core.convert.converter.Converter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort>{

	@Override
	public IpPort convert(String source) {
		log.info("converte source = {}" , source);
		//127.0.0.1:8080 -> IpPort 객체
		String[] split = source.split(":");
		String ip = split[0];
		int port = Integer.parseInt(split[1]);
		return new IpPort(ip, port);
	}

	
	
}
