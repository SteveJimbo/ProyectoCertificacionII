package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.entities.Cargo;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.services.ITrabajadorService;

@Controller
@RequestMapping(value="/trabajador")
public class TrabajadorController {

	@Autowired
	private ITrabajadorService srvTrabajador;
	
	@Autowired
	private ICargoService srvCargo;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Trabajador trabajador = new Trabajador();
		List<Cargo> cargos = srvCargo.findAll();
		model.addAttribute("title","Registro de un nuevo Trabajador");
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("cargos", cargos);
		return "trabajador/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Trabajador trabajador = srvTrabajador.findById(id);
		List<Cargo> cargos = srvCargo.findAll();
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("cargos", cargos);
		return "trabajador/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Trabajador trabajador = srvTrabajador.findById(id);
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("title","Actualizando el registro de: "+ trabajador.toString());
		return "trabajador/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvTrabajador.delete(id);
		return "redirect:/trabajador/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Trabajador> trabajadores = this.srvTrabajador.findAll();
		
		model.addAttribute("trabajadores", trabajadores);
		
		model.addAttribute("title","Listado de Trabajadores");
		return "trabajador/list";
	}
	
	@PostMapping(value="/save")
	public String save(Trabajador trabajador, Model model) {
		this.srvTrabajador.save(trabajador);
		return "redirect:/trabajador/list";
	}
}
