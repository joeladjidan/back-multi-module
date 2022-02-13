package com.joeladjidan.gestiondestock.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

  Optional<Ventes> findVentesByCode(String code);
}
