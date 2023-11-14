package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.TokenDTO;
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
/**
 * Controlador que maneja las operaciones del login  y generación de tokens.
 */
@CrossOrigin
@RestController
public class LoginController {

    /**
     * Servicio que valida las credenciales durante el login.
     * Clave secreta con la que se firman los tokens JWT
     */
    private final UsuarioService usuarioService;
    @Value("${myapp.secretKey}")
    private String secretKey;

    /**
     * Constructor que maneja la dependencia del servicio de usuario.
     *
     * @param usuarioService Servicio de usuario.
     */
    @Autowired
    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Realizar el inicio de sesión de un mensajero y generar un token JWT.
     *
     * @param correo   Correo electrónico del mensajero.
     * @param password Contraseña del mensajero.
     * @return ResponseEntity con el token JWT en caso de éxito o un mensaje de error en caso de falla.
     */
    @PostMapping("/loginMensajero")
    public ResponseEntity<?> login(@RequestParam("correo") String correo, @RequestParam("password") String password) {

        if (usuarioService.validarCredenciales(correo, password, "MENSAJERO")) {
            return new ResponseEntity<>(new TokenDTO(getJWTToken(correo, "MENSAJERO")), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.OK);
        }
    }

    /**
     * Método que genera un token JWT.
     *
     * @param username Nombre de usuario (correo electrónico).
     * @param rol      Rol del usuario.
     * @return Token JWT firmado.
     */
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
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }
}