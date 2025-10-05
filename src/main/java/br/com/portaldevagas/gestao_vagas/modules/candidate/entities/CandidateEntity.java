package br.com.portaldevagas.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços")
  private String username;

  @Email(message = "O campo e-mail deve conter um e-mail válido")
  private String email;

  @NotBlank
  // @Length(min = 6, max = 20, message = "A senha deve conter entre 6 e 20
  // caracteres")
  private String password;

  @Length(min = 3, max = 3000, message = "A descrição deve conter entre 3 e 3000 caracteres")
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
