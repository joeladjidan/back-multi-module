package com.joeladjidan.gestiondestock.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.CommandeClient;
import com.joeladjidan.gestiondestock.model.CommandeFournisseur;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<CommandeClient> findAllByFournisseurId(Integer id);
}
