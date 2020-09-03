package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contratacion.proyecto.models.dao.IDetalle;
import com.contratacion.proyecto.models.entities.Detalle;

@Service
public class DetalleService implements IDetalleService{

	
	@Autowired
	private IDetalle dao;
	
	@Override
	public void save(Detalle c) {
		dao.save(c);
		
	}

	@Override
	public Detalle findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Detalle> findAll() {
		return (List<Detalle>) dao.findAll();
	}

}
