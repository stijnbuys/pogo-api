package be.stijn.pokemonapp.security.jwt;

import be.stijn.pokemonapp.security.SecurityProperties;
import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenUtil {

    private final SecurityProperties properties;

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(properties.getAuthoritiesKey(), authorities)
                .signWith(SignatureAlgorithm.HS256, properties.getSigningKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getAccessTokenValidSeconds() * 1000))
                .compact();
    }

    public Boolean isValidToken(String token, String email) {
        return getEmailFromToken(token).equals(email) && !isTokenExpired(token);
    }

    String getJwtTokenFromAuthenticationHeader(String authenticationHeader) {
        if (authenticationHeader != null && authenticationHeader.startsWith(properties.getTokenPrefix())) {
            return authenticationHeader.replace(properties.getTokenPrefix(), "");
        }
        return null;
    }

    public String getEmailFromRequestHeaders(String authenticationHeader) {
        return getEmailFromToken(getJwtTokenFromAuthenticationHeader(authenticationHeader));
    }

    String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final UserDetails userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(properties.getSigningKey());

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(properties.getAuthoritiesKey()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
