package com.examen.cashonline.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.LinkedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.examen.cashonline.entidades.Loan;
import com.examen.cashonline.servicios.ServicioLoan;
import com.examen.cashonline.servicios.ServicioUser;
import org.springframework.web.bind.annotation.RequestParam;
import javassist.NotFoundException;

@Controller
@CrossOrigin("*")
@RequestMapping("/loans")
public class ControllerLoan {
	Logger logger = LoggerFactory.getLogger(ControllerLoan.class);

	@Autowired
	ServicioLoan servicioLoan;
	@Autowired
	ServicioUser servicioUser;

	@SuppressWarnings("serial")
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerLoans(
			@RequestParam(name = "page", defaultValue = "1") final int page, 
			@RequestParam(name = "size", defaultValue = "5") final int size,
			@RequestParam (name = "user_id", required = false) final Long user_id
			) throws NotFoundException {
		if(page-1<0 || size<1) {
			logger.info("GET /loans?page="+page+"&size="+size+"&user_id="+user_id+"  "+ "BAD_REQUEST");	
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Pageable requestPageable = PageRequest.of(page-1, size);
		logger.info("GET /loans?page="+page+"&size="+size+"&user_id="+user_id);		
		Page<Loan> paginacion = user_id != null ? servicioLoan.obtenerLoansByUser(requestPageable, user_id):servicioLoan.obtenerLoansByUser(requestPageable) ;

		return ResponseEntity.ok(new LinkedHashMap<String, Object>() {
			{
				put("items", paginacion.getContent());
				put("paging", new LinkedHashMap<String, Object>() {
					{
						put("page", paginacion.getTotalPages());
						put("size", size);
						put("total", paginacion.getTotalElements());
					}
				});
			}
		});
	}

}

