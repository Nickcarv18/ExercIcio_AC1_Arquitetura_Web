package com.example.exercicio2.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.exercicio2.Model.Agenda;
import com.example.exercicio2.Model.Curso;
import com.example.exercicio2.Model.Professor;
import com.example.exercicio2.Repository.AgendaRepository;
import com.example.exercicio2.Repository.CursoRepository;
import com.example.exercicio2.Repository.ProfessorRepository;

public class AgendaController {
    
    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/criarAgenda")
    public Agenda cadastrarAgenda(@RequestBody Agenda agenda) {
        
        List<Agenda> agendas = agendaRepository.findByProfessorAndDataInicioBetween(
                agenda.getProfessor(),
                agenda.getDataInicio(),
                agenda.getDataFim()
        );

        if (!agendas.isEmpty()) {
            throw new RuntimeException("O professor já está ministrando curso em outra agenda nesta data.");
        }

        Optional<Curso> cursoOptional = cursoRepository.findById(agenda.getCurso().getId());

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();

            if (!curso.getProfessores().contains(agenda.getProfessor())) {
                throw new RuntimeException("O professor pode ministrar este curso.");
            }

            cursoRepository.save(curso);
        } else {
            throw new RuntimeException("Curso não encontrado.");
        }

        return agendaRepository.save(agenda);
    }

    @PostMapping("/cadastrarResumo/{agendaId}")
    public Agenda cadastrarResumoTreinamento(@PathVariable("agendaId") Long agendaId, @RequestBody String resumo) {
        
        Optional<Agenda> agendaOptional = agendaRepository.findById(agendaId);

        if (agendaOptional.isPresent()) {
            Agenda agenda = agendaOptional.get();
            
            agenda.setResumoTreinamento(resumo);

            return agendaRepository.save(agenda);
        } else {
            throw new RuntimeException("Agenda não encontrada.");
        }
    }

    @GetMapping(value="/agendas")
    public List<Agenda> listarAgenda(){
        return this.agendaRepository.findAll();
    }

    @GetMapping("agenda/{id}")
    public Optional<Agenda> buscarAgenda(@PathVariable("id") Long id){
        return this.agendaRepository.findById(id);
    }

    @GetMapping("/listar/{professorId}")
    public List<Agenda> listarAgendasPorProfessor(@PathVariable("professorId") Long professorId) {
        
        Optional<Professor> professorOptional = professorRepository.findById(professorId);

        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();

            List<Agenda> agendas = agendaRepository.findByProfessor(professor);

            return agendas;
        } else {
            throw new RuntimeException("Professor não encontrado.");
        }
    }

    @DeleteMapping("deletarAgenda/{id}")
    public void deletarCurso(@PathVariable("id") Long id){
        this.agendaRepository.deleteById(id);
    }
}
