package com.letsdo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cfg=new CorsConfiguration();
                        cfg.setAllowedOrigins(Arrays.asList());
                        //To allow all the methods
                        cfg.setAllowedMethods(Collections.singletonList("*"));

                        cfg.setAllowCredentials(true);
                        cfg.setAllowedHeaders(Collections.singletonList("*"));
                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
                        cfg.setMaxAge(3600L);
                        return cfg;
                    }
                }
                )).httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()).formLogin(form->form.disable());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}




//                        cfg.setAllowedOrigins(Arrays.asList(
//                                "http://localhost:3000"    // URL of frontend
//                        ));
//                        cfg.setAllowedMethods(Collections.singletonList("*"));
//                        cfg.setAllowCredentials(true);
//                        cfg.setAllowedHeaders(Collections.singletonList("*"));
//                        cfg.setExposedHeaders(Arrays.asList("Authorization"));
//                        cfg.setMaxAge(3600L);
//                        return null;
//                    }
//                })
//                .and().httpBasic().and().formLogin();
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

