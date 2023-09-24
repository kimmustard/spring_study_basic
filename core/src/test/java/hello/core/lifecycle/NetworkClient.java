package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetworkClient{

	
	private String url;

	public NetworkClient() {
		log.info("url = {} " , url);
	}
	
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	//서비스 시작시 호출하는 메서드
	public void connect() {
		log.info("connect  : " + url);
	}
	
	
	public void call(String message) {
		log.info("call : " + url + ", message = {}" + message);
	}
	
	
	//서비스 종료시 호출
	public void disconnect() {
		log.info("close : " + url);
	}


	//초기화 완료 콜백
	@PostConstruct
	public void init(){
		log.info("NetworkClient.init");
		connect();
		call("초기화 연결 메세지");
		
	}

	//소멸전 콜백
	@PreDestroy
	public void close(){
		log.info("NetworkClient.close");
		disconnect();
	}
	
	

	
}
