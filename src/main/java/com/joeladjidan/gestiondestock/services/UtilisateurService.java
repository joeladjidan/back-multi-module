package com.joeladjidan.gestiondestock.services;

import java.util.List;

import com.joeladjidan.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.joeladjidan.gestiondestock.dto.UtilisateurDto;

public interface UtilisateurService {

  UtilisateurDto save(UtilisateurDto dto);

  UtilisateurDto findById(Integer id);

  List<UtilisateurDto> findAll();

  void delete(Integer id);

  UtilisateurDto findByEmail(String email);

  UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);


}
