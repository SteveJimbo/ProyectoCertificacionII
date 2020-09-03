package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.Bono;
import com.contratacion.proyecto.models.services.IBonoService;

@Controller
@RequestMapping(value="/bono")
public class BonoController {

	@Autowired
	private IBonoService srvBono;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Bono bono = new Bono();

		model.addAttribute("title","Registro de un nuevo Bono");
		model.addAttribute("bono", bono);
		return "bono/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Bono bono = srvBono.findById(id);
		model.addAttribute("bono", bono);
		return "bono/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Bono bono = srvBono.findById(id);
		model.addAttribute("bono", bono);
		model.addAttribute("title","Actualizando el registro de: "+ bono.toString());
		return "bono/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvBono.delete(id);
		return "redirect:/bono/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Bono> bonos = this.srvBono.findAll();
		model.addAttribute("bonos", bonos);
		model.addAttribute("title","Listado de Bonos");
		return "bono/list";
	}
	
	@PostMapping(value="/save")
	public String save(Bono bono, Model model) {
		this.srvBono.save(bono);
		return "redirect:/bono/list";
	}
}
