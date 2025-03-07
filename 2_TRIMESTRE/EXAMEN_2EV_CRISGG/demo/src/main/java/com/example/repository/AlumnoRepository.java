package com.example.repository;

import com.example.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    // Para buscar por id del curso:
    List<Alumno> findByCursoId(int idCurso);

    // Para buscar por nombre de curso:
    @Query("SELECT a FROM Alumno a WHERE a.curso.nombre = :nombreCurso")
    List<Alumno> findByNombreCurso(@Param("nombreCurso") String nombreCurso);

}
public class AlumnoRepository {
}
