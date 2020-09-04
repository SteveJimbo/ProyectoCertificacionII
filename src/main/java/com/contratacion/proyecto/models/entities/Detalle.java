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
@Table(name="detalles")
public class Detalle implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_detalle")
	private Integer iddetalle;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="monto")
	private Float monto;
	
	@OneToMany(mappedBy="detalle", fetch=FetchType.LAZY)
	private List<Descuento> descuentos;
	
	@OneToMany(mappedBy="detalle", fetch=FetchType.LAZY)
	private List<Bono> bonos;
	
	@JoinColumn(name="fk_rol", referencedColumnName="pk_rol")
	@ManyToOne
	private RolDePago rolDePago;
	
	public Detalle() {
		super();
	}
	
	public Detalle(Integer iddetalle) {
		super();
		this.iddetalle = iddetalle;
	}

	public Integer getIddetalle() {
		return iddetalle;
	}

	public String getNombre() {
		return nombre;
	}

	public Float getMonto() {
		return monto;
	}

	public List<Descuento> getDescuentos() {
		return descuentos;
	}

	public List<Bono> getBonos() {
		return bonos;
	}

	public RolDePago getRolDePago() {
		return rolDePago;
	}

	public void setIddetalle(Integer iddetalle) {
		this.iddetalle = iddetalle;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public void setDescuentos(List<Descuento> descuentos) {
		this.descuentos = descuentos;
	}

	public void setBonos(List<Bono> bonos) {
		this.bonos = bonos;
	}

	public void setRolDePago(RolDePago rolDePago) {
		this.rolDePago = rolDePago;
	}
	
}
