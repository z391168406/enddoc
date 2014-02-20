package com.endDoc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncoddingFilter implements Filter {
	public EncoddingFilter() {
	}

	String encode = "UTF-8";

	public void init(FilterConfig filterConfig) throws ServletException {
		encode = filterConfig.getInitParameter("encode");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		chain.doFilter(request, response);

	}

	public void destroy() {

	}

}
