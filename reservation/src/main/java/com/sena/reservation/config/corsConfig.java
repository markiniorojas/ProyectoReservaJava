// package com.sena.reservation.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.cors.CorsConfiguration;

// @Configuration
// public class corsConfig {

//     @Bean 
//     public CorsFiltrer corsFilter() {
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         CorsConfiguration config = new CorsConfiguration();
        
//         CorsConfiguration configConsulta = new CorsConfiguration();

//         //Permitir solicitudes desde todos los origenes
//         // config.addAllowedOrigin("*");
//         config.setAllowOrigin(true);
//         config.addAllowedOrigin("*");
//         config.addAllowedHeader("*");
//         config.addAllowedMethod("*");
//         source.registerCorsConfiguration("/**", config);
//         return new CorsFilter(source);
// }
