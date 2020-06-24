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
import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.services.IPenalidadService;
import com.contratacion.proyecto.models.services.IRolDePagoService;
import com.contratacion.proyecto.models.services.ISancionService;
import com.contratacion.proyecto.models.services.ITrabajadorService;

@Controller
@RequestMapping(value="/roldepago")
public class RolDePagoController {
	@Autowired
	private IRolDePagoService srvRolDePago;
	
	@Autowired
	private ITrabajadorService srvTrabajador;
	
	@Autowired
	private ISancionService srvSancion;
	
	@Autowired
	private IPenalidadService srvPenalidad;
	
	@Autowired
	private ICargoService srvCargo;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		RolDePago rolDePago= new RolDePago();
		List<Trabajador> trabajadores = srvTrabajador.findAll();
		model.addAttribute("title","Registro de un nuevo Rol De Pago");
		model.addAttribute("rolDePago", rolDePago);
		model.addAttribute("trabajadores", trabajadores);
		return "roldepago/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		RolDePago rolDePago = srvRolDePago.findById(id);
		Trabajador trabajador = srvTrabajador.findById(rolDePago.getTrabajador().getIdtrabajador());
		model.addAttribute("rolDePago", rolDePago);
		model.addAttribute("trabajador", trabajador);
		return "roldepago/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		RolDePago rolDePago = srvRolDePago.findById(id);
		model.addAttribute("rolDePago", rolDePago);
		//el metodo toString se ejecuta por default
		model.addAttribute("title","Actualizando el registro de: "+ rolDePago.toString());
		return "roldepago/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvRolDePago.delete(id);
		//despues de borrar se hace un redirect a una accion por invocar
		return "redirect:/roldepago/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<RolDePago> rolesDePago = this.srvRolDePago.findAll();
		model.addAttribute("rolesDePago", rolesDePago);
		model.addAttribute("title","Listado de Roles De Pago");
		return "roldepago/list";
	}
	
	@PostMapping(value="/save")
	public String save(RolDePago rolDePago, Model model) {
		Trabajador t = srvTrabajador.findById(rolDePago.getTrabajador().getIdtrabajador());
		rolDePago.getTrabajador().setSanciones(srvSancion.findAll(t.getIdtrabajador()));		
		rolDePago.getTrabajador().setCargo(srvCargo.findById(t.getCargo().getIdcargo()));
		List<Penalidad> p = srvPenalidad.findAll();
		rolDePago.calcularTotal(p);
		this.srvRolDePago.save(rolDePago);
		Integer id = srvRolDePago.findLast();
		return "redirect:/roldepago/retrieve/"+id;
	}
}
