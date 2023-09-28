package hello.servlet.basic.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import hello.servlet.basic.HelloData;

@WebServlet(name = "responseJsonSerlvet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet{
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Content-Type: application/json
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		HelloData helloDate = new HelloData();
		helloDate.setUsername("kim");
		helloDate.setAge(20);
		
		
		// 위에 내용을 JSON으로 변환해야한다. {"username" : "kim" , "age" : 20}
		
		String result = objectMapper.writeValueAsString(helloDate);
		response.getWriter().write(result);
		
		
		
	}
}
