package hello.servlet.web.frontcontroller.v1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

public class MemberSaveControllerV1 implements ControllerV1{

	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		Member member = new Member(username , age);
		memberRepository.save(member);
		
		
		//"Model" 에 데이터를 보관해야한다. 
		request.setAttribute("member", member);
		
		
		//"view"로 보낸다
		String viewPath = "/WEB-INF/views/save-result.jsp";
		
		RequestDispatcher Dispatcher = request.getRequestDispatcher(viewPath);
		Dispatcher.forward(request, response);
		
		
	}

	 
}
