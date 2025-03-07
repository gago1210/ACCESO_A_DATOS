package com.example.repository;


import com.example.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
    @Query("SELECT a FROM Aula a WHERE a.capacidad > 30")
    List<Aula> findAulasPara31Alumnos();

}