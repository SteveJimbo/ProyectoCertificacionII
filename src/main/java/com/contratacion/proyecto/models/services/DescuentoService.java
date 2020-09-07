package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contratacion.proyecto.models.dao.IDescuento;
import com.contratacion.proyecto.models.entities.Descuento;

@Service
public class DescuentoService implements IDescuentoService{

	@Autowired
	private IDescuento dao;
	
	@Override
	public void save(Descuento c) {
		dao.save(c);
		
	}

	@Override
	public Descuento findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Descuento> findAll() {
		return (List<Descuento>) dao.findAll();
	}

	@Override
	public Descuento findNombre(String nombre) {
		return dao.findByNombre(nombre);
	}

	
}
