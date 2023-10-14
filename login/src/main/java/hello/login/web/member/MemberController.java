package hello.login.web.member;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {
	private final MemberRepository memberRepository;
	
	
	
	//여기서 Member 객체를 파라미터에 넣는이유?
	//검증기 사용시 되돌아가도 값이 남아있게 하려고한다.
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member") Member member) {
		return "members/addMemberForm";
	}
	
	
	
	@PostMapping("/add")
	public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "members/addMemberForm";
		}
		
		
		memberRepository.save(member);
		return "redirect:/";
		
	}
}
