package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.services.GuiaIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GuiaIntroController {

    private final GuiaIntroService guiaIntroService;

    @Autowired
    public GuiaIntroController(GuiaIntroService guiaIntroService) {
        this.guiaIntroService = guiaIntroService;
    }

    @GetMapping("/guiasIntro/{estado}")
    public ResponseEntity<?> getAllByUser(@PathVariable("estado") String estado) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String correo = authentication.getName();
            return new ResponseEntity<>(guiaIntroService.obtenerTodoPorUsuarioYEstado(correo, estado), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.UNAUTHORIZED);
        }
    }

}
