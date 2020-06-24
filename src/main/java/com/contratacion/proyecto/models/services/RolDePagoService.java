package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IRolDePago;
import com.contratacion.proyecto.models.entities.RolDePago;

@Service
public class RolDePagoService implements IRolDePagoService{
	@Autowired
	private IRolDePago dao;
	
	@Override
	@Transactional
	public void save(RolDePago r) {
		dao.save(r);
	}

	@Override
	@Transactional
	public RolDePago findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		dao.deleteById(id);
	}

	@Override
	@Transactional
	public List<RolDePago> findAll() {
		return (List<RolDePago>) dao.findAll();
	}

	@Override
	@Transactional
	public Integer findLast() {
		List<RolDePago> roles = (List<RolDePago>) dao.findAll();
		RolDePago rol = roles.get(roles.size()-1);
		return rol.getIdrol();
	}
}
