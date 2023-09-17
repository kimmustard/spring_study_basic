package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


@Configuration
public class AppConfig {

	/*
	 * 이 방식을 생성자 주입이라고 한다. AppConfig가 애플리케이션의 실제동작에 필요한 "구현 객체를 생성" 해준다. -
	 * MemberServiceImpl, OrderServiceImpl - MemoryMemberRepository,
	 * FixDiscountPolicy
	 * 
	 * AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입 해준다. - MemberServiceImpl ->
	 * MemoryMemberRepository - OrderServiceImpl -> MemoryMemberRepository,
	 * FixDiscountPolicy
	 */

	
	//@Bean memberService -> new MemoryMemberRepository()
	//@Bean orderService -> new MemoryMemberRepository() ? 이러면 싱글톤이 깨지는것이 아닌가???
	
	//결과적으로 memberRepository는 3번 호출 되어야한다.
	//call AppConfig.memberService
	//call AppConfig.memberRepository
	//call AppConfig.memberRepository
	//call AppConfig.orderService
	//call AppConfig.memberRepository
	
	//하지만? 의도와는 다르게
	//call AppConfig.memberService
	//call AppConfig.memberRepository
	//call AppConfig.orderService
	
	
	@Bean
	public MemberService memberService() {
		System.out.println("call AppConfig.memberService");
		return new MemberServiceImpl(memberRepository());
	}
	
	@Bean  
	public MemoryMemberRepository memberRepository() {
		System.out.println("call AppConfig.memberRepository");
		return new MemoryMemberRepository();
	}
	
	@Bean	
	public OrderService orderService() {
		System.out.println("call AppConfig.orderService");
		return new OrderServiceImpl(memberRepository(), discountPolicy());
	}
	
	@Bean
	public DiscountPolicy discountPolicy() {
//		return new FixDiscountPolicy();
		return new RateDiscountPolicy();
	}

}
