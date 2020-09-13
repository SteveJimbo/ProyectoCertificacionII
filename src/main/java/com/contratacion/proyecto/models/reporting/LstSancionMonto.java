package com.contratacion.proyecto.models.reporting;

import java.io.Serializable;

public class LstSancionMonto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String sancion;
	private Float monto;
	
	public LstSancionMonto(String sancion, Float monto) {
		super();
		this.sancion = sancion;
		this.monto = monto;
	}
	
	public LstSancionMonto() {
		super();
	}

	public String getSancion() {
		return sancion;
	}

	public Float getMonto() {
		return monto;
	}

	public void setSancion(String sancion) {
		this.sancion = sancion;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}
	
}
