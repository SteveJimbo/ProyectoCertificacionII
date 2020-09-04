package com.contratacion.proyecto.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.MensajeContacto;
import com.contratacion.proyecto.models.services.IMensajeContactoService;

@Controller
@RequestMapping(value="/mensajes")
public class MensajesController {
	
	@Autowired
	private IMensajeContactoService srvMensajes;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		MensajeContacto mensaje = new MensajeContacto();
		model.addAttribute("mensaje", mensaje);		
		return "mensajeContacto/form";
	}
	
	@PostMapping(value = "/save")
	public String save(@RequestBody MensajeContacto mensaje, Model model) {				
		try {
			mensaje.setLeido(false);
			mensaje.setFechaRecibido(Calendar.getInstance());
			this.srvMensajes.save(mensaje);
			return "mensajeContacto/exito";
		} catch (Exception ex) {
			return "mensajeContacto/exito";
		}		
	}
}
