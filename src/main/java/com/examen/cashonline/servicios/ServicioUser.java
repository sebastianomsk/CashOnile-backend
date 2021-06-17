package com.examen.cashonline.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examen.cashonline.entidades.User;
import com.examen.cashonline.repositorios.RepositorioUser;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ServicioUser {
	@Autowired
	private RepositorioUser repositorioUser;

	Logger logger = LoggerFactory.getLogger(ServicioUser.class);

	public User obtenerUsuarioPorId(Long idUsuario) throws NotFoundException {
		return repositorioUser.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("No existe el usuario con el id " + idUsuario));
	}

	public void guardar(User usuario) {
		try {
			repositorioUser.save(usuario);
		} catch (Exception exc) {
			logger.warn("Falló al guardar el usuario", exc);
		}
	}

}
