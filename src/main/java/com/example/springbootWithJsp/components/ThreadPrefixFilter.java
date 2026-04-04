package com.example.springbootWithJsp.components;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ThreadPrefixFilter extends OncePerRequestFilter {

	static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ThreadPrefixFilter.class);
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
	    String path = request.getRequestURI();

		return !(path.startsWith("/svl/")
				|| path.startsWith("/ws/")
				|| path.startsWith("/sign"));
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		Thread tt = Thread.currentThread();
		String oldName = tt.getName();
		try {
			int idx = oldName.lastIndexOf('-');
			String num = (idx >= 0) ? oldName.substring(idx + 1) : oldName;
			tt.setName("my-api-" + num);
			filterChain.doFilter(request, response);
		} finally {
			tt.setName(oldName);
		}
	}

}