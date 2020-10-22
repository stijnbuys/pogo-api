package be.stijn.pokemonapp.security.jwt;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            String jwtToken = jwtTokenUtil.getJwtTokenFromAuthenticationHeader(request.getHeader(AUTHORIZATION));
            if (jwtToken != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getEmailFromToken(jwtToken));

                setAuthenticationIfValidToken(request, jwtToken, userDetails);
            }
        } catch (Exception e) {
            log.error("Can NOT set user authentication -> Message:", e);
        }
        chain.doFilter(request, response);
    }

    private void setAuthenticationIfValidToken(HttpServletRequest request, String jwtToken, UserDetails userDetails) {
        if (jwtTokenUtil.isValidToken(jwtToken, userDetails.getUsername())) {
            UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(jwtToken, userDetails);

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            getContext().setAuthentication(authentication);
        }
    }
}
