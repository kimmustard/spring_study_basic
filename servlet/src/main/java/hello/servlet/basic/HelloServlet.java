package hello.servlet.basic;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("HelloServlet.service");
		System.out.println("reqeust = " + request);
		System.out.println("response = " + response);
		
		
		//http://localhost:8080/hello?username=kim 라고 쿼리 스트링을 보내면?
		
		String username = request.getParameter("username");
		System.out.println("username = " + username);

		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		//getWriter를하면 Http Message Body에 값이 들어간다.
		response.getWriter().write("hello " + username);
		
		 
		
	}
	
	
}
