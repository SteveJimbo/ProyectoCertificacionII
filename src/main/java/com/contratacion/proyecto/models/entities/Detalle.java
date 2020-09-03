package com.contratacion.proyecto.models.entities;

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
public class Detalle {
	
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
	
	
	
	
	
	
	
}
