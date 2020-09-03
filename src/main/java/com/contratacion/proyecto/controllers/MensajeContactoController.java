package com.contratacion.proyecto.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.MensajeContacto;
import com.contratacion.proyecto.models.services.IMensajeContactoService;

@Controller
@RequestMapping(value="/mensajeContacto")
public class MensajeContactoController {
	
	@Autowired
	private IMensajeContactoService srvMensajes;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		MensajeContacto mensaje = new MensajeContacto();
		model.addAttribute("mensaje", mensaje);		
		return "mensajeContacto/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		MensajeContacto mensaje = srvMensajes.findById(id);
		model.addAttribute("mensaje", mensaje);
		return "mensajeContacto/card";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvMensajes.delete(id);
		return "redirect:/mensajeContacto/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<MensajeContacto> mensajes = this.srvMensajes.findAll();
		model.addAttribute("Mensajes", mensajes);
		model.addAttribute("title","Listado de Mensajes");
		return "mensajeContacto/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@RequestBody MensajeContacto mensaje, Model model) {				
		try {
			mensaje.setLeido(false);
			this.srvMensajes.save(mensaje);
			return "mensajeContacto/exito";
		} catch (Exception ex) {
			return "mensajeContacto/exito";
		}		
	}
}
