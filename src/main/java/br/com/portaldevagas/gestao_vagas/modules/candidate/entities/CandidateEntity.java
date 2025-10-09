package br.com.portaldevagas.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(example = "John Does")
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [username] não pode conter espaços")
  @Schema(example = "John John Doe")
  private String username;

  @Email(message = "O campo e-mail deve conter um e-mail válido")
  @Schema(example = "johndoe@example.com")
  private String email;

  @NotBlank
  // @Length(min = 6, max = 20, message = "A senha deve conter entre 6 e 20
  // caracteres")
  @Schema(example = "teste123123")
  private String password;

  @Length(min = 3, max = 3000, message = "A descrição deve conter entre 3 e 3000 caracteres")
  @Schema(example = "Desenvolvedor Full Stack com foco em backend Java e Spring Boot")
  private String description;

  @Schema(example = "curriculum-dev-fullstack.pdf")
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
