package com.examen.cashonline;

import java.math.BigDecimal;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.entidades.User;
import com.examen.cashonline.repositorios.RepositorioLoan;
import com.examen.cashonline.repositorios.RepositorioUser;
import com.examen.cashonline.servicios.ServicioUser;

@Service
@SuppressWarnings("all")
public class Bootstrap implements InitializingBean {

	@Autowired
	private ServicioUser servicioUser;

	User pedro = new User();
	User juan = new User();
	User leo = new User();

	Loan prestamo1 = new Loan();
	Loan prestamo2 = new Loan();
	Loan prestamo3 = new Loan();
	Loan prestamo4 = new Loan();

	public void crearUsuarios() {
		pedro.setFirstName("Pedro");
		pedro.setLastName("Lopez");
		pedro.setEmail("pedro@lopez.com");

		juan.setFirstName("Juan");
		juan.setLastName("Perez");
		juan.setEmail("juan@perez.com");

		leo.setFirstName("Leonardo");
		leo.setLastName("Cachi");
		leo.setEmail("leo@cachi.com");


		prestamo1.setTotal(new BigDecimal(10000));
		prestamo2.setTotal(new BigDecimal(15000));
		prestamo3.setTotal(new BigDecimal(5000));
		prestamo4.setTotal(new BigDecimal(55000));

		pedro.setLoan(prestamo1);
		pedro.setLoan(prestamo2);
		juan.setLoan(prestamo3);
		leo.setLoan(prestamo4);

		servicioUser.guardar(pedro);
		servicioUser.guardar(juan);
		servicioUser.guardar(leo);
	}


	public void init_app() {
		crearUsuarios();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Running initialization");
		this.init_app();
	}
}
