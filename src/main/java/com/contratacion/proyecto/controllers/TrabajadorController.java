package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contratacion.proyecto.models.entities.Area;
import com.contratacion.proyecto.models.entities.Cargo;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.services.IAreaService;
import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.services.ITrabajadorService;

@Controller
@RequestMapping(value="/trabajador")
public class TrabajadorController {

	@Autowired
	private ITrabajadorService srvTrabajador;
	
	@Autowired
	private ICargoService srvCargo;
	
	@Autowired
	private IAreaService srvArea;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Trabajador trabajador = new Trabajador();
		List<Area> areas = srvArea.findAll();
		List<Cargo> cargos = srvCargo.findAll();
		model.addAttribute("title","Registro de un nuevo Trabajador");
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("cargos", cargos);
		model.addAttribute("areas", areas);
		return "trabajador/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Trabajador trabajador = srvTrabajador.findById(id);
		model.addAttribute("trabajador", trabajador);
		return "trabajador/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Trabajador trabajador = srvTrabajador.findById(id);
		List<Cargo> cargos = srvCargo.findAll();
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("cargos", cargos);
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
	public String save(@Validated Trabajador trabajador, BindingResult result, Model model, RedirectAttributes flash) {
		
		try {
			
			String message = "Trabajador agregado correctamente";
			String titulo = "Registro de un nuevo Trabajador";
			
			if(trabajador.getIdtrabajador() != null) {
				message = "Trabajador actualizado correctamente";
				titulo = "Actualizando el registro de: "+ trabajador.toString();
			}
			
			if(result.hasErrors()) {
				model.addAttribute("title", titulo);
				flash.addFlashAttribute("error", "Error, intentelo de nuevo");
				return "trabajador/form";				
			}
			
			this.srvTrabajador.save(trabajador);
			flash.addFlashAttribute("success", message);
		}catch (Exception ex){
			flash.addFlashAttribute("error", ex.getMessage());
		}
		return "redirect:/trabajador/list";
	}
}
