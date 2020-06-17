package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Trabajador;

public interface ITrabajadorService {
	public void save(Trabajador t);
	public Trabajador findById(Integer id);
	public void delete(Integer id);
	public List<Trabajador> findAll();
}
