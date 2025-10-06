package br.com.portaldevagas.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.portaldevagas.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.portaldevagas.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body("Cadastro realizado com sucesso");

    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
