package br.com.portaldevagas.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity(name = "company")
@Data
public class CompanyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String username;

  @Email(message = "O e-mail deve ser um e-mail válido.")
  private String email;
  private String password;
  private String website;
  private String cnpj;

  @Length(min = 3, max = 3000, message = "A descrição deve conter entre 3 e 3 mil caracteres")
  private String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
