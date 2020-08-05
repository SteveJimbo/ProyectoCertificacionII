package com.contratacion.proyecto.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
@Table(name="roles_de_pago")
public class RolDePago implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_rol")
	private Integer idrol;
	
	@Column(name="fecha_impresion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaImpresion;
	
	@Column(name="total")
	private Float total;
	
	@JoinColumn(name="fk_trabajador", referencedColumnName="pk_trabajador")
	@ManyToOne
	private Trabajador trabajador;

	public RolDePago() {
		super();
	}
	
	public RolDePago(Integer id) {
		super();
		this.idrol = id;
	}

	public Integer getIdrol() {
		return idrol;
	}

	public void setIdrol(Integer idrol) {
		this.idrol = idrol;
	}

	public Calendar getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(Calendar fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	@Override
	public String toString() {
		return "Rol De Pago : " + this.fechaImpresion();
	}
	public String fechaImpresion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaImpresion.getTime());
	}
	
	public void calcularTotal(List<Penalidad> p) {
		this.total = this.trabajador.getCargo().getSueldo();
		this.total -= total*0.0945f;
		if(this.trabajador.getSanciones().size()>0) {
			for(Sancion s : this.trabajador.getSanciones()) {
				for(Penalidad pe : p) {
					if(s.getPenalidad().getIdpenalidad() == pe.getIdpenalidad()) {
						this.total -= pe.getMonto();
					}
				}
			}
		}
		this.fechaImpresion = Calendar.getInstance();	
	}
}
