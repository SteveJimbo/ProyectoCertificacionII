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
@Table(name="trabajadores")
public class Trabajador implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_trabajador")
	private Integer idtrabajador;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellidos")
	private String apellidos;
	
	@Column(name="cedula")
	private String cedula;
	
	@Column(name="nacionalidad")
	private String nacionalidad;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fecha_nacimiento")
	private Calendar fechaNacimiento;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="email")
	private String email;
	
	@Column(name="estado_civil")
	private String estadoCivil;
	
	@JoinColumn(name="fk_cargo", referencedColumnName="pk_cargo")
	@ManyToOne
	private Cargo cargo;
	
	@JoinColumn(name="fk_area", referencedColumnName="pk_area")
	@ManyToOne
	private Area area;
	
	@OneToMany(mappedBy="trabajador", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<RolDePago> roles;

	public Trabajador() {
		super();
	}
	
	public Trabajador(Integer id) {
		super();
		this.idtrabajador = id;
	}

	public Integer getIdtrabajador() {
		return idtrabajador;
	}

	public void setIdtrabajador(Integer idtrabajador) {
		this.idtrabajador = idtrabajador;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}



	public List<RolDePago> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDePago> roles) {
		this.roles = roles;
	}
	
	
	

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return this.idtrabajador + " - " +  this.getNombres()+ " " + this.getApellidos();
	}
	public String fechaNacimiento() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaNacimiento.getTime());
	}
	
	
	
	
}
