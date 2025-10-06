package br.com.portaldevagas.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.portaldevagas.gestao_vagas.exceptions.UserFoundException;
import br.com.portaldevagas.gestao_vagas.modules.candidate.ICandidateRepository;
import br.com.portaldevagas.gestao_vagas.modules.candidate.entities.CandidateEntity;

@Service
public class CreateCandidateUseCase {
  @Autowired
  private final ICandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CreateCandidateUseCase(ICandidateRepository candidateRepository) {
    this.candidateRepository = candidateRepository;
  }

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByEmailOrUsername(candidateEntity.getEmail(), candidateEntity.getUsername())
        .ifPresent((user) -> {
          throw new UserFoundException();
        });

    var password = passwordEncoder.encode(candidateEntity.getPassword());

    candidateEntity.setPassword(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }
}
