package org.robn.ecommerce.auth.config;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.filter.JwtFilter;
import org.robn.ecommerce.auth.model.enums.Role;
import org.robn.ecommerce.auth.security.EcoAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String BASE_URL = "/api/v1";
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity,
                                                   final EcoAuthenticationEntryPoint ecoAuthenticationEntryPoint) throws Exception {
        httpSecurity
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(ecoAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(BASE_URL + "/auth/**").anonymous()
                        .requestMatchers(BASE_URL + "/guest-addresses/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_URL + "/brands/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_URL + "/categories/**").permitAll()
                        .requestMatchers(BASE_URL + "/inventories/**").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, BASE_URL + "/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_URL + "/product-category-relations/**").permitAll()
                        .requestMatchers(HttpMethod.GET, BASE_URL + "/warehouses/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /**
     * Returns a new instance of {@link BCryptPasswordEncoder} as the password encoder.
     * This bean is used to encode and verify passwords in the application.
     *
     * @return A {@link PasswordEncoder} instance using BCrypt hashing algorithm.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
