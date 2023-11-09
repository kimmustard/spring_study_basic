package hello.proxy.pureproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheProxy implements Subject{

	//프록시 입장에서 호출해야하는 대상을 타겟이라고 한다.
	private Subject target;
	private String cacheValue;
	
	public CacheProxy(Subject target) {
		this.target = target;
	}

	@Override
	public String operation() {
		log.info("프록시 호출");
		if(cacheValue == null) {
			cacheValue = target.operation();
		}
		
		return cacheValue;
	}
	
}
