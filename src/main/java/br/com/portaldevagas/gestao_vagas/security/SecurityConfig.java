package br.com.portaldevagas.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@Configuration indica que essa classe fornece configurações do Spring, especialmente beans.
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private SecurityFilter securityFilter;

  @Autowired
  private SecurityCandidateFilter securityCandidateFilter;

  @Bean // O Spring vai usar esse bean para definir todas as regras de segurança da
        // aplicação.
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable()) // Desativa a proteção CSRF, comum em APIs REST, porque normalmente não usamos
                                      // cookies/sessões.
        .authorizeHttpRequests(auth -> {
          auth.requestMatchers("/candidate").permitAll()
              .requestMatchers("/company").permitAll()
              .requestMatchers("/company/auth").permitAll()
              .requestMatchers("/candidate/auth").permitAll();

          // Qualquer requisição que não tenha sido explicitamente listada antes deve
          // exigir autenticação.
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class) // JWT filters personalizados para
                                                                                   // interceptar as requisições
        .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
