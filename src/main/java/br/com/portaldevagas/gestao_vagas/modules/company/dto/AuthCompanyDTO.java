package br.com.portaldevagas.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // cria um constructor
public class AuthCompanyDTO {
  private String username;
  private String password;
}
