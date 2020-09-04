package com.contratacion.proyecto.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.contratacion.proyecto.models.entities.Cargo;

public interface ICargo extends CrudRepository<Cargo, Integer>{

	@Query("SELECT M FROM Cargo M WHERE M.area.idarea = :id")	
	public List<Cargo> findByArea(Integer id);
}
