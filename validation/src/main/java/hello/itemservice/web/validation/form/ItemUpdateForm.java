package hello.itemservice.web.validation.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class ItemUpdateForm {
	
	
	@NotNull
	private Long id;
	
	@NotBlank
	private String itemName; 
	
	@NotNull
	@Range(min = 1000, max = 100000)
    private Integer price;
	
	//수정시 수량 자율변경
    private Integer quantity;
	
}
