package com.contratacion.proyecto.models.reporting;

import java.io.Serializable;

public class RptCantidadMensual implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String mes;
	private Integer cantidad;
	
	public RptCantidadMensual(String mes, Integer cantidad) {
		super();
		this.mes = mes;
		this.cantidad = cantidad;
	}
	
	public RptCantidadMensual() {
		super();
	}

	public String getMes() {
		return mes;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
