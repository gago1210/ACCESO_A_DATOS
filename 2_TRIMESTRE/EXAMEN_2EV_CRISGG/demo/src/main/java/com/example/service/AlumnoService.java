package com.example.service;

import com.example.model.Alumno;

import java.util.List;

public interface AlumnoService {

    void agregarAlumno(Alumno alumno);

    List<Alumno> getAllAlumnos();

    List<Alumno> findByCursoId(int idCurso);

    List<Alumno> findByNombreCurso(String nombreCurso);


}