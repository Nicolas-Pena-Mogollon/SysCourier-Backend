package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.TokenDTO;
import co.edu.unbosque.syscourier.security.JWTConfig;
import co.edu.unbosque.syscourier.services.UsuarioService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class LoginController {

    private final UsuarioService usuarioService;

    private final JWTConfig jwtConfig;

    @Autowired
    public LoginController(UsuarioService usuarioService, JWTConfig jwtConfig) {
        this.usuarioService = usuarioService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/loginMensajero")
    public ResponseEntity<?> login(@RequestParam("correo") String correo, @RequestParam("password") String password) {
        try {
            if (usuarioService.validarCredenciales(correo, password, "MENSAJERO")) {
                return new ResponseEntity<>(new TokenDTO(getJWTToken(correo, "MENSAJERO")), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO("Ocurrió un error al procesar la solicitud intente nuevamente"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getJWTToken(String username, String rol) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("SysCourierJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000000))
                .signWith(SignatureAlgorithm.HS512,
                        this.jwtConfig.getSecretKey().getBytes()).compact();
        return "Bearer " + token;
    }
}