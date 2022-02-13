package com.joeladjidan.gestiondestock.controller.api;

import io.swagger.annotations.Api;

import static com.joeladjidan.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.joeladjidan.gestiondestock.dto.EntrepriseDto;

@Api("entreprises")
@CrossOrigin(origins = "http://localhost:4200")
public interface EntrepriseApi {

  @PostMapping(ENTREPRISE_ENDPOINT + "/create")
  EntrepriseDto save(@RequestBody EntrepriseDto dto);

  @GetMapping(ENTREPRISE_ENDPOINT + "/{idEntreprise}")
  EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

  @GetMapping(ENTREPRISE_ENDPOINT + "/all")
  List<EntrepriseDto> findAll();

  @DeleteMapping(ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
  void delete(@PathVariable("idEntreprise") Integer id);

}
