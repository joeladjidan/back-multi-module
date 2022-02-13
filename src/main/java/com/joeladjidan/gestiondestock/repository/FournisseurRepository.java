package com.joeladjidan.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}
