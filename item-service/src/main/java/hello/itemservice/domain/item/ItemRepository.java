package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	//멀티 스레드 환경을 맞추려면 concurrentHashMap<>();, AtomicLong 을 사용하자
	private static final Map<Long, Item> store = new HashMap<>(); //static
	private static long sequence = 0L ; //static
	
	//상품 정보 저장
	public Item save(Item item) {
		item.setId(++sequence);
		store.put(item.getId(), item);
		return item;
	}
	
	//상품 조회
	public Item findById(Long id) {
		return store.get(id);
	}
	
	//상품 전체조회
	public List<Item> findAll() {
		//바로 store를 써도되지만 안전하게 한번더 ArrayList로 감싸서 반환
		return new ArrayList<>(store.values());
	}
	
	//상품 수정
	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId);
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}
	
	
	// 테스트에서 사용할 리스트 제거 메서드
	public void clearStore() {
		store.clear();
	}
	
	
}
