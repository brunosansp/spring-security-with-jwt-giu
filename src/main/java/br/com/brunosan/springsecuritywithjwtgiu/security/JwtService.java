package br.com.brunosan.springsecuritywithjwtgiu.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {

    // essa dependência(OAuth2 Resource Server) precisa ser adicionada no pom.xml
    private final JwtEncoder encoder;
    
    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }
    
    // Este Authentication é do pacote Spring Security
    public String generateToken(Authentication authentication){
        Instant now = Instant.now();
        long expiry = 3600L;
//        Duration duration = Duration.ofSeconds(expiry);
//        Instant expiryInstant = now.plus(duration);
        
        /***
         * Os escopos são obtidos das authorities.
         * Ao pegar as authorities, transformamos em um stream de dados
         * mapeamos as authorities pegando da string retornada em GrantedAuthority
         * coletamos com o Collectors.joining
         *
         */
        String scopes = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining());
        
        // pegando as propriedades do token, as claims
        var claims = JwtClaimsSet.builder()
            // identity provider do projeto
            .issuer("spring-security-with-jwt-giu")
            // quando o token foi emitido
            .issuedAt(now)
            // Em expiresAt passamos o tempo futuro, quando vai expirar. O now + expiry.
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.getName())
            // No claim é possível passar propriedades customizadas, caso queira colocar mais propriedades do que UserDetails
            .claim("scopes", scopes)
            .build();
        
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
