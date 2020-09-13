package com.contratacion.proyecto.models.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
	
	@Column(name="fecha_generacion")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaGeneracion;
	
	@Column(name="mes")
	private String mes;
	
	@Column(name="anio")
	private String anio;

	
	@Column(name="total")
	private Float total;
	
	@JoinColumn(name="fk_trabajador", referencedColumnName="pk_trabajador")
	@ManyToOne
	private Trabajador trabajador;
	
	@OneToMany(mappedBy="rolDePago", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Detalle> detalles;

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
	
	public Calendar getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Calendar fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
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

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	@Override
	public String toString() {
		return "Rol De Pago : " + this.fechaGeneracion();
	}
	public String fechaGeneracion() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaGeneracion.getTime());
	}
	
	public void calcularTotal(List<Descuento> p) {
		/*this.total = this.trabajador.getCargo().getSueldo();
		this.total -= total*0.0945f;
		if(this.trabajador.getSanciones().size()>0) {
			for(Sancion s : this.trabajador.getSanciones()) {
				for(Descuento pe : p) {
					if(s.getPenalidad().getIdpenalidad() == pe.getIdpenalidad()) {
						this.total -= pe.getMonto();
					}
				}
			}
		}
		this.fechaGeneracion = Calendar.getInstance();	*/
	}
}
