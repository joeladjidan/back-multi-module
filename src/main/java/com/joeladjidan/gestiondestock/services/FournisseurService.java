package com.joeladjidan.gestiondestock.services;

import java.util.List;

import com.joeladjidan.gestiondestock.dto.FournisseurDto;

public interface FournisseurService {

  FournisseurDto save(FournisseurDto dto);

  FournisseurDto findById(Integer id);

  List<FournisseurDto> findAll();

  void delete(Integer id);

}
