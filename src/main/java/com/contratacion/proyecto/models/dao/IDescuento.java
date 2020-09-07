package com.contratacion.proyecto.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.contratacion.proyecto.models.entities.Descuento;

public interface IDescuento extends CrudRepository<Descuento, Integer>{
	
	@Query("SELECT M FROM Descuento M WHERE M.nombre = :nombre")	
	public Descuento findByNombre(String nombre);
	
}
