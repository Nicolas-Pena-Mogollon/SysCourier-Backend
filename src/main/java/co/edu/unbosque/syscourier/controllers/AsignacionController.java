package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.exceptions.AsignacionException;
import co.edu.unbosque.syscourier.services.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AsignacionController {
    private final AsignacionService asignacionService;

    @Autowired
    public AsignacionController(AsignacionService asignacionService) {
        this.asignacionService = asignacionService;
    }

    @PutMapping("/asignacion")
    public ResponseEntity<?> asignarGuia(@RequestParam("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String correo = authentication.getName();
            try {
                asignacionService.realizarAsignacion(id, correo);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (AsignacionException e) {
                return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.UNAUTHORIZED);
        }

    }
}
