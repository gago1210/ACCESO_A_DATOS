package com.examen_ad.controller.service;

import com.example.model.Aula;

import java.util.List;

public interface AulaService {
    List<Aula> findAulaPara31Alumnos();

    List<Aula> findByCapacidadGreaterThan(int capacidad);

}
