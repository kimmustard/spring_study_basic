package hello.servlet.basic.request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;



@WebServlet(name = "requestBodyStringServlet" , urlPatterns = "/request-body-string")
public class RequestBodyStrtingServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream inputStream = request.getInputStream(); //이런식으로 바이트코드를 바로 얻을 수 있다.
	
		//바이트코드 -> String으로 변환
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // 바이트코드를 변환 할땐 항상 인코딩 정보가 무엇인지 알려줘야 한다.
		
		System.out.println("messageBody = " + messageBody);
		
		response.getWriter().write("Ok");
	}
	
	
}



