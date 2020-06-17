package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.entities.Cargo;

@Controller
@RequestMapping(value="/cargo")
public class CargoController {

	@Autowired
	private ICargoService srvCargo;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Cargo cargo = new Cargo();

		model.addAttribute("title","Registro de un nuevo Cargo");
		model.addAttribute("cargo", cargo);
		return "cargo/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Cargo cargo= srvCargo.findById(id);//consulta
		model.addAttribute("cargo", cargo);
		return "cargo/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Cargo cargo = srvCargo.findById(id);
		model.addAttribute("cargo", cargo);
		//el metodo toString se ejecuta por default
		model.addAttribute("title","Actualizando el registro de: "+ cargo.toString());
		return "cargo/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvCargo.delete(id);
		//despues de borrar se hace un redirect a una accion por invocar
		return "redirect:/cargo/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Cargo> cargo = this.srvCargo.findAll();
		
		model.addAttribute("cargos", cargo);
		
		model.addAttribute("title","Listado de cargos");
		return "cargo/list";
	}
	
	@PostMapping(value="/save")
	public String save(Cargo cargo, Model model) {
		this.srvCargo.save(cargo);
		return "redirect:/cargo/list";
	}
	
	
	
	
	
}
