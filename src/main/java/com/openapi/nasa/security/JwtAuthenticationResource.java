package com.openapi.nasa.security;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class JwtAuthenticationResource {
    // inject JwtEncoder //
    private JwtEncoder jwtEncoder;

    @Autowired
    public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Hidden
    @PostMapping("/authenticate")
    public JWTResponse authenticate(Authentication authentication)
    {
        return new JWTResponse(createToken(authentication));
    }
    @Hidden
    @GetMapping("/get-token")
    public String getToken(Authentication authentication) {
        return createToken(authentication);
    }

    private String createToken(Authentication authentication) {
        var claims= JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60*30))
                .subject(authentication.getName())
                .claim("scope",createScope(authentication))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.joining(" "));
    }
}

record
JWTResponse(String token){}

