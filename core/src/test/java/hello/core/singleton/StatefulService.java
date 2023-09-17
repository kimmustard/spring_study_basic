package hello.core.singleton;

public class StatefulService {
	
	/*
	 * 현재 문제점 
	 * StatefulService의 price필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
	 * 사용자A의 주문금액은 10000원이 되어야하는데, 20000원이라는 결과가 나왔다.
	 * 진짜 공유필드는 조심해야한다. 스프링빈은 항상 무상태(stateless)로 설계하자
	 */

//	private int price; //상태를 유지하는 필드. 원래 10000 -> 20000 바뀐것
	
	//상태
//	public void order(String name, int price) {
//		System.out.println("name = " + name + "price = " + price);
//		this.price= price;	//여기가 문제
//	}
	
	//무상태
	/*
	 * 이 객체 내부에선 더이상 공유되는 필드는 없다.
	 * 밖에 StatefulServiceTest에서 각 필드를 나눠 사용한다(무상태)
	 */
	public int order(String name, int price) {
		System.out.println("name = " + name + "price = " + price);
//		this.price= price;	//여기가 문제
		return price;
	}
	
	
	
//	public int getPrice() {
//		return price;
//	}
	
	
}
