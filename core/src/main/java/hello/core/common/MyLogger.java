package hello.core.common;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
//타겟이 클래스면 TARGET_CLASS , 인터페이스면 TARGET_INTERFACE 
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS )
public class MyLogger {
	
	private String uuid;
	private String requestURL;
	
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	public void log(String message) {
		System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
	}
	
	@PostConstruct
	public void init() {
		uuid = UUID.randomUUID().toString();
		System.out.println("[" + uuid + "] request scope bean create: " + this);
	}
	
	@PreDestroy
	public void close() {
		System.out.println("[" + uuid + "] request scope bean close: " + this);
	}
	
}
