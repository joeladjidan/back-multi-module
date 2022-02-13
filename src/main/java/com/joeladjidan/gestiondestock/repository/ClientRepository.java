package com.joeladjidan.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
