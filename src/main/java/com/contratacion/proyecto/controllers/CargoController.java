package com.contratacion.proyecto.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.contratacion.proyecto.models.services.IAreaService;
import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.entities.Area;
import com.contratacion.proyecto.models.entities.Cargo;

@Controller
@RequestMapping(value="/cargo")
public class CargoController {

	@Autowired
	private ICargoService srvCargo;
	
	@Autowired
	private IAreaService srvArea;
	
	@GetMapping(value="/create/{id}")
	public String create(@PathVariable(value="id") Integer id, Model model) {
		Cargo cargo = new Cargo();
		cargo.setAreaid(id);
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
		cargo.setAreaid(cargo.getArea().getIdarea());
		System.out.println(cargo.getAreaid());
		model.addAttribute("cargo", cargo);
		//el metodo toString se ejecuta por default
		return "cargo/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		Cargo cargo = this.srvCargo.findById(id);
		int idArea = cargo.getAreaid();
		this.srvCargo.delete(id);
		
		//despues de borrar se hace un redirect a una accion por invocar
		return "redirect:/cargo/list/"+idArea;
	}
	
	@GetMapping(value="/list/{id}")
	public String list(@PathVariable(value="id") Integer id, Model model) {
		List<Cargo> cargos = this.srvCargo.findByArea(id);
		System.out.println(cargos);
		model.addAttribute("cargos", cargos);
		return "cargo/list";
	}
	
	@PostMapping(value="/save")
	public String save(@RequestBody @Valid Cargo cargo, BindingResult result, Model model) {
		try {
			Area area = this.srvArea.findById(cargo.getAreaid());
			cargo.setArea(area);
			this.srvCargo.save(cargo);			
			return "cargo/list";
		} catch (Exception ex) {	
			
			return "cargo/form";
		}	
	
	}
	
	@PostMapping(value="/saveSimple")
	public String saveSimple(@RequestBody @Valid Cargo cargo, BindingResult result, Model model) {
		try {
			this.srvCargo.save(cargo);			
			return "cargo/list";
		} catch (Exception ex) {	
			
			return "cargo/form";
		}
	}
	@GetMapping(value="/search/{id}")
	public @ResponseBody List<Cargo> search(@PathVariable(value="id") Integer id){
		System.out.println("Método");
		
		List<Cargo> list = srvCargo.findByArea(id);
		
		return list;
	}
	
	
	
	
	
}
