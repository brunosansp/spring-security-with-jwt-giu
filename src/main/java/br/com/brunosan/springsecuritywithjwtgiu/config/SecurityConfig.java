package br.com.brunosan.springsecuritywithjwtgiu.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;
    
    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;
    
    /***
     * SecurityConfig é a classe responsável por orquestrar os Beans
     * Ela vai habilitar a segurança no projeto, e definir filtros de segurança
     */
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(
                auth -> auth.requestMatchers("/authenticate").permitAll()
                    .anyRequest().authenticated()
            )
            // para autenticação com usuário e senha, isso pelo menos para a primeira requisição
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer(
                // aqui estamos usando token JWT, mas é possível usar token opaco
                conf -> conf.jwt(Customizer.withDefaults())
            );
        return http.build();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
        // precisamos da chave pública para decodificar
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    
    @Bean
    JwtEncoder jwtEncoder() {
        /***
         * RSAKey é importado do pacote com.nimbusds.jose.jwk.RSAKey
         * pegamos o json web key
         */
        var jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        
        // aqui criamos o conjunto das chaves para retornar
        var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
    
    /***
     * Para não salvarmos em texto puro, iremos criptografar
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
