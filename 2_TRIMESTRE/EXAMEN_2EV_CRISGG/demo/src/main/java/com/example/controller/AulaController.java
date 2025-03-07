package com.example.controller;

import com.example.model.Aula;
import com.example.service.AulaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService aulaService;

    @Autowired
    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @GetMapping("/mayores30")
    public ResponseEntity<List<Aula>> obtenerAulasMayores30() {
        List<Aula> aulas = aulaService.findByCapacidadGreaterThan(30);
        return ResponseEntity.ok(aulas); // Usando ResponseEntity.ok()
    }

    @GetMapping("/capacidad-mayor-30")
    public ResponseEntity<List<Aula>> obtenerAulasPorCapacidadMayor30() {
        return ResponseEntity.ok(aulaService.findByCapacidadGreaterThan(30));
    }
}