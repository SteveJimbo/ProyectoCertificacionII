package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.MensajeContacto;

public interface IMensajeContactoService {
	public void save(MensajeContacto m);
	public MensajeContacto findById(Integer id);
	public void delete(Integer id);
	public List<MensajeContacto> findAll();
}
