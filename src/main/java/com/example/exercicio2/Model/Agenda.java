package com.example.exercicio2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dataInicio;
    private String dataFim;
    private String horarioIncio;
    private String horarioFim;
    private String cidade;
    private String estado;
    private String cep;
    private String resumoTreinamento;

    @ManyToOne
    @JoinColumn(name = "professor_id ")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "curso_id ")
    private Curso curso;
}
