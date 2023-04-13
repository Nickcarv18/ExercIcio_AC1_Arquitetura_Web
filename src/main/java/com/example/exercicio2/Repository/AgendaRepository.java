package com.example.exercicio2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.exercicio2.Model.Agenda;
import com.example.exercicio2.Model.Professor;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
    
    List<Agenda> findByProfessor(Professor professor);

    @Query("SELECT a FROM Agenda a WHERE a.professor = :professor AND a.dataInicio BETWEEN :dataInicio AND :dataFim")
    List<Agenda> findByProfessorAndDataInicioBetween(Professor professor, String DataInicio, String dataFim);
}
