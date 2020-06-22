package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Sancion;

public interface ISancionService {
	public void save(Sancion s);
	public Sancion findById(Integer id);
	public void delete(Integer id);
	public List<Sancion> findAll();
	public List<Sancion> findAll(Integer id);
}
