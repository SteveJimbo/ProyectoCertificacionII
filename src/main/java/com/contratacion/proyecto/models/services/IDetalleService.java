package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Detalle;

public interface IDetalleService {

	public void save(Detalle c);
	public Detalle findById(Integer id);
	public void delete(Integer id);
	public List<Detalle> findAll();
}
