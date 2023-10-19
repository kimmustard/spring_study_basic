package hello.hellospring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public class MemoryMemberRepository implements MemberRepository{

	private static HashMap<Long,Member> store = new HashMap<>();
	private static long sequence=0;
	
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		//널이 있을 가능성이 있는 경우 옵셔널로 감싸면 
		//클라이언트가(화면개발자) 뭔가를 할 수 있음
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
		.filter(member->member.getName()
				.equals(name)).findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

}
