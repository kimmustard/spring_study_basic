package hello.core.singleton;

public class SingletonService {

	/*
	 * 1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
	 * 2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
	 * 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
	 * 3. 딱 1개의 인스턴스만 존재해야하므로, 생성자를 private으로 막아서 
	 * 혹시라도 외부에서 new키워드로 객체 인스턴스가 생성되는것을 막는다.
	 */
	
	private static final SingletonService instance = new SingletonService();
	
	//static으로 만들어 놨기때문에 호출해도 같은 instance 객체를 호출한다
	public static SingletonService getInstance() {
		return instance;
	}
	
	//생성자
	private SingletonService() {
	}
	
	public void logic() {
		System.out.println("싱글톤 객체 로직 호출");
	}
	
	
	
}
