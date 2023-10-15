package hello.upload.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemRepository itemRepository;
	private final FileStore fileStore;
	
	@GetMapping("/items/new")
	public String newItem(@ModelAttribute ItemForm form) {
		return "item-form";
	}
	
	@PostMapping("/items/new")
	public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		
		UploadFile attachFile = fileStore.storeFile(form.getAttachFile());
		
		List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
		
		// 데이터베이스에 저장
		Item item = new Item();
		item.setItemName(form.getItemName());
		item.setAttachFile(attachFile);
		item.setImageFiles(storeImageFiles);
		itemRepository.save(item);
		
		redirectAttributes.addAttribute("itemId", item.getId());
		
		return "redirect:/items/{itemId}";
	}
	
	
	@GetMapping("/items/{id}")
	public String items(@PathVariable Long id, Model model) {
		Item item = itemRepository.findById(id);
		model.addAttribute("item",item);
		return "item-view";
	}
	
	//업로드한 파일 이미지 표현하기
	//이건 보안에 취약하기때문에 추가적인 체크 로직이 필요하다.
	@ResponseBody
	@GetMapping("/images/{filename}")
	public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
		// file: c:/../1d2f348e-f347-499a-bb79-9286b8613165.png
		// 경로에 있는 파일에 접근하여 스트림으로 반환 해준다.
		return new UrlResource("file:"+ fileStore.getFullPath(filename));
	}
	
	
	@GetMapping("/attach/{itemId}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException{
		Item item = itemRepository.findById(itemId);
		String storeFileName = item.getAttachFile().getStoreFileName();
		String uploadFileName = item.getAttachFile().getUploadFileName();
		
		UrlResource resource = new UrlResource("file:"+ fileStore.getFullPath(storeFileName));
	
		
		log.info("uploadFileName = {}", uploadFileName);
		
		//파일명을 인코딩 해줌
		String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(resource);
	}
	
	
	
	
}
