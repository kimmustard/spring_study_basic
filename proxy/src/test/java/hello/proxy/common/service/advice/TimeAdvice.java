package hello.proxy.common.service.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TimeAdvice implements MethodInterceptor{
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();
		
		
		//타겟을 찾아서 알아서 호출해준다.
		Object result = invocation.proceed();
		
		long endTime = System.currentTimeMillis();
		long resulttime = endTime - startTime;
		
		log.info("TimeProxy 종료 resultTime = {}" , resulttime);
		return result;
	}

	
}
