package br.com.portaldevagas.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
  private UUID id;
  private String name;

  @Pattern(regexp = "^(?!\\s*$).+", message = "O campo [username] não pode conter espaços")
  private String username;

  @Email(message = "O campo e-mail deve conter um e-mail válido")
  private String email;

  @Length(min = 6, max = 40)
  private String password;

  @Length(min = 3, max = 3000)
  private String description;
  private String curriculum;
}
