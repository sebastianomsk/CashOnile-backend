package com.examen.cashonline.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.examen.cashonline.entidades.User;


public interface RepositorioUser extends CrudRepository<User, Long>{
	Optional<User> findById(final Long id);
}
