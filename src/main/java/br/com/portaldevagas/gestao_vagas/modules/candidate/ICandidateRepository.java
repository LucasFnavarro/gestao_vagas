package br.com.portaldevagas.gestao_vagas.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portaldevagas.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface ICandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByEmail(String email);
}
