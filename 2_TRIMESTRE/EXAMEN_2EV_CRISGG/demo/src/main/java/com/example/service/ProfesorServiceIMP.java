package com.examen_ad.controller.service;

import com.example.model.Profesor;
import com.example.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServiceImp implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImp(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    @Override
    public void agregarProf(Profesor profesor) {
        profesorRepository.save(profesor);
    }

    @Override
    public List<Profesor> findByListaCursosId(int idCurso) {
        return profesorRepository.findByCursoId(idCurso); 
    }
}