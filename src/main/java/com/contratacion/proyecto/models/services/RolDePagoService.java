package com.contratacion.proyecto.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IDetalle;
import com.contratacion.proyecto.models.dao.IRolDePago;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.reporting.RptCantidadMensual;
import com.contratacion.proyecto.models.reporting.RptMontoArea;

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

	@Override
	public List<RptMontoArea> rptMontoArea(String mes, String anio) {
		return null;
	}
	
	@Override
	public List<RptCantidadMensual> rptCantidadMensual(String Anio){
		List<RptCantidadMensual> resultado = new ArrayList<RptCantidadMensual>();
		for(int m=1; m<13; m++) {
			String mes = "";
			switch(m){
				case 1:
					mes="Enero";
					break;
				case 2:
					mes="Febrero";
					break;
				case 3:
					mes="Marzo";
					break;
				case 4:
					mes="Abril";
					break;
				case 5:
					mes="Mayo";
					break;
				case 6:
					mes="Junio";
					break;
				case 7:
					mes="Julio";
					break;
				case 8:
					mes="Agosto";
					break;
				case 9:
					mes="Septiembre";
					break;
				case 10:
					mes="Octubre";
					break;
				case 11:
					mes="Noviembre";
					break;
				case 12:
					mes="Diciembre";
					break;
			};
			List<RolDePago> roles = dao.findByMes(mes);
			Integer cant = 0;
			Float sum = 0.0f;
			for(RolDePago r : roles) {
				if(r.getAnio().equals(Anio)) {
					cant++;
					sum+=r.getTotal();
				}
			}
			resultado.add(new RptCantidadMensual(mes,cant,sum));
		}
		return resultado;
	}
}
