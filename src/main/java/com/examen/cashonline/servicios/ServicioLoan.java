package com.examen.cashonline.servicios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.repositorios.RepositorioLoan;

@Service
public class ServicioLoan {
	@Autowired
	private RepositorioLoan repositorioLoan;

	Logger logger = LoggerFactory.getLogger(ServicioUser.class);
	
	public Iterable<Loan> obtenerLoans()  {
		return repositorioLoan.findAll();
	}
}
