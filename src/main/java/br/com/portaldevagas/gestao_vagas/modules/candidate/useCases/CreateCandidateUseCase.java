package br.com.portaldevagas.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.portaldevagas.gestao_vagas.exceptions.UserFoundException;
import br.com.portaldevagas.gestao_vagas.modules.candidate.ICandidateRepository;
import br.com.portaldevagas.gestao_vagas.modules.candidate.entities.CandidateEntity;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private final ICandidateRepository candidateRepository;

  public CreateCandidateUseCase(ICandidateRepository candidateRepository) {
    this.candidateRepository = candidateRepository;
  }

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var passwordHashed = BCrypt.withDefaults()
        .hashToString(12, candidateEntity.getPassword().toCharArray());

    candidateEntity.setPassword(passwordHashed);

    return this.candidateRepository.save(candidateEntity);
  }
}
