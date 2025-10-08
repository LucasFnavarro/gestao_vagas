package br.com.portaldevagas.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data → já gera getters, setters, toString(), equals(), hashCode()
@Builder // @Builder → te deixa instanciar sem depender da ordem e sem precisar escrever
         // construtores manualmente
@AllArgsConstructor // → gera o construtor com todos os atributos
@NoArgsConstructor // @NoArgsConstructor → gera o construtor vazio
public class AuthCandidateResponseDTO {
  private String access_token;
  private Long expires_in;
}
