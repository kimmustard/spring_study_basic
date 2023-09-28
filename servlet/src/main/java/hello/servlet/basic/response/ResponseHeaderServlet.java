package hello.servlet.basic.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "responseHeaderServlet" , urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet{
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//[ status - line ]
		response.setStatus(HttpServletResponse.SC_OK);
		
		//[ response - headers ]
		response.setHeader("Content-Type", "text/plain; charset=utf-8");	// 응답 타입 지정
		response.setHeader("Cache-Contorl", "no-cache, no-store, must-revalidate");	// 캐시 무효화 정보
		response.setHeader("Pragma","no-cache"); // 과거버전의 캐시 무효화 까지
		response.setHeader("my-header", "hello"); //내가 원하는 임의의 헤더도 가능하다.
		
		
		//[ Header 편의 메서드 ]
//		content(response);
		cookie(response);
		redirect(response);
		
		
		// [ Message body ]
		PrintWriter writer = response.getWriter();
		writer.print("Ok");
		
	}
	
	
	private void content(HttpServletResponse response) {
		 //Content-Type: text/plain;charset=utf-8
		 //Content-Length: 2
		 //response.setHeader("Content-Type", "text/plain;charset=utf-8");
		
		 response.setContentType("text/plain");
		 response.setCharacterEncoding("utf-8");
		 //response.setContentLength(2); //(생략시 자동 생성)
		}
	
	
	private void cookie(HttpServletResponse response) {
		 //Set-Cookie: myCookie=good; Max-Age=600;
		 //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
		
		 Cookie cookie = new Cookie("myCookie", "good");
		 cookie.setMaxAge(600); //600초
		 response.addCookie(cookie);
		}
	
	
	private void redirect(HttpServletResponse response) throws IOException {
		 //Status Code 302
		 //Location: /basic/hello-form.html
		
		 response.setStatus(HttpServletResponse.SC_FOUND); //302
		 response.setHeader("Location", "/basic/hello-form.html");
		 
		 
		 response.sendRedirect("/basic/hello-form.html");
		 
		}
	
}
