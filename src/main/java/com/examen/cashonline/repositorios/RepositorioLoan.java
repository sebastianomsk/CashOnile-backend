package com.examen.cashonline.repositorios;


import org.springframework.data.repository.CrudRepository;
import com.examen.cashonline.entidades.Loan;

public interface RepositorioLoan extends CrudRepository<Loan, Long> {

}
