package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Penalidad;

public interface IPenalidadService {
	public void save(Penalidad p);
	public Penalidad findById(Integer id);
	public void delete(Integer id);
	public List<Penalidad> findAll();
}
