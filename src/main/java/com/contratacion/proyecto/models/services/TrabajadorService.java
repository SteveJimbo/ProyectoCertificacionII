package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.ITrabajador;
import com.contratacion.proyecto.models.entities.Trabajador;

@Service
public class TrabajadorService implements ITrabajadorService {
	@Autowired
	private ITrabajador dao;
	
	@Override
	@Transactional
	public void save(Trabajador t) {
		dao.save(t);
	}

	@Override
	@Transactional
	public Trabajador findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Trabajador> findAll() {
		return (List<Trabajador>) dao.findAll();
	}
}
