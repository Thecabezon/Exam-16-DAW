package com.rojas.gestiontareas.gestion_tareas_api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rojas.gestiontareas.gestion_tareas_api.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}