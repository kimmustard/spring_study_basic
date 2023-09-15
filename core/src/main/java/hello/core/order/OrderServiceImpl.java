package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

	
	/*
	 * DIP를 지킨거 같지만?
	 * 인터페이스 뿐만아니라.. new RateDiscountPolicy()와 같은 구현체에도 의존하고있다!
	 * 인터페이스(DiscountPolicy) , 구현클래스(FixDiscountPolicy, RateDiscountPolicy) DIP위반
	 * 
	 * 그리고 FixDiscountPolicy -> RateDiscountPolicy 정책을 변경하는 순간
	 * OrderServiceImpl의 코드도 변경해야한다. OCP위반 
	 */
	
	/*
	 * 변경후 ->
	 * 생성자 주입을 통해 DIP원칙을 잘 지키고 있고, 현재 OrderServiceImpl은 
	 * FixDiscountPolicy가 들어올지, RateDiscountPolicy 들어올지 모른다 왜냐? 추상화에 의존하고 있기때문,,,
	 */
	
	private final MemberRepository memberRepository;
	private final DiscountPolicy discountPolicy;
	
	
	
	public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
		this.memberRepository = memberRepository;
		this.discountPolicy = discountPolicy;
	}



	@Override
	public Order createOrder(Long memberId, String itemName, int itemPrice) {
		
		Member member = memberRepository.findById(memberId);
		int discountPrice = discountPolicy.discount(member, itemPrice);
		
		return new Order(memberId, itemName, itemPrice, discountPrice);
	}

}
