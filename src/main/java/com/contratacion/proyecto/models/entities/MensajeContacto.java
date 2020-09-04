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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="mensajes")
public class MensajeContacto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name="pk_mensaje")
	private Integer idmensajeContacto;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telefono")
	private String telefono;
	
	@Column(name="mensaje")
	private String mensaje;
	
	@Column(name="leido")
	private boolean leido;
	
	@Column(name="fecha_recibido")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar fechaRecibido;
	
	public MensajeContacto() {
		super();
	}
	
	public MensajeContacto(Integer idmensajeContacto) {
		super();
		this.idmensajeContacto = idmensajeContacto;
	}

	public Integer getIdmensajeContacto() {
		return idmensajeContacto;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getMensaje() {
		return mensaje;
	}

	public boolean getLeido() {
		return leido;
	}

	public void setIdmensajeContacto(Integer idmensajeContacto) {
		this.idmensajeContacto = idmensajeContacto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setLeido(boolean leido) {
		this.leido = leido;
	}
	
	public Calendar getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Calendar fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
	}

	public String fechaRecibido() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");		
		return sdf.format(fechaRecibido.getTime());
	}
}
