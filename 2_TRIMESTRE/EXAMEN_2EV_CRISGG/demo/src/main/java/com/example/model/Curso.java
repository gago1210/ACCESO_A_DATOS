package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private int id;

    @Column(nullable = false)
    private String nombre;

    @OneToOne
    @JoinColumn(name = "id_aula", nullable = false)
    @JsonIgnore
    private Aula aula;

    @OneToMany(mappedBy = "curso", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("curso")
    private List<Alumno> listaAlumnos;

    @ManyToMany(mappedBy = "listaCursos", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Profesor> listaProfesores;
}