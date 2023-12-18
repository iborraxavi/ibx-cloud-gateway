package com.ibx.ibxcloudgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http,
            ServerLogoutSuccessHandler handler) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.anyExchange().permitAll())
                // TODO Descomentar para activar la autenticación
                /*.authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec
                                .pathMatchers("/actuator/**", "/", "logout.html")
                                .permitAll())
                .authorizeExchange(authorizeExchangeSpec ->
                        authorizeExchangeSpec
                                .anyExchange()
                                .authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(logoutSpec -> logoutSpec.logoutSuccessHandler(handler))*/
                .build();
    }

    @Bean
    public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(
            ReactiveClientRegistrationRepository repository
    ) {
        var oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logout.html");

        return oidcLogoutSuccessHandler;
    }

}
