package com.contratacion.proyecto.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="sanciones")
public class Sancion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_sancion")
	private Integer idsancion;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha")
	private Calendar fecha;
	
	@JoinColumn(name="fk_penalidad", referencedColumnName="pk_penalidad")
	@ManyToOne
	private Penalidad penalidad;
	
	@JoinColumn(name="fk_trabajador", referencedColumnName="pk_trabajador")
	@ManyToOne
	private Trabajador trabajador;

	public Sancion() {
		super();
	}
	
	public Sancion(Integer id) {
		super();
		this.idsancion = id;
	}

	public Integer getIdsancion() {
		return idsancion;
	}

	public void setIdsancion(Integer idsancion) {
		this.idsancion = idsancion;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Penalidad getPenalidad() {
		return penalidad;
	}

	public void setPenalidad(Penalidad penalidad) {
		this.penalidad = penalidad;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	@Override
	public String toString() {
		return "Sanción: "+this.fecha();
	}
	public String fecha() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fecha.getTime());
	}
	
	
}
