package th.co.gosoft.audit.cpram.api;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.ResourceFilterController;

public class ResourceFilter implements Filter {

	private final static Logger logger = Logger.getLogger(ResourceFilter.class);
	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();		
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//ResourceFilterController resourceFilterController = new ResourceFilterController(request);
		//resourceFilterController.searchLogoForEmail();
		
		chain.doFilter(request, response);
	}

	

}
