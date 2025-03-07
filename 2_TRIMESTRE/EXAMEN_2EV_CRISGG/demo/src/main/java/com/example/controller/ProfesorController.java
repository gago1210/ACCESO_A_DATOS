package com.example.controller;

import com.example.Profesor;
import com.example.ProfesorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProfesor(@RequestBody Profesor profesor) {
        profesorService.agregarProf(profesor);
        String mensaje = "Docente " + profesor.getNombre() + " " + profesor.getApellido() + " añadido correctamente.";
        return ResponseEntity.ok(mensaje); // Usando ResponseEntity.ok()
    }

    @GetMapping("/por-curso")
    public ResponseEntity<List<Profesor>> obtenerProfesoresPorCurso(@RequestParam("idCurso") int idCurso) {
        List<Profesor> profesores = profesorService.findByListaCursosId(idCurso);
        return ResponseEntity.ok(profesores); // Usando ResponseEntity.ok()
    }
}