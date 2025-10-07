package br.com.portaldevagas.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.portaldevagas.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.portaldevagas.gestao_vagas.modules.candidate.repositories.ICandidateRepository;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretKey;

  @Autowired
  private ICandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("Username or password incorrect.");
        });

    var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException("Username or password incorrect.");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create()
        .withIssuer("javagas")
        .withSubject(candidate.getId().toString())
        .withExpiresAt(Instant.now().plus(Duration.ofHours(1)))
        .sign(algorithm);

    return token;
  }
}
