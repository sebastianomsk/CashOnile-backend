package com.examen.cashonline.repositorios;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examen.cashonline.entidades.Loan;

public interface RepositorioLoan extends JpaRepository<Loan, Long> {
	Page<Loan> findAllByUserId(Long userId ,Pageable pageable);
}
