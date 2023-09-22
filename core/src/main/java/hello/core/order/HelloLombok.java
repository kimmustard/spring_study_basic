package hello.core.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Slf4j
public class HelloLombok {

	
	private String name;
	private int age;
	
	public static void main(String[] args) {

		HelloLombok helloLombok = new HelloLombok();
		helloLombok.setName("asdasd");
		helloLombok.setAge(30);
		log.info("helloLombok = ", helloLombok);
		
	}

	
}
