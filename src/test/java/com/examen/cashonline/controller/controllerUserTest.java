package com.examen.cashonline.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.examen.cashonline.Application;
import com.examen.cashonline.CashOnilebackend.JsonUtil;
import com.examen.cashonline.entidades.User;
import com.examen.cashonline.repositorios.RepositorioLoan;
import com.examen.cashonline.repositorios.RepositorioUser;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@AutoConfigureTestDatabase
public class controllerUserTest extends Datos {
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
	public void getUserStatusEs200() throws IOException, Exception {

		mvc.perform(get("/users/1")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[*]", hasSize(5)))
				.andExpect(jsonPath("$.email", is("pedro@lopez.com")))
				.andExpect(jsonPath("$.firstName", is("Pedro")))
				.andExpect(jsonPath("$.lastName", is("Lopez")))
				.andExpect(jsonPath("$.loans", hasSize(3)))
				.andExpect(jsonPath("$.loans..total", hasSize(3)))
		        .andExpect(jsonPath("$.loans..total", containsInAnyOrder(10000.0, 15000.0, 1000.0)));
	}

	@Test
	public void deleteUserStatusEs200() throws Exception {
		
		mvc.perform(delete("/users/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		Optional<User> userId1 = repositorioUser.findById( 1L );
		Assert.assertTrue(userId1.isEmpty());
		
		Optional<User> userId2 = repositorioUser.findById( 2L );
		Assert.assertFalse(userId2.isEmpty());
	}
	
	@Test
	public void postUserStatusEs201()  throws IOException, Exception {
		User gaston = new User();
		
		gaston.setFirstName("gaston");
		gaston.setLastName("korban");
		gaston.setEmail("gaston@korban.com");
		
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(JsonUtil.toJson(gaston)))		
				.andExpect(status().isCreated());
		
		Iterable<User> found = repositorioUser.findAll();
		assertThat(found).extracting(User::getFirstName).hasSize(4);
        assertThat(found).extracting(User::getFirstName).contains("gaston");
        assertThat(found).extracting(User::getLastName).contains("korban");
        assertThat(found).extracting(User::getEmail).contains("gaston@korban.com");
        
	}
}
