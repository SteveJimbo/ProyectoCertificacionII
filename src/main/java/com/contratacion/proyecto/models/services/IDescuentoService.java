package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Descuento;

public interface IDescuentoService {

	public void save(Descuento c);
	public Descuento findById(Integer id);
	public void delete(Integer id);
	public List<Descuento> findAll();
	public Descuento findNombre(String nombre);
}
