package hello.itemservice.web.basic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

	private final ItemRepository itemRepository;

	//상품 리스트(메인)
	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items",items);
		return "basic/items";
	}
	
	//상품 상세조회
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute(item);
		return "basic/item";
	}
	
	//상품 등록 폼 (페이지이동)
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
	//상품 등록
//	@PostMapping("/add")
	public String addItemV1(
			@RequestParam String itemName,
			@RequestParam int price,
			@RequestParam Integer quantity,
			Model model) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		
		return "basic/item";
	}
	
	
	/**
	 * @ModelAttribute("item") Item item
	 * model.addAttribute("item", item); 자동 추가
	 */
	//상품 등록
//	@PostMapping("/add")
	public String addItemV2(@ModelAttribute("item") Item item) {

		itemRepository.save(item);
//		model.addAttribute("item", item);	//자동 추가, 생략가능
		
		return "basic/item";
	}
	
	
	/**
	 * @ModelAttribute name 생략 가능
	 * model.addAttribute(item); 자동 추가, 생략 가능
	 * 생략시 model에 저장되는 name은 클래스명 첫글자만 소문자로 등록 Item -> item
	 */
	//상품 등록
//	@PostMapping("/add")
	public String addItemV3(@ModelAttribute Item item) {
		itemRepository.save(item);
		
		return "basic/item";
	}
	
	
	/**
	 * @ModelAttribute 자체 생략 가능
	 * model.addAttribute(item) 자동 추가
	 */
	//상품 등록
//	@PostMapping("/add")
	public String addItemV4(Item item) {
		itemRepository.save(item);
		
		return "basic/item";
	}
	
	/**
	 * PRG - Post/Redirect/Get
	 */
	//상품 등록
//	@PostMapping("/add")
	public String addItemV5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/"+item.getId();
		//이 방식은 URL 인코딩문제가 생긴다.
	}
	
	
	
	//상품 등록
	@PostMapping("/add")
	public String addItemV6(Item item, RedirectAttributes rttr) {
		Item savedItem = itemRepository.save(item);
		rttr.addAttribute("itemId", savedItem.getId()); //치환이 된건 그대로 들어가고
		rttr.addAttribute("status", true); //치환이 아닌건 쿼리 파라미터로 들어간다 ?status=ture
		return "redirect:/basic/items/{itemId}";
		//이렇게 addAttribute를 이용하면 URL 인코딩도 자동으로 해결된다.
	}
	
	
	
	// 수정 form 조회
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item",item);
		return "basic/editForm";
	}
	
	// 수정 form 등록
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	
	
	/*
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}
	
}
