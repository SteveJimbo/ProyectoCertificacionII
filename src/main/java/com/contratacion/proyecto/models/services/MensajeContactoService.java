package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IMensajeContacto;
import com.contratacion.proyecto.models.entities.MensajeContacto;

@Service
public class MensajeContactoService implements IMensajeContactoService{
	
	@Autowired
	private IMensajeContacto dao;
	
	@Override
	@Transactional
	public void save(MensajeContacto m) {
		dao.save(m);
	}

	@Override
	@Transactional
	public MensajeContacto findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<MensajeContacto> findAll() {
		return (List<MensajeContacto>) dao.findAll();
	}

}
