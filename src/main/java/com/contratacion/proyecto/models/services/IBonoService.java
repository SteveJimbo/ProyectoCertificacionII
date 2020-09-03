package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Bono;

public interface IBonoService {

	public void save(Bono c);
	public Bono findById(Integer id);
	public void delete(Integer id);
	public List<Bono> findAll();
}
