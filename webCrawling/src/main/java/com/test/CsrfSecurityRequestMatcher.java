package com.test;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CsrfSecurityRequestMatcher implements RequestMatcher{

	private Pattern allowedMethods = Pattern.compile("GET");	//허용할 http method (post, head, options, delete, connect 는 막힘)
	private RegexRequestMatcher unprotectedURL = new RegexRequestMatcher("/main", null);	//허용할 url과 method
	
	@Override
	public boolean matches(HttpServletRequest request) {
		System.out.println("** CsrfSecurityRequestMatcher, 현재 method : "+request.getMethod());
		if(allowedMethods.matcher(request.getMethod()).matches()){
			return false;
		}
		return !unprotectedURL.matches(request);
	}

}
