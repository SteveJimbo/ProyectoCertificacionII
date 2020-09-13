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

import com.contratacion.proyecto.models.entities.Area;
import com.contratacion.proyecto.models.entities.Cargo;
import com.contratacion.proyecto.models.entities.Descuento;
import com.contratacion.proyecto.models.entities.Detalle;
import com.contratacion.proyecto.models.entities.RolDePago;
import com.contratacion.proyecto.models.entities.Trabajador;
import com.contratacion.proyecto.models.reporting.RptAreaSanciones;
import com.contratacion.proyecto.models.reporting.RptCantidadMensual;
import com.contratacion.proyecto.models.reporting.RptMontoArea;
import com.contratacion.proyecto.models.services.DetalleService;
import com.contratacion.proyecto.models.services.IAreaService;
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
	
	@Autowired
	private IAreaService srvAreas;
	
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
		if(rolDePago.getTrabajador().getIdtrabajador() == 0) {
			RolDePago rdp = new RolDePago();
			rolDePago.setDetalles(new ArrayList<Detalle>());
			List<Trabajador> trabajadores = srvTrabajador.findAll();
			model.addAttribute("title","Registro de un nuevo Rol De Pago");
			model.addAttribute("rolDePago", rdp);
			model.addAttribute("trabajadores", trabajadores);
			flash.addFlashAttribute("error", "Trabajador No Valido");
			return "redirect:/roldepago/create";
		}
		if(rolDePago.getMes().equals("0")) {
			Calendar c1 = Calendar.getInstance();
			int mes = c1.get(Calendar.MONTH)+1;
			String mese = "";
			switch(mes){
				case 1:
					mese="Enero";
					break;
				case 2:
					mese="Febrero";
					break;
				case 3:
					mese="Marzo";
					break;
				case 4:
					mese="Abril";
					break;
				case 5:
					mese="Mayo";
					break;
				case 6:
					mese="Junio";
					break;
				case 7:
					mese="Julio";
					break;
				case 8:
					mese="Agosto";
					break;
				case 9:
					mese="Septiembre";
					break;
				case 10:
					mese="Octubre";
					break;
				case 11:
					mese="Noviembre";
					break;
				case 12:
					mese="Diciembre";
					break;
			};
			rolDePago.setMes(mese);
		}
		if(rolDePago.getAnio().equals("0")) {
			rolDePago.setAnio(""+Calendar.getInstance().get(Calendar.YEAR));
		}
		if(srvRolDePago.validarRol(rolDePago)) {	
			rolDePago.setFechaGeneracion(Calendar.getInstance());
			RolDePago rol = (RolDePago) session.getAttribute("rolDePago");
			rolDePago.setDetalles(rol.getDetalles());
			srvRolDePago.save(rolDePago);
			status.setComplete();
			flash.addFlashAttribute("success", "Rol Generado Exitosamente");
			return "redirect:/roldepago/retrieve/"+srvRolDePago.findLast();
		}else {
			flash.addFlashAttribute("error", "Rol Existente - Borrelo antes de Generarlo Nuevamente");
			return "redirect:/roldepago/list";
		}
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
				float number = rol.getTrabajador().getCargo().getSueldo()*m;
				number = Math.round(number * 100);
				number = number/100;
				detalle.setMonto(-(number));
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
					float number = t.getCargo().getSueldo()*0.0945f;
					number = Math.round(number * 100);
					number = number/100;
					d.setMonto(-(number));
					pv = false;
				}
				if(srvDescuento.findNombre(d.getNombre()) != null) {
					Descuento des = srvDescuento.findNombre(d.getNombre()) ;
					float m = des.getMonto()/100;
					float number = t.getCargo().getSueldo()*m;
					number = Math.round(number * 100);
					number = number/100;
					d.setMonto(-(number));
				}
			}
			if(pv) {
				Detalle det = new Detalle();
				det.setNombre("Sueldo");
				det.setMonto(t.getCargo().getSueldo());
				Detalle det2 = new Detalle();
				det2.setNombre("IESS");
				float number = t.getCargo().getSueldo()*0.0945f;
				number = Math.round(number * 100);
				number = number/100;
				det2.setMonto(-(number));
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
	
	@GetMapping(value = "/dataRptMontoArea/{id}/{id2}/{id3}", produces="application/json")
	public @ResponseBody List<RptMontoArea> dataRptMontoArea(@PathVariable(value="id") Integer id, @PathVariable(value="id2") Integer id2, @PathVariable(value="id3") Integer id3, Model model) {				
		try {
			String ano = ""+id;
			return this.srvRolDePago.rptMontoArea(id2 , ano, id3);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	@GetMapping(value = "/rptMontoArea")
	public String rptMatriculasUsuario(Model model) {
		List<Area> areas = srvAreas.findAll();
		model.addAttribute("title","Reporte de la Sumatoria en un Área");
		model.addAttribute("areas",areas);
		return "roldepago/rptMontoArea";				
	}
	
	@GetMapping(value = "/dataRptCantidadMensual/{id}", produces="application/json")
	public @ResponseBody List<RptCantidadMensual> dataRptCantidadMensual(@PathVariable(value="id") Integer id, Model model) {				
		try {
			String anio = ""+id;
			return srvRolDePago.rptCantidadMensual(anio);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
	@GetMapping(value = "/rptCantidadMensual")
	public String rptCanttidadMensual(Model model) {
		model.addAttribute("title","Reporte de la Cantidad de Roles de Pago por Mes");
		return "roldepago/rptCantidadMensual";				
	}
	
	@GetMapping(value = "/dataRptAreaSanciones/{id}/{id2}", produces="application/json")
	public @ResponseBody List<RptAreaSanciones> dataRptAreaSanciones(@PathVariable(value="id") Integer id,@PathVariable(value="id2") Integer id2 , Model model) {				
		try {
			String anio = ""+id;
			return srvRolDePago.rptAreaSanciones(id2, anio);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}		
	}
	
	@GetMapping(value = "/rptAreaSanciones")
	public String rptAreaSanciones(Model model) {
		model.addAttribute("title","Reporte de Descuentos por Área y Mes");
		return "roldepago/rptAreaSanciones";				
	}
	
}
