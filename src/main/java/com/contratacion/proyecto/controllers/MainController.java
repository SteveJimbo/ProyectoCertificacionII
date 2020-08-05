package com.contratacion.proyecto.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping(value="/login")
	public String login(@RequestParam(value="error", required=false) String error, 
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("success", "El usuario ya tiene una sesión activa.");
			return "redirect:/sistema.html";
		}		
		if(error != null) {
			model.addAttribute("error", "Usuario o contraseña incorrectas");
		}				
		return "login";
	}
}
