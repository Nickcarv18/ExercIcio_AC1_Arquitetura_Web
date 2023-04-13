package com.example.exercicio2.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String descricao;
    private Integer cargaHoraria; 
    private String objetivos;
    private String ementa;

    @ManyToMany(mappedBy = "cursos")
    private List<Professor> professores;

    @OneToMany(mappedBy = "curso")
    private List<Agenda> agendas;
}
