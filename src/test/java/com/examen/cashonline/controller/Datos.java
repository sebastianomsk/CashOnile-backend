package com.examen.cashonline.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.entidades.User;
import com.examen.cashonline.servicios.ServicioLoan;
import com.examen.cashonline.servicios.ServicioUser;

public class Datos {
	@Autowired
	private ServicioUser servicioUser;
	
	@Autowired
	private ServicioLoan servicioLoan;
	
	User pedro = new User();
	User juan = new User();
	User leo = new User();

	Loan prestamo1 = new Loan();
	Loan prestamo2 = new Loan();
	Loan prestamo3 = new Loan();
	Loan prestamo4 = new Loan();
	Loan prestamo5 = new Loan();
	
	@BeforeEach
	void init() {
		crearUsuarios();
		crearLoan();
	}
	
	private void crearUsuarios() {
		pedro.setFirstName("Pedro");
		pedro.setLastName("Lopez");
		pedro.setEmail("pedro@lopez.com");

		juan.setFirstName("Juan");
		juan.setLastName("Perez");
		juan.setEmail("juan@perez.com");

		leo.setFirstName("Leonardo");
		leo.setLastName("Cachi");
		leo.setEmail("leo@cachi.com");

		servicioUser.guardar(pedro);
		servicioUser.guardar(juan);
		servicioUser.guardar(leo);
	}
	
	private void crearLoan() {
		prestamo1.setTotal(new BigDecimal(10000));
		prestamo2.setTotal(new BigDecimal(15000));
		prestamo3.setTotal(new BigDecimal(5000));
		prestamo4.setTotal(new BigDecimal(55000));
		prestamo5.setTotal(new BigDecimal(1000));

		prestamo1.setUser(pedro);
		prestamo2.setUser(pedro);
		prestamo3.setUser(juan);
		prestamo4.setUser(leo);
		prestamo5.setUser(pedro);
		
		servicioLoan.guardarLoan(prestamo1);
		servicioLoan.guardarLoan(prestamo2);
		servicioLoan.guardarLoan(prestamo3);
		servicioLoan.guardarLoan(prestamo4);
		servicioLoan.guardarLoan(prestamo5);
	}
}