package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.Penalidad;
import com.contratacion.proyecto.models.services.IPenalidadService;

@Controller
@RequestMapping(value="/penalidad")
public class PenalidadController {

	@Autowired
	private IPenalidadService srvPenalidad;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Penalidad penalidad = new Penalidad();

		model.addAttribute("title","Registro de una nueva Penalidad");
		model.addAttribute("penalidad", penalidad);
		return "penalidad/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Penalidad penalidad = srvPenalidad.findById(id);
		model.addAttribute("penalidad", penalidad);
		return "penalidad/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Penalidad penalidad = srvPenalidad.findById(id);
		model.addAttribute("penalidad", penalidad);
		//el metodo toString se ejecuta por default
		model.addAttribute("title","Actualizando el registro de: "+ penalidad.toString());
		return "penalidad/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvPenalidad.delete(id);
		//despues de borrar se hace un redirect a una accion por invocar
		return "redirect:/penalidad/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Penalidad> penalidades = this.srvPenalidad.findAll();
		
		model.addAttribute("penalidades", penalidades);
		
		model.addAttribute("title","Listado de Penalidades");
		return "penalidad/list";
	}
	
	@PostMapping(value="/save")
	public String save(Penalidad penalidad, Model model) {
		this.srvPenalidad.save(penalidad);
		return "redirect:/penalidad/list";
	}
}
