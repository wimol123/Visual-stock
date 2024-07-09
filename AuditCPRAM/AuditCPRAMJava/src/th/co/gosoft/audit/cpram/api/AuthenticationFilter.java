package th.co.gosoft.audit.cpram.api;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

	private final static Logger logger = Logger.getLogger(AuthenticationFilter.class);
	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		HttpSession session = req.getSession(false);
		uri = uri.toLowerCase();
		
//		logger.info("uri : "+uri);
		// && !uri.endsWith("/final_auditresult_form.jsp")
		if((session == null || req.getSession().getAttribute("sessionAuthen") == null)  
				&& !uri.endsWith("/index.jsp") 
				&& !uri.endsWith("/activate.jsp") 
				&& !uri.endsWith("/forget_password.jsp")
				&& !uri.endsWith("/change_password.jsp")
				&& uri.indexOf("/assets/")<0 
				&& uri.indexOf("/api/")<0 
				&& uri.indexOf("/connector_final_audit_result.jsp") < 0){
			
			res.sendRedirect(req.getContextPath()+"/index.jsp");	
			
			
		}else if (uri.indexOf("/logout")>-1) {
			session.removeAttribute("sessionAuthen");
			res.sendRedirect(req.getContextPath()+"/index.jsp");
		}else{		
			/*if((session == null || req.getSession().getAttribute("sessionAuthen") == null) && uri.indexOf("/api/") > 0 && !uri.endsWith("/authen") ) {
				res.sendRedirect(req.getContextPath()+"/index.jsp");
			}else {
				
			}*/
			
			chain.doFilter(request, response);
					
		}


	}

	public void destroy() {
		//close any resources here
	}
	
}
