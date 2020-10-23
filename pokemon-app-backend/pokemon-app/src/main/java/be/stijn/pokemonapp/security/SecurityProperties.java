package be.stijn.pokemonapp.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
@Configuration
@Data
@ConfigurationProperties("be.stijn.security")
public class SecurityProperties {

    private String signingKey;
    private String authoritiesKey;
    private long accessTokenValidSeconds;
    private String tokenPrefix = "Bearer ";
    private String projectVersion;
}
