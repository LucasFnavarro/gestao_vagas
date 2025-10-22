package br.com.portaldevagas.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.portaldevagas.gestao_vagas.exceptions.JobNotFoundException;
import br.com.portaldevagas.gestao_vagas.exceptions.UserNotFoundException;
import br.com.portaldevagas.gestao_vagas.modules.candidate.repositories.ICandidateRepository;
import br.com.portaldevagas.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private ICandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  // Id do candidate
  // Id do Job/Vaga
  public void execute(UUID idCandidate, UUID idJob) {
    this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundException();
        });

    this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
          throw new JobNotFoundException();
        });
  }
}
