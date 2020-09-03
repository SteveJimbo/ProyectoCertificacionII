package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.Area;

public interface IAreaService {

	public void save(Area c);
	public Area findById(Integer id);
	public void delete(Integer id);
	public List<Area> findAll();
}
