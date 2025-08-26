package org.robn.ecommerce.auth.config;

import lombok.RequiredArgsConstructor;
import org.robn.ecommerce.auth.filter.JwtFilter;
import org.robn.ecommerce.auth.security.EcoAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private static final String API_V1_AUTH = "/api/v1/auth/**";
    private static final String API_V1_GUEST_ADDRESSES = "/api/v1/guest-addresses/**";
    private static final String API_V1_CUSTOMER_ADDRESSES = "/api/v1/customer-addresses/**";
    private static final String API_V1_SELLER_ADDRESSES = "/api/v1/seller-addresses/**";
    private static final String API_V1_BRANDS = "/api/v1/brands/**";
    private static final String API_V1_CATEGORIES = "/api/v1/categories/**";
    private static final String API_V1_INVENTORIES = "/api/v1/inventories/**";
    private static final String API_V1_PRODUCTS = "/api/v1/products/**";
    private static final String API_V1_WAREHOUSES = "/api/v1/warehouses/**";
    private static final String API_V1_PRODUCT_CATEGORY_RELATIONS = "/api/v1/product-category-relations/**";

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity,
                                                   final EcoAuthenticationEntryPoint ecoAuthenticationEntryPoint) throws Exception {
        httpSecurity
                .exceptionHandling(customizer -> customizer.authenticationEntryPoint(ecoAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(API_V1_AUTH).permitAll()
                        .requestMatchers(API_V1_GUEST_ADDRESSES).permitAll()
                        .requestMatchers(HttpMethod.GET, API_V1_CUSTOMER_ADDRESSES).hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, API_V1_CUSTOMER_ADDRESSES).hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_CUSTOMER_ADDRESSES).hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_SELLER_ADDRESSES).hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, API_V1_SELLER_ADDRESSES).hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_SELLER_ADDRESSES).hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_BRANDS).permitAll()
                        .requestMatchers(HttpMethod.POST, API_V1_BRANDS).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_BRANDS).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_CATEGORIES).permitAll()
                        .requestMatchers(HttpMethod.POST, API_V1_CATEGORIES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_CATEGORIES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_INVENTORIES).permitAll()
                        .requestMatchers(HttpMethod.POST, API_V1_INVENTORIES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_INVENTORIES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_PRODUCTS).permitAll()
                        .requestMatchers(HttpMethod.POST, API_V1_PRODUCTS).hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_PRODUCTS).hasAnyRole("SELLER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_WAREHOUSES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, API_V1_WAREHOUSES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_WAREHOUSES).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, API_V1_PRODUCT_CATEGORY_RELATIONS).permitAll()
                        .requestMatchers(HttpMethod.POST, API_V1_PRODUCT_CATEGORY_RELATIONS).hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, API_V1_PRODUCT_CATEGORY_RELATIONS).hasAnyRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
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
