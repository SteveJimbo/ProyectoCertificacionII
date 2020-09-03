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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="descuentos")
public class Descuento implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_descuento")
	private Integer iddescuento;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="monto")
	private Float monto;
	
	@JoinColumn(name="fk_detalle", referencedColumnName="pk_detalle")
	@ManyToOne
	private Detalle detalle;
	


	public Descuento() {
		super();
	}
	
	public Descuento(Integer id) {
		super();
		this.iddescuento = id;
	}

	
	

	public Integer getIddescuento() {
		return iddescuento;
	}

	public void setIddescuento(Integer iddescuento) {
		this.iddescuento = iddescuento;
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
	
	

	
	public Detalle getDetalle() {
		return detalle;
	}

	public void setDetalle(Detalle detalle) {
		this.detalle = detalle;
	}

	@Override
	public String toString() {
		return ""+nombre+" - "+descripcion;
	}
	
	
}
