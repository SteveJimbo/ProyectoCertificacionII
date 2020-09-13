package com.contratacion.proyecto.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.contratacion.proyecto.models.dao.IArea;
import com.contratacion.proyecto.models.dao.IDescuento;
import com.contratacion.proyecto.models.dao.IDetalle;
import com.contratacion.proyecto.models.dao.IRolDePago;
import com.contratacion.proyecto.models.dao.ITrabajador;
import com.contratacion.proyecto.models.entities.Area;
import com.contratacion.proyecto.models.entities.Descuento;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.reporting.LstSancionMonto;
import com.contratacion.proyecto.models.reporting.RptAreaSanciones;
import com.contratacion.proyecto.models.reporting.RptCantidadMensual;
import com.contratacion.proyecto.models.reporting.RptMontoArea;

@Service
public class RolDePagoService implements IRolDePagoService{
	
	@Autowired
	private IRolDePago dao;
	
	@Autowired
	private IDetalle daoDetalle;
	
	@Autowired
	private IArea daoArea;
	
	@Autowired
	private ITrabajador daoTrabajador;
	
	@Autowired
	private IDescuento daoDescuento;
	
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
	public List<RptMontoArea> rptMontoArea(Integer mes, String anio, Integer idarea) {
		List<RptMontoArea> resultado = new ArrayList<RptMontoArea>();
		List<RolDePago> roles = new ArrayList<RolDePago>();
		Area a = daoArea.findById(idarea).get();
		List<Trabajador> trabajadores = (List<Trabajador>)daoTrabajador.findAll();
		for(Trabajador t : trabajadores) {
			if(t.getArea().getIdarea() == idarea) {
				roles.addAll(dao.findByTrabajador(t.getIdtrabajador()));
			}
		}
		String mese = "";
		switch(mes){
			case 1:
				mese="Enero";
				break;
			case 2:
				mese="Febrero";
				break;
			case 3:
				mese="Marzo";
				break;
			case 4:
				mese="Abril";
				break;
			case 5:
				mese="Mayo";
				break;
			case 6:
				mese="Junio";
				break;
			case 7:
				mese="Julio";
				break;
			case 8:
				mese="Agosto";
				break;
			case 9:
				mese="Septiembre";
				break;
			case 10:
				mese="Octubre";
				break;
			case 11:
				mese="Noviembre";
				break;
			case 12:
				mese="Diciembre";
				break;
		};
		Float monto = 0.0f;
		for(RolDePago r : roles) {
			if(r.getAnio().equals(anio) && r.getMes().equals(mese)) {
				monto+=r.getTotal();
			}
		}
		
		resultado.add(new RptMontoArea(a.getNombre(),monto));
		return resultado;
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
	
	@Override
	public List<RptAreaSanciones> rptAreaSanciones(Integer mes, String anio){
		List<RptAreaSanciones> res = new ArrayList<RptAreaSanciones>();
		String mese = "";
		switch(mes){
			case 1:
				mese="Enero";
				break;
			case 2:
				mese="Febrero";
				break;
			case 3:
				mese="Marzo";
				break;
			case 4:
				mese="Abril";
				break;
			case 5:
				mese="Mayo";
				break;
			case 6:
				mese="Junio";
				break;
			case 7:
				mese="Julio";
				break;
			case 8:
				mese="Agosto";
				break;
			case 9:
				mese="Septiembre";
				break;
			case 10:
				mese="Octubre";
				break;
			case 11:
				mese="Noviembre";
				break;
			case 12:
				mese="Diciembre";
				break;
		};
		
		List<RolDePago> roles = dao.findByMes(mese);
		
		List<Descuento> descuentos = (List<Descuento>)daoDescuento.findAll();
		
		List<Area> areas = (List<Area>)daoArea.findAll();
		
		for(Area a : areas) {
			List<LstSancionMonto> lstSM = new ArrayList<LstSancionMonto>();
			for(Descuento d : descuentos) {
				Float monto = 0f;
				for(RolDePago r : roles) {
					if(r.getAnio().equals(anio)) {
						if(r.getTrabajador().getArea().getNombre().equals(a.getNombre())) {
							for(Detalle det : r.getDetalles()) {
								if(det.getNombre().equals(d.getNombre())) {
									monto += Math.abs(det.getMonto()) ;
								}
							}
						}
					}
				}
				lstSM.add(new LstSancionMonto(d.getNombre(),monto));
			}
			res.add(new RptAreaSanciones(a.getNombre(),lstSM));
		}
		return res;
	}
}
