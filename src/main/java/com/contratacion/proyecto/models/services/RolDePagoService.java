package com.contratacion.proyecto.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IDetalle;
import com.contratacion.proyecto.models.dao.IRolDePago;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.entities.RolDePago;

@Service
public class RolDePagoService implements IRolDePagoService{
	
	@Autowired
	private IRolDePago dao;
	
	@Autowired
	private IDetalle daoDetalle;
	
	@Override
	@Transactional
	public void save(RolDePago r) {
		try {
			dao.save(r);
			for(Detalle d : r.getDetalles()) {
				d.setRolDePago(r);
				daoDetalle.save(d);
			}
		}catch(Exception ex) {
			throw ex;
		}
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
	
	@Override
	@Transactional
	public boolean validarRol(RolDePago rol) {
		for(RolDePago r : dao.findByTrabajador(rol.getTrabajador().getIdtrabajador())) {
			if(r.getMes().equals(rol.getMes()) && r.getAnio().equals(rol.getAnio())) {
				return false;
			}
		}
		return true;
	}
}
