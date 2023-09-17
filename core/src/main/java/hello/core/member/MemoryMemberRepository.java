package hello.core.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component	// "m"emoryMemberRepository
public class MemoryMemberRepository implements MemberRepository{

	//원래는 동시성 문제 때문에 컨커런트 해쉬맵을 써야한다.
	private static Map<Long, Member> store = new HashMap<>();
	
	@Override
	public void save(Member member) {
		store.put(member.getId(), member);
	}

	@Override
	public Member findById(Long memberId) {
		return store.get(memberId);
	}

}
