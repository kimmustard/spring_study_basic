package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MemberServiceTest {

	MemberService memberService = new MemberServiceImpl();
	
	//단위 테스트란? 스프링 도움없이 순수하게 자바코드로만 테스트 하는것을 의미
	@Test
	void join() {
		//given
		Member member = new Member(1L, "memberA", Grade.VIP);
		
		//when
		memberService.join(member);
		Member findMember = memberService.findMember(1L);
		
		
		//then
		assertThat(member).isEqualTo(findMember);
		
	}
}
