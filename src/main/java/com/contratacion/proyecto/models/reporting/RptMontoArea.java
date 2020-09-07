package com.contratacion.proyecto.models.reporting;

import java.io.Serializable;

public class RptMontoArea implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String area;
	private Float monto;
	
	public RptMontoArea(String area, Float monto) {
		super();
		this.area = area;
		this.monto = monto;
	}
	public RptMontoArea() {
		super();

	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
	
	
}
