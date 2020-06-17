package com.contratacion.proyecto.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="penalidades")
public class Penalidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_penalidad")
	private Integer idpenalidad;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="monto")
	private Float monto;
	
	@OneToMany(mappedBy="penalidad", fetch=FetchType.LAZY)
	private List<Sancion> sanciones;

	public Penalidad() {
		super();
	}
	
	public Penalidad(Integer id) {
		super();
		this.idpenalidad = id;
	}

	public Integer getIdpenalidad() {
		return idpenalidad;
	}

	public void setIdpenalidad(Integer idpenalidad) {
		this.idpenalidad = idpenalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public List<Sancion> getSanciones() {
		return sanciones;
	}

	public void setSanciones(List<Sancion> sanciones) {
		this.sanciones = sanciones;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}
	
	
}
