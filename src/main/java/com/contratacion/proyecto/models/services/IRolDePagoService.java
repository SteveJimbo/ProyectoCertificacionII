package com.contratacion.proyecto.models.services;

import java.util.List;

import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.reporting.RptAreaSanciones;
import com.contratacion.proyecto.models.reporting.RptCantidadMensual;
import com.contratacion.proyecto.models.reporting.RptMontoArea;

public interface IRolDePagoService {
	public void save(RolDePago r);
	public RolDePago findById(Integer id);
	public void delete(Integer id);
	public List<RolDePago> findAll();
	public Integer findLast();
	public boolean validarRol(RolDePago rol);
	public List<RptMontoArea> rptMontoArea(Integer mes, String anio, Integer idarea);
	public List<RptCantidadMensual> rptCantidadMensual(String Anio);
	public List<RptAreaSanciones> rptAreaSanciones(Integer mes, String anio);
}
