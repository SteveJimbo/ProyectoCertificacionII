package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contratacion.proyecto.models.dao.IBono;
import com.contratacion.proyecto.models.entities.Bono;

@Service
public class BonoService implements IBonoService{

	
	@Autowired
	private IBono dao;
	
	@Override
	public void save(Bono c) {
		dao.save(c);
		
	}

	@Override
	public Bono findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Bono> findAll() {
		return (List<Bono>) dao.findAll();
	}

}
