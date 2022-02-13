package com.joeladjidan.gestiondestock.controller.api;

import io.swagger.annotations.Api;

import static com.joeladjidan.gestiondestock.utils.Constants.VENTES_ENDPOINT;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.joeladjidan.gestiondestock.dto.VentesDto;

@Api("ventes")
@CrossOrigin(origins = "http://localhost:4200")
public interface VentesApi {

  @PostMapping(VENTES_ENDPOINT + "/create")
  VentesDto save(@RequestBody VentesDto dto);

  @GetMapping(VENTES_ENDPOINT + "/{idVente}")
  VentesDto findById(@PathVariable("idVente") Integer id);

  @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
  VentesDto findByCode(@PathVariable("codeVente") String code);

  @GetMapping(VENTES_ENDPOINT + "/all")
  List<VentesDto> findAll();

  @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
  void delete(@PathVariable("idVente") Integer id);

}
