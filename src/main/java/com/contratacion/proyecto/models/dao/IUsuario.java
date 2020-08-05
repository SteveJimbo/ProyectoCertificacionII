package com.contratacion.proyecto.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.contratacion.proyecto.models.entities.Usuario;

public interface IUsuario extends CrudRepository<Usuario, Integer>{

	public Usuario findByNombre(String nombre);
}
