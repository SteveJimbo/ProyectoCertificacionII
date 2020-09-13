package com.contratacion.proyecto.models.reporting;

import java.io.Serializable;
import java.util.List;

public class RptAreaSanciones implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String area;
	
	private List<LstSancionMonto> sanciones;

	public RptAreaSanciones(String area, List<LstSancionMonto> sanciones) {
		super();
		this.area = area;
		this.sanciones = sanciones;
	}
	
	public RptAreaSanciones() {
		super();
	}

	public String getArea() {
		return area;
	}

	public List<LstSancionMonto> getSanciones() {
		return sanciones;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setSanciones(List<LstSancionMonto> sanciones) {
		this.sanciones = sanciones;
	}
	
	
}
