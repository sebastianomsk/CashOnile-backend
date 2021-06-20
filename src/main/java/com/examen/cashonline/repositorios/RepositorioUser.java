package com.examen.cashonline.repositorios;

import org.springframework.data.repository.CrudRepository;
import com.examen.cashonline.entidades.User;


public interface RepositorioUser extends CrudRepository<User, Long>{
}
