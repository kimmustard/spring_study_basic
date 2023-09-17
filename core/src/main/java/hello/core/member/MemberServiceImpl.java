package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
	
	/*
	 * 이제 완벽히 추상화에만 의존하게 됐다.
	 */
	
	private final MemberRepository memberRepository;
	
	//@Autowired 는
	//ac.getBean(MemberRepository.class) 라는 코드가 자동으로 들어가는 느낌이라생각하자.
	@Autowired 
	public MemberServiceImpl(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	
	//"다형성" 덕분에 MemoryMemberRepository가 호출 된다.
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

	
	//테스트용
	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
}
