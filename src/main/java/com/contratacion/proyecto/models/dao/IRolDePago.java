package com.contratacion.proyecto.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.contratacion.proyecto.models.entities.RolDePago;

public interface IRolDePago extends CrudRepository<RolDePago, Integer>{
	
	@Query("SELECT M FROM RolDePago M WHERE M.trabajador.idtrabajador = :id")	
	public List<RolDePago> findByTrabajador(Integer id);
	
	/*@Query("SELECT A.nombre, SUM(R.total) FROM RolDePago AS R INNER JOIN Area AS A JOIN Trabajador AS T WHERE R.mes = :mes AND R.anio = :anio AND T.idtrabajador = R.trabajador.idtrabajador AND T.area.idarea = A.idarea GROUP BY A.nombre")
	public List<RptMontoArea> findByYearMonth(String mes, String anio);*/
	
	@Query("SELECT M FROM RolDePago M WHERE M.mes = :mes")	
	public List<RolDePago> findByMes(String mes);
	
}
