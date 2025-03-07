package com.example.controller;

import com.example.examen_ad.model.Alumno;
import com.example.examen_ad.service.AlumnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    @Autowired
    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Alumno> addAlumno(@RequestBody Alumno alumno) {
        Alumno addedAlumno = alumnoService.agregarAlumno(alumno);
        return ResponseEntity.ok(addedAlumno);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.getAllAlumnos();
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/findAllAlumnosByCurso")
    public ResponseEntity<List<Alumno>> getAlumnosByCurso(@RequestParam int idCurso) {
        List<Alumno> alumnos = alumnoService.findByCursoId(idCurso);
        return ResponseEntity.ok(alumnos);
    }

    @GetMapping("/findAllAlumnosByNombreCurso")
    public ResponseEntity<List<Alumno>> getAlumnosByNombreCurso(@RequestParam String nombreCurso) {
        List<Alumno> alumnos = alumnoService.findByNombreCurso(nombreCurso);
        return ResponseEntity.ok(alumnos);
    }
}