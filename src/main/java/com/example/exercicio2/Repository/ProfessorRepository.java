package com.example.exercicio2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exercicio2.Model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
     
}
