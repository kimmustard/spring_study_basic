package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class MemberApp {

	public static void main(String[] args) {
	
//		AppConfig appConfig = new AppConfig();
//		MemberService memberService = appConfig.memberService();
		
		
		/*
		 *  스프링은 모든게 ApplicationContext에서 시작한다.
		 *  AppConfig 내부에있는 @Bean(환경설정정보)들을 가지고, 스프링컨테이너에 다 집어넣어 "관리"해준다
		 */
		
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

		Member member = new Member(1L,"memberA", Grade.VIP);
		memberService.join(member);
		
		Member findMember = memberService.findMember(1L);
		
		System.out.println("new Member = " + member.getName());
		System.out.println("find Member = " + findMember.getName());
	}
	
}
