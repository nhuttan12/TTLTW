package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
@WebFilter("/*")
public class LanguageFilter implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		String lang=arg0.getParameter("lang_local");
		System.out.println(lang);
		if(lang!=null) {
			req.getSession().setAttribute("lang", lang);
	
		}
		arg2.doFilter(arg0, arg1);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}
	public static void main(String[] args) {
		
	}

}
