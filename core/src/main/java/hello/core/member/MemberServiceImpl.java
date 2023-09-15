package hello.core.member;

public class MemberServiceImpl implements MemberService{
	
	
	
	/*
	 * 이제 완벽히 추상화에만 의존하게 됐다.
	 */
	
	private final MemberRepository memberRepository;
	
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

}
