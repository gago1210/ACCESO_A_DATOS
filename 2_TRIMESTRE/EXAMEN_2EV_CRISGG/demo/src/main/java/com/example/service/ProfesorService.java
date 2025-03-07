package com.examen_ad.controller.service;


import com.example.model.Profesor;

import java.util.List;

public interface ProfesorService {
    void agregarProf(Profesor profesor);

    List<Profesor> findByListaCursosId(int idCurso);

}