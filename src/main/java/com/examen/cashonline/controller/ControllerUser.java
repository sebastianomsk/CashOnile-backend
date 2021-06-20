package com.examen.cashonline.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.examen.cashonline.entidades.User;
import com.examen.cashonline.servicios.ServicioUser;

import javassist.NotFoundException;

@Controller
@CrossOrigin("*")
@RequestMapping("/users")
public class ControllerUser {
	Logger logger = LoggerFactory.getLogger(ControllerUser.class);

	@Autowired
	ServicioUser servicioUser;

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User obtenerUsuarioPorId(@PathVariable("id") final Long id) throws NotFoundException {
		logger.info("GET /users/ " + id);
		return servicioUser.obtenerUsuarioPorId(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ResponseBody
	public void crearUsuario(@RequestBody User usuario) {
		logger.info("POST /users");
		servicioUser.guardar(usuario);
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public void eliminarUsuarioPorId(@PathVariable("id") final Long id) {
		logger.info("DELETE /users/" + id);
		servicioUser.eliminar(id);
	}

}
