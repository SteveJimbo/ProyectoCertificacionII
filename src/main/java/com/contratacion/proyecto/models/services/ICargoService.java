package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Cargo;

public interface ICargoService{
	public void save(Cargo c);
	public Cargo findById(Integer id);
	public void delete(Integer id);
	public List<Cargo> findAll();
	public List<Cargo> findByArea(Integer id);
}
