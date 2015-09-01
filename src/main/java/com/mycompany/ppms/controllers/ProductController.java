package com.mycompany.ppms.controllers;

import com.mycompany.ppms.models.Product;
import com.mycompany.ppms.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired ProductService productService;

	@RequestMapping(value = "/product/search", method = RequestMethod.GET)
	public void productSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String productId = request.getParameter("productId");
		String errorText = String.format("Could not find any product matches '%s'", productId);
		
		logger.info("productSearch called with '" + productId + "'");
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Product product = productService.find(productId);
		if(product != null) {
			response.getWriter().write(product.toJson());
		} else {
			response.getWriter().write("{\"errorText\":\"" + errorText + "\"}");
		}
	}

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public void productAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String productId = request.getParameter("id");
		String productName = request.getParameter("name");
		String errorText = String.format("Could not add new product");

		logger.info("productAdd called with '" + productId + "'");

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		Product product = productService.add(new Product(productId, productName));
		if(product != null) {
			response.getWriter().write("{\"message\": \"successfully added new product\"}");
		} else {
			response.getWriter().write("{\"errorText\":\"" + errorText + "\"}");
		}
	}
}
