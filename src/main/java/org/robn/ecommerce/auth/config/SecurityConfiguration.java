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

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity,
                                                   final EcoAuthenticationEntryPoint ecoAuthenticationEntryPoint) throws Exception {
        httpSecurity
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(ecoAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").anonymous()
                        .requestMatchers("/api/v1/guest-addresses/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/brands/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers("/api/v1/inventories/**").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/product-category-relations/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/warehouses/**").permitAll()
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
