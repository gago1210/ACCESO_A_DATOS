package com.example.repository;

import com.example.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    // Buscar cursos por IDaula
    List<Curso> findByAulaId(int Idaula);

    // Buscar cursos por ID de profesor
    @Query("SELECT c FROM Curso c JOIN c.listaProfesores p WHERE p.id = :profesorId")
    List<Curso> findByProfesorId(@Param("profesorId") int profesorId);
}
