package com.example.exercicio2.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercicio2.Model.Curso;
import com.example.exercicio2.Repository.CursoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@SpringBootApplication
public class CursoController {
    
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping(value="/criarCurso")
    public Curso criaCurso(@RequestBody Curso curso) {
        return this.cursoRepository.save(curso);
    }

    @GetMapping(value="/cursos")
    public List<Curso> listarCursoes(){
        return this.cursoRepository.findAll();
    }

    @GetMapping("curso/{id}")
    public Optional<Curso> buscarCurso(@PathVariable("id") Long id){
        return this.cursoRepository.findById(id);
    }

    @PutMapping("editarCurso/{id}")
    public Curso atualizaCurso(@PathVariable("id") Long id, @RequestBody Curso curso){
        Optional<Curso> optionalCurso = this.cursoRepository.findById(id);

        if(optionalCurso.isPresent()){
            Curso cursoExistente = optionalCurso.get();
            cursoExistente.setDescricao(curso.getDescricao());
            cursoExistente.setCargaHoraria(curso.getCargaHoraria());
            cursoExistente.setObjetivos(curso.getObjetivos());
            cursoExistente.setEmenta(curso.getEmenta());

            return this.cursoRepository.save(cursoExistente);
        } else {
            return null;
        }
    }

    @DeleteMapping("excluirCurso/{id}")
    public void deletarCurso(@PathVariable("id") Long id){
        this.cursoRepository.deleteById(id);
    }
}
