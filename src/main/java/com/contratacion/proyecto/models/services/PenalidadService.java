package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IPenalidad;
import com.contratacion.proyecto.models.entities.Penalidad;

@Service
public class PenalidadService implements IPenalidadService{
	@Autowired
	private IPenalidad dao;
	
	@Override
	@Transactional
	public void save(Penalidad p) {
		dao.save(p);
	}

	@Override
	@Transactional
	public Penalidad findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Penalidad> findAll() {
		return (List<Penalidad>) dao.findAll();
	}
}
