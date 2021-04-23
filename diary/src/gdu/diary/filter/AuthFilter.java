package gdu.diary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/_auth/*") 
// /Auth/IndexController -> 
//요청 1. encodingFilter 2. AuthFilter 3. IndexController 4.AuthFilter 5. EncodingFilter
public class AuthFilter implements Filter {

	//생성자
    public AuthFilter() {
    }

	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인이 되어 있지 않은 상태에서 "/Auth/"문자로 시작하는 요청이 redirect(index)
		//웹브라우저에서는 HttpServletRequest에서 요청을 받지만 여기서는 ServletRequest에서 요청을 받기 때문에 getsession이 바로 가능하지 않음. 따라서 형변환 해야함.
		//HttpServletResponse이 아닌 부모인 ServletResponse 으로 요청을 받기 때문에 send 불가능.따라서 형변환
		HttpServletRequest httpRequest = (HttpServletRequest)request; 
		HttpSession session = httpRequest.getSession();
		if(session.getAttribute("sessionMember")==null ) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/login");

			//디버깅
			System.out.println("로그인이 필요합니다.");
			
			return; //새로운 요청발생으로 doFilter메서드를 종료
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
