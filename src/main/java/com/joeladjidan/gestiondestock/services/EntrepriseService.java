package com.joeladjidan.gestiondestock.services;

import java.util.List;

import com.joeladjidan.gestiondestock.dto.EntrepriseDto;

public interface EntrepriseService {

  EntrepriseDto save(EntrepriseDto dto);

  EntrepriseDto findById(Integer id);

  List<EntrepriseDto> findAll();

  void delete(Integer id);

}
