package com.contratacion.proyecto.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contratacion.proyecto.models.entities.Cargo;
import com.contratacion.proyecto.models.entities.Descuento;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.services.DetalleService;
import com.contratacion.proyecto.models.services.ICargoService;
import com.contratacion.proyecto.models.services.IDescuentoService;
import com.contratacion.proyecto.models.services.IRolDePagoService;
import com.contratacion.proyecto.models.services.ITrabajadorService;

@Controller
@SessionAttributes("rolDePago")
@RequestMapping(value="/roldepago")
public class RolDePagoController {
	@Autowired
	private IRolDePagoService srvRolDePago;
	
	@Autowired
	private ITrabajadorService srvTrabajador;
	
	@Autowired
	private IDescuentoService srvDescuento;
	
	@GetMapping(value="/create")
	public String create(Model model) {
		RolDePago rolDePago = new RolDePago();
		rolDePago.setDetalles(new ArrayList<Detalle>());
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
		model.addAttribute("title","Actualizando el registro de: "+ rolDePago.toString());
		return "roldepago/form";
	}
	
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Integer id, Model model) {
		this.srvRolDePago.delete(id);
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
	public String save(RolDePago rolDePago, Model model, SessionStatus status, RedirectAttributes flash, HttpSession session) {
		rolDePago.setFechaGeneracion(Calendar.getInstance());
		RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
		rolDePago.setDetalles(rol.getDetalles());
		/*List<Detalle> aux = new ArrayList<Detalle>();
		for(Detalle d : rol.getDetalles()) {
			aux.add(d);
		}
		rolDePago.getDetalles().addAll(aux);*/
		srvRolDePago.save(rolDePago);
		status.setComplete();
		flash.addFlashAttribute("success", "Rol Generado Exitosamente");
		return "redirect:/roldepago/retrieve/"+srvRolDePago.findLast();
	}
	
	
	@PostMapping(value = "/add", produces="application/json")
	public @ResponseBody Object add(@RequestBody Detalle detalle, Model model, HttpSession session) {				
		try {
			Descuento d = srvDescuento.findNombre(detalle.getNombre());
			RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
			if(d == null) {
				rol.getDetalles().add(detalle);
			}else {
				float m = d.getMonto()/100;
				detalle.setMonto(-(rol.getTrabajador().getCargo().getSueldo()*m));
				rol.getDetalles().add(detalle);
			}
			return detalle;
		} catch (Exception ex) {			
			return ex;
		}		
	}
	
	@GetMapping(value = "/detalles")
	public String detalles(Model model, HttpSession session) {
		RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
		model.addAttribute("detalles", rol.getDetalles());
		return "detalle/list";
	}
	
	@GetMapping(value="/obligaciones/{id}")
	public String obligaciones(@PathVariable(value="id") Integer id, Model model, HttpSession session) {
		try {
			boolean pv = true;
			Trabajador t = srvTrabajador.findById(id);
			t.getCargo().setTrabajadores(null);
			RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
			rol.setTrabajador(t);
			List<Detalle> detalles = rol.getDetalles();
			for(Detalle d : detalles) {
				if(d.getNombre().equals("Sueldo")) {
					d.setMonto(t.getCargo().getSueldo());
					pv = false;
				}
				if(d.getNombre().equals("IESS")) {
					d.setMonto(-(t.getCargo().getSueldo()*0.0945f));
					pv = false;
				}
				if(srvDescuento.findNombre(d.getNombre()) != null) {
					Descuento des = srvDescuento.findNombre(d.getNombre()) ;
					float m = des.getMonto()/100;
					d.setMonto(-(t.getCargo().getSueldo()*m));
				}
			}
			if(pv) {
				Detalle det = new Detalle();
				det.setNombre("Sueldo");
				det.setMonto(t.getCargo().getSueldo());
				Detalle det2 = new Detalle();
				det2.setNombre("IESS");
				det2.setMonto(-(t.getCargo().getSueldo()*0.0945f));
				rol.getDetalles().add(det);
				rol.getDetalles().add(det2);
			}
		} catch (Exception ex) {			
			return ex.toString();
		}	
		
		return "detalle/list";
	}
	
	@GetMapping(value="/total")
	public @ResponseBody Object total( Model model, HttpSession session) {
		String tot = "";
		try {
			float total = 0;
			RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
			List<Detalle> detalles = rol.getDetalles();  
			for(Detalle d : detalles) {
				total += d.getMonto();
			}
			tot = ""+total;
		} catch (Exception ex) {			
			return ex.toString();
		}	
		
		return tot;
	}
}
