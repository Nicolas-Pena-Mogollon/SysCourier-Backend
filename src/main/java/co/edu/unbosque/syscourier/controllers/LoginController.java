package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.services.UsuarioService;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private UsuarioService usuarioService;
    @Value("${myapp.secretKey}")
    private String secretKey;

    @PostMapping("/loginMensajero")
    public ResponseEntity<?> login(@RequestParam("correo") String correo, @RequestParam("password") String password) {

        if (usuarioService.validarCredenciales(correo, password, "MENSAJERO")){

            String token = getJWTToken(correo, "MENSAJERO");

            return new ResponseEntity<String>(token, HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<String>("No se ha ingresado correctamente", HttpStatus.OK);
        }



    }

    private String getJWTToken(String username, String rol) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(rol);

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}