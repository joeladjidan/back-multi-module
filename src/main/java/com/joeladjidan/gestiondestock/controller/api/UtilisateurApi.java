package com.joeladjidan.gestiondestock.controller.api;


import io.swagger.annotations.Api;

import static com.joeladjidan.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.joeladjidan.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.joeladjidan.gestiondestock.dto.UtilisateurDto;

@Api("utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")
public interface UtilisateurApi {

  @PostMapping(UTILISATEUR_ENDPOINT + "/create")
  UtilisateurDto save(@RequestBody UtilisateurDto dto);

  @PostMapping(UTILISATEUR_ENDPOINT + "/update/password")
  UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

  @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
  UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

  @GetMapping(UTILISATEUR_ENDPOINT + "/find/{email}")
  UtilisateurDto findByEmail(@PathVariable("email") String email);

  @GetMapping(UTILISATEUR_ENDPOINT + "/all")
  List<UtilisateurDto> findAll();

  @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
  void delete(@PathVariable("idUtilisateur") Integer id);

}
