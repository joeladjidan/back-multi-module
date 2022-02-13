package com.joeladjidan.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}
