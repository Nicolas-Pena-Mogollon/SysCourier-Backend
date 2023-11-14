package co.edu.unbosque.syscourier.filters;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Filtro de autorización que valida y procesa los tokens JWT en las solicitudes HTTP.
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {


    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    @Value("${myapp.secretKey}")
    private String secretKey;

    /**
     * Filtra las solicitudes HTTP para validar y procesar los tokens JWT.
     *
     * @param request  HttpServletRequest que representa la solicitud HTTP.
     * @param response HttpServletResponse que representa la respuesta HTTP.
     * @param chain    FilterChain que permite la ejecución de filtros adicionales.
     * @throws ServletException Si ocurre una excepción de servlet.
     * @throws IOException      Si ocurre una excepción de entrada/salida.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (existeJWTToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims.get("authorities") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    /**
     * Valida y parsea el token JWT obtenido del encabezado de autorización de la solicitud.
     *
     * @param request HttpServletRequest que representa la solicitud HTTP.
     * @return Claims (reclamaciones) del token JWT que contienen información como el nombre de usuario, roles, etc.
     * @throws ExpiredJwtException      Si el token JWT ha expirado.
     * @throws UnsupportedJwtException  Si el token JWT no es soportado.
     * @throws MalformedJwtException     Si el token JWT está mal formado.
     */
    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    /**
     * Metodo para autenticarnos dentro del flujo de Spring
     *
     * @param claims Claims del token JWT que contienen la información de autorización.
     */
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("authorities");

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    /**
     * Verifica si existe un token JWT en la solicitud.
     *
     * @param request HttpServletRequest que representa la solicitud HTTP.
     * @param res     HttpServletResponse que representa la respuesta HTTP.
     * @return true si existe un token JWT, false en caso contrario.
     */
    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

}