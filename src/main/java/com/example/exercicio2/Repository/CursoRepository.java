package com.example.exercicio2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exercicio2.Model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
    
}
