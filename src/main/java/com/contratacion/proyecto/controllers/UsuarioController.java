package com.contratacion.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contratacion.proyecto.models.services.UsuarioService;
import com.contratacion.proyecto.models.entities.Rol;
import com.contratacion.proyecto.models.entities.Usuario;

@Controller
@RequestMapping(value="/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping(value="/create")
	public String registro(Model model) {	
		Usuario usuario = new Usuario();
		String nombrerol = "";
		model.addAttribute("usuario", usuario);
		model.addAttribute("nombrerol", nombrerol);
		model.addAttribute("title", "Registro de nuevo usuario");				
		return "usuario/form";
	}
	
	@PostMapping(value="/save")
	public String save(@Validated Usuario usuario, BindingResult result, @RequestParam(value = "rol") String rol, Model model, RedirectAttributes flash) {
		int i = 0;
		
		try {
			String role = "ROLE_" + rol; 
			if(result.hasErrors())
			{	
				model.addAttribute("title", "Registro de nuevo usuario");
				model.addAttribute("usuario", usuario);
				return "usuario/form";
			}			
			String pass = usuario.getPassword();
			usuario.setPassword(encoder.encode(pass));			
			usuario.getRoles().add(new Rol(role));
			usuario.setHabilitado(true);
			service.save(usuario);
			flash.addFlashAttribute("success", "El usuario fue agregado con éxito.");
		}
		catch(Exception ex) {
			flash.addFlashAttribute("error", "El usuario no pudo ser agregado.");
		}
		return "redirect:/sistema.html";		
	} 
	
}
