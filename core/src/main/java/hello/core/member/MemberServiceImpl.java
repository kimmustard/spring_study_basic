package hello.core.member;

public class MemberServiceImpl implements MemberService{
	
	
	//추상화에도 의존하고.. (인터페이스) , 구체화에도 의존하고있다 DIP 원칙 어긋남
	private MemberRepository memberRepository = new MemoryMemberRepository();

	
	//"다형성" 덕분에 MemoryMemberRepository가 호출 된다.
	@Override
	public void join(Member member) {
		memberRepository.save(member);
	}

	@Override
	public Member findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

}
