package com.contratacion.proyecto.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.contratacion.proyecto.models.entities.RolDePago;

public interface IRolDePago extends CrudRepository<RolDePago, Integer>{
	
	@Query("SELECT M FROM RolDePago M WHERE M.trabajador.idtrabajador = :id")	
	public List<RolDePago> findByTrabajador(Integer id);
	
}
