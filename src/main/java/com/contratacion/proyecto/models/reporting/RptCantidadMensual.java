package com.contratacion.proyecto.models.reporting;

import java.io.Serializable;

public class RptCantidadMensual implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String mes;
	private Integer cantidad;
	private Float sumatoria;
	
	public RptCantidadMensual(String mes, Integer cantidad, Float sumatoria) {
		super();
		this.mes = mes;
		this.cantidad = cantidad;
		this.sumatoria = sumatoria;
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

	public Float getSumatoria() {
		return sumatoria;
	}

	public void setSumatoria(Float sumatoria) {
		this.sumatoria = sumatoria;
	}
		
}
