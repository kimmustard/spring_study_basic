package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * 동시성 문제가 고려되어 있지 않음, 실수메어슨 concuurnetHashMap, AtomicLog 사용 고려
 */

public class MemberRepository {
	
	private static Map <Long,Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	private static final MemberRepository instance = new MemberRepository();
	
	
	public static MemberRepository getInstance() {
		return instance;
	}
	
	
	private MemberRepository() {
		
	}
	
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	
	
	public List<Member> findAll(){
		
		
		//store에 있는 리스트를 꺼내 ArrayList에 새로 넣는작업 기존 store 불변성유지
		return new ArrayList<>(store.values());
	}
	
	
	//스토어 전부삭제
	public void clearStore() {
		store.clear();
	}
	
	
	
	
}
