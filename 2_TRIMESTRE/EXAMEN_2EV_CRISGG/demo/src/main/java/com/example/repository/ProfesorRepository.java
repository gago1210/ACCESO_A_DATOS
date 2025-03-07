package com.example.repository;

import com.example.model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {

    // Para buscar por id del curso
    @Query("SELECT p FROM Profesor p JOIN p.listaCursos c WHERE c.id = :idCurso")
    List<Profesor> findByCursoId(@Param("idCurso") int idCurso);
}