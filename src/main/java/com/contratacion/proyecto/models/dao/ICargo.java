package com.contratacion.proyecto.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.contratacion.proyecto.models.entities.Cargo;

public interface ICargo extends CrudRepository<Cargo, Integer>{

}
