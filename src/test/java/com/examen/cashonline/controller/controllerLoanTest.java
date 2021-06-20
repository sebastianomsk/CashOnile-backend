package com.examen.cashonline.controller;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.examen.cashonline.Application;
import com.examen.cashonline.repositorios.RepositorioLoan;
import com.examen.cashonline.repositorios.RepositorioUser;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK,classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class controllerLoanTest extends Datos {
	 	@Autowired
	    private MockMvc mvc;

		@Autowired
		private RepositorioUser repositorioUser;
		@Autowired
		private RepositorioLoan repositorioLoan;
	    
	    @AfterEach
	    public void resetDb() {
			repositorioUser.deleteAll();
			repositorioLoan.deleteAll();
	    }
 
	    
	    @Test
	    public void getLoansConPageYSizeStatusEs200()
	    		throws IOException, Exception {

	        mvc.perform(get("/loans?page=1&size=5")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$[*]", hasSize(2)))
	          .andExpect(jsonPath("$.items", hasSize(5)))
	          .andExpect(jsonPath("$.paging.[*]", hasSize(3)))
	          .andExpect(jsonPath("$.paging.page", is(1)))
	          .andExpect(jsonPath("$.paging.size", lessThanOrEqualTo(5)))
	          .andExpect(jsonPath("$.paging.total", is(5)));
	    }
	    
	    @Test
	    public void getLoansConPageSizeYUserIdStatusEs200()
	    		throws IOException, Exception {

	        mvc.perform(get("/loans?page=1&size=50&user_id=2")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk())
	          .andExpect(content()
	          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	          .andExpect(jsonPath("$[*]", hasSize(2)))

	          .andExpect(jsonPath("$.items.[*]", hasSize(1)))
	          .andExpect(jsonPath("$.items.[0].total", equalTo(5000.0)))
	          .andExpect(jsonPath("$.items.[0].userId", is(2)))

	          .andExpect(jsonPath("$.paging.[*]", hasSize(3)))
	          .andExpect(jsonPath("$.paging.page", is(1)))
	          .andExpect(jsonPath("$.paging.size", is(50)))
	          .andExpect(jsonPath("$.paging.total", is(1)));
	    }
}
