package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

public class MemoryMemberRepositoryTest {

	MemberRepository repository = new MemoryMemberRepository();

	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");

		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		System.out.println("result = " + (result = member));
	
	}
}
