package com.example.controller;

import com.example.Curso;
import com.example.CursoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/por-aula")
    public ResponseEntity<List<Curso>> obtenerCursosPorAulaId(@RequestParam("aula_id") int aulaId) {
        List<Curso> cursos = cursoService.findByAulaId(aulaId);
        return ResponseEntity.ok(cursos); // Usando ResponseEntity.ok()
    }

    @GetMapping("/por-profesor")
    public ResponseEntity<List<Curso>> obtenerCursosPorProfesorId(@RequestParam("prof_id") int profesorId) {
        List<Curso> cursos = cursoService.findByListaProfesoresId(profesorId);
        return ResponseEntity.ok(cursos); // Usando ResponseEntity.ok()
    }
}