package com.examen.cashonline.servicios;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.repositorios.RepositorioLoan;

import javassist.NotFoundException;

@Service
public class ServicioLoan {
	@Autowired
	private RepositorioLoan repositorioLoan;

	Logger logger = LoggerFactory.getLogger(ServicioUser.class);

	public Page<Loan> obtenerLoans(Pageable pegeable) {
		return repositorioLoan.findAll(pegeable);
	}

	public void guardarLoan(Loan loan) {
		repositorioLoan.save(loan);
	}

	public Page<Loan> obtenerLoansByUser(Pageable pegeable, Long userId) throws NotFoundException {
		return repositorioLoan.findAllByUserId(userId, pegeable);
	}
	
	public Page<Loan> obtenerLoansByUser(Pageable pegeable) throws NotFoundException {	
		return repositorioLoan.findAll(pegeable);
	}
}
