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
import com.contratacion.proyecto.models.entities.Descuento;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.services.IBonoService;
import com.contratacion.proyecto.models.services.IDescuentoService;
import com.contratacion.proyecto.models.services.IDetalleService;

@Controller
@RequestMapping(value="/detalle")
public class DetalleController {

	@Autowired
	private IDetalleService srvDetalle;
	
	@Autowired
	private IDescuentoService srvDescuentos;
	
	@Autowired
	private IBonoService srvBonos;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Detalle detalle = new Detalle();
		List<Descuento> descuentos = srvDescuentos.findAll();
		List<Bono> bonos = srvBonos.findAll();
		model.addAttribute("title","Registro de un nuevo detalle");
		model.addAttribute("detalle", detalle);
		model.addAttribute("descuentos", descuentos);
		model.addAttribute("bonos", bonos);
		return "detalle/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Detalle detalle = srvDetalle.findById(id);
		model.addAttribute("detalle", detalle);
		return "detalle/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Detalle detalle = srvDetalle.findById(id);
		model.addAttribute("detalle", detalle);
		model.addAttribute("title","Actualizando el registro de: "+ detalle.toString());
		return "detalle/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvDetalle.delete(id);
		return "redirect:/detalle/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Detalle> detalles = this.srvDetalle.findAll();
		model.addAttribute("detalles", detalles);
		model.addAttribute("title","Listado de detalles");
		return "detalle/list";
	}
	
	@PostMapping(value="/save")
	public String save(Detalle detalle, Model model) {
		this.srvDetalle.save(detalle);
		return "redirect:/detalle/list";
	}
}
