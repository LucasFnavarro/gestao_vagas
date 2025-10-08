package br.com.portaldevagas.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.portaldevagas.gestao_vagas.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component → Spring vai gerenciar a instância dessa classe automaticamente.
@Component
public class SecurityCandidateFilter extends OncePerRequestFilter { // OncePerRequestFilter → garante que esse filtro
                                                                    // rode apenas uma vez por requisição, evitando
                                                                    // execução dupla.

  @Autowired
  private JWTCandidateProvider jwtProvider; // validar o token JWT dos candidatos.

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Limpa qualquer autenticação antiga para a requisição atual.
    // Garante que não reste nenhum usuário logado de requisições anteriores.
    // SecurityContextHolder.getContext().setAuthentication(null);

    // Aqui pegamos o token enviado pelo cliente no header Authorization.
    String header = request.getHeader("Authorization");

    // Filtra apenas rotas de candidatos
    if (request.getRequestURI().startsWith("/candidate")) {
      if (header != null) {
        var token = this.jwtProvider.validateToken(header);

        if (token == null) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        // Se for válido, coloca o candidate_id como atributo da requisição para ser
        // usado em outros lugares.
        request.setAttribute("candidate_id", token.getSubject());
        var roles = token.getClaim("roles").asList(Object.class);

        var grants = roles.stream()
            .map(
                role -> new SimpleGrantedAuthority("ROLE_" + role.toString().toUpperCase()))
            .toList();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token.getSubject(), null,
            grants);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }
    }

    // Permite que a requisição continue para os próximos filtros ou para o
    // controller final.
    // É obrigatório chamar, caso contrário a requisição para por aqui.
    filterChain.doFilter(request, response);
  }
}
