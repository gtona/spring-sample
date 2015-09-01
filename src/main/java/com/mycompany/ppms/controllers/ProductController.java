package com.mycompany.ppms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ProductController {
	
	final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(value = "/product/search", method = RequestMethod.GET)
	public void productSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = request.getParameter("shoeId");
		String errorText = String.format("Could not find any product matches '%s'", keyword);
		
		logger.info("productSearch called with '" + keyword + "'");
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		if("1".equals(keyword)) {
			response.getWriter().write("{\"name\":\"Very Nice Shoes\"}");
		} else {
			response.getWriter().write("{\"errorText\":\"" + errorText + "\"}");
		}
	}
}
