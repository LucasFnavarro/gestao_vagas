package br.com.portaldevagas.gestao_vagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portaldevagas.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface ICandidateRepository extends JpaRepository<CandidateEntity, UUID> {
  Optional<CandidateEntity> findByEmailOrUsername(String email, String username);

  Optional<CandidateEntity> findByUsername(String username);
}
