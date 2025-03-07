package com.examen_ad.controller.service;

import com.example.model.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> findByAulaId(int aula_id);

    List<Curso> findByListaProfesoresId(int prof_id);
}