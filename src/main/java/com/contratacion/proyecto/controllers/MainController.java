package com.contratacion.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class MainController {

	@GetMapping(value= {"/","/index.html"})
	public String index(Model model) {
		model.addAttribute("title","Página Principal");
		return "inicio";
	}
	
	@GetMapping(value= {"/sistema.html"})
	public String sistema(Model model) {
		model.addAttribute("title","Página Principal");
		return "layout";
	}
}
