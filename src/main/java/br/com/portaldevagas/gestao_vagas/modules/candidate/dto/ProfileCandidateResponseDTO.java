package br.com.portaldevagas.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
  @Schema(example = "Densenvolvedora Java e Spring Boot")
  private String description;

  @Schema(example = "Maria de Souza")
  private String name;

  @Schema(example = "mariadesouza")
  private String username;

  @Schema(example = "mariadesouza@gmail.com")
  private String email;
  private UUID id;
}
