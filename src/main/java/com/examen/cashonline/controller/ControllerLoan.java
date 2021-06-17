package com.examen.cashonline.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.servicios.ServicioLoan;

import javassist.NotFoundException;

@Controller
@CrossOrigin("*")
@RequestMapping("/loans")
public class ControllerLoan {
	Logger logger = LoggerFactory.getLogger(ControllerLoan.class);

	@Autowired
	ServicioLoan servicioLoan;

	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<Loan> obtenerLoans() throws NotFoundException {
		logger.info("GET obtener el usuario con id ");
		return servicioLoan.obtenerLoans();
	}
}
