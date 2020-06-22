package com.contratacion.proyecto.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.ISancion;
import com.contratacion.proyecto.models.entities.Sancion;

@Service
public class SancionService implements ISancionService{
	
	@Autowired
	private ISancion dao;
	
	@Override
	@Transactional
	public void save(Sancion s) {
		dao.save(s);
	}

	@Override
	@Transactional
	public Sancion findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<Sancion> findAll() {
		return (List<Sancion>) dao.findAll();
	}

	@Override
	@Transactional
	public List<Sancion> findAll(Integer id) {
		List<Sancion> especificas = new ArrayList<Sancion>();
		List<Sancion> sanciones = (List<Sancion>) dao.findAll();
		for(Sancion s: sanciones) {
			if(s.getTrabajador().getIdtrabajador() == id) {
				especificas.add(s);
			}
		}
		return especificas;
	}
}
