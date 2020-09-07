package com.contratacion.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contratacion.proyecto.models.entities.Descuento;
import com.contratacion.proyecto.models.services.IDescuentoService;

@Controller
@RequestMapping(value="/descuento")
public class DescuentoController {

	@Autowired
	private IDescuentoService srvDescuento;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		Descuento descuento = new Descuento();

		model.addAttribute("title","Registro de un nuevo descuento");
		model.addAttribute("descuento", descuento);
		return "descuento/form";
	}
	
	@GetMapping(value="/retrieve/{id}")
	public String retrieve(@PathVariable(value="id") Integer id, Model model) {
		Descuento descuento = srvDescuento.findById(id);
		model.addAttribute("descuento", descuento);
		return "descuento/card";
	}
	
	@GetMapping(value="/update/{id}")
	public String update(@PathVariable(value="id") Integer id, Model model) {
		Descuento descuento = srvDescuento.findById(id);
		model.addAttribute("descuento", descuento);
		model.addAttribute("title","Actualizando el registro de: "+ descuento.toString());
		return "descuento/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvDescuento.delete(id);
		return "redirect:/descuento/list";
	}
	
	@GetMapping(value="/list")
	public String list(Model model) {
		List<Descuento> descuentos = this.srvDescuento.findAll();
		model.addAttribute("descuentos", descuentos);
		model.addAttribute("title","Listado de descuentos");
		return "descuento/list";
	}
	
	@PostMapping(value="/save")
	public String save(Descuento descuento, Model model) {
		this.srvDescuento.save(descuento);
		return "redirect:/descuento/list";
	}
	
	@GetMapping(value = "/listaDescuentos", produces="application/json")
	public @ResponseBody List<Descuento> listaDescuentos(Model model) {				
		try {			
			return this.srvDescuento.findAll();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
}
