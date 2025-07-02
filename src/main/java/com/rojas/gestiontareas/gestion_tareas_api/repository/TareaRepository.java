package com.rojas.gestiontareas.gestion_tareas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rojas.gestiontareas.gestion_tareas_api.entity.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

}