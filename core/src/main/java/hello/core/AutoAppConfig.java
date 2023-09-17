package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

/*
 * @Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스프링빈으로 등록해준다.
 *
 */

@Configuration
@ComponentScan(
		//이렇게하면 member만 컴포넌트 스캔 대상이된다. ("지정한곳"부터 하위패키지까지 쭉 찾는다)
//		basePackages = "hello.core.member",
		
		//AutoAppConfig가 존재하는 hello.core 부터 시작된다
//		basePackageClasses = AutoAppConfig.class,
		//excludeFilters 모든 빈을 스캔하면서 제외할(필터) 명단
		//Configuration도 자동스캔 대상이라 제외를 하지않으면? 이중으로 스캔하기때문에 충돌이 일어난다.
		
		//default 값은 여기 클래스부터 하위 패키지를 전부 스캔한다.
		excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
		)
public class AutoAppConfig {
	
	
	@Bean(name = "memoryMemberRepository")
	MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
	
	
	
}
