package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
class BackToHome implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest= (HttpServletRequest) arg0;
		HttpServletResponse httpServletResponse =(HttpServletResponse) arg1;
		String url =httpServletRequest.getServletPath();
		if(url.endsWith(".jsp") && !url.equals("/login.jsp")&&!url.equals("/register.jsp")) {
			httpServletResponse.sendRedirect("index");
		}
		arg2.doFilter(arg0, arg1);
	}

}
