package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contratacion.proyecto.models.dao.IArea;
import com.contratacion.proyecto.models.entities.Area;

@Service
public class AreaService implements IAreaService {

	@Autowired
	private IArea dao;
	@Override
	public void save(Area c) {
		dao.save(c);
		
	}

	@Override
	public Area findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Area> findAll() {
		return (List<Area>) dao.findAll();
	}

}
