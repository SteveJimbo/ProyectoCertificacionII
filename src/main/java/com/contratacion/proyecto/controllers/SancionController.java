package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.Sancion;
import com.contratacion.proyecto.models.services.ISancionService;

@Controller
@RequestMapping(value="/sancion")
public class SancionController {

	@Autowired
	private ISancionService srvSancion;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Sancion sancion = new Sancion();

		model.addAttribute("title","Registro de una nueva Sanción");
		model.addAttribute("sancion", sancion);
		return "sancion/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Sancion sancion = srvSancion.findById(id);
		model.addAttribute("sancion", sancion);
		return "sancion/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Sancion sancion = srvSancion.findById(id);
		model.addAttribute("sancion", sancion);
		//el metodo toString se ejecuta por default
		model.addAttribute("title","Actualizando el registro de: "+ sancion.toString());
		return "sancion/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvSancion.delete(id);
		//despues de borrar se hace un redirect a una accion por invocar
		return "redirect:/sancion/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Sancion> sanciones = this.srvSancion.findAll();
		
		model.addAttribute("sanciones", sanciones);
		
		model.addAttribute("title","Listado de Sanciones");
		return "sancion/list";
	}
	
	@PostMapping(value="/save")
	public String save(Sancion sancion, Model model) {
		this.srvSancion.save(sancion);
		return "redirect:/sancion/list";
	}
}
