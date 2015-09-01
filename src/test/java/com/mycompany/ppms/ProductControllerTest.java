package com.mycompany.ppms;

import com.mycompany.ppms.controllers.ProductController;
import com.mycompany.ppms.models.Product;
import com.mycompany.ppms.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("test-servlet-context.xml")
public class ProductControllerTest {
	@Autowired @InjectMocks private ProductController subject;
	@Mock private ProductService productService;

	@Autowired private WebApplicationContext wac;

	private MockMvc mockMvc;

    @Before
    public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		MockitoAnnotations.initMocks(this);
    }

	// searching for a Product -----------------------------------------------------------------------------------------

	@Test
    public void validSearch_returnsProduct() throws Exception {
		String id = "1";

		when(productService.find("1"))
				.thenReturn(new Product("1", "stubbed product"));

        this.mockMvc.perform(get("/product/search")
        		.param("productId", id)
        		.accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.id").value("1"))
          .andExpect(jsonPath("$.name").value("stubbed product"));
    }
    
	@Test
	public void invalidSearch_returnsError() throws Exception {
		String id = "non-existent id";
		String errorText = String.format(
				"Could not find any product matches '%s'", id);

		this.mockMvc.perform(get("/product/search")
				.param("productId", id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorText").value(errorText));
	}

	// Adding a new Product --------------------------------------------------------------------------------------------

	@Test
	public void validProduct_isAddedToRepository() throws Exception {
		String body = "{\"" +
				"id\":\"" + "new id" + "\"," +
				"\"name\":\"" + "new name" + "\"" +
				"}";

		when(productService.add(any(Product.class)))
				.thenReturn(new Product("1", "stubbed product"));

		this.mockMvc.perform(post("/product/add")
				.content(body)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("successfully added new product"));
	}

	@Test
	public void invalidProduct_returnsError() throws Exception {
		String body = "{\"" +
				"id\":\"" + "new id" + "\"," +
				"\"name\":\"" + "new name" + "\"" +
				"}";

		this.mockMvc.perform(post("/product/add")
				.content(body)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.errorText").value("Could not add new product"));
	}

	//	describe("adding a new product") {
//		describe("adding a valid product") {
//			it("adds a new product to the repository") {
//				assert ...
//			}
//
//			it("increments product counter") {...}
//		}
//
//		describe("adding an invalid product") {
//			it ("returns some error") {
//				assert ...
//			}
//
//			it ("does not ad dto the db") {...}
//		}
//	}
}
