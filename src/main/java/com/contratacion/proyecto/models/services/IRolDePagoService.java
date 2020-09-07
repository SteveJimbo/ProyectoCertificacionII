package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.RolDePago;

public interface IRolDePagoService {
	public void save(RolDePago r);
	public RolDePago findById(Integer id);
	public void delete(Integer id);
	public List<RolDePago> findAll();
	public Integer findLast();
	public boolean validarRol(RolDePago rol);
}
