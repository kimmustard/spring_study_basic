package hello.core.discount;
import org.springframework.stereotype.Component;

import hello.core.member.Grade;
import hello.core.member.Member;


@Component
public class FixDiscountPolicy implements DiscountPolicy{

	private int discountFixAmount = 1000; //1000원 할인
	
	@Override
	public int discount(Member member, int price) {
		//Enum은 ==을 쓴다. equals 아님
		if (member.getGrade() == Grade.VIP) {
			return discountFixAmount;
		}else {
			return 0;
		}

	}

}
