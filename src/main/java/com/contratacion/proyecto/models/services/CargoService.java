package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.ICargo;
import com.contratacion.proyecto.models.entities.Cargo;

@Service
public class CargoService implements ICargoService{
	@Autowired
	private ICargo dao;

	@Override
	@Transactional
	public void save(Cargo c) {
		dao.save(c);
	}

	@Override
	@Transactional
	public Cargo findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Cargo> findAll() {
		return (List<Cargo>) dao.findAll();
	}
}
