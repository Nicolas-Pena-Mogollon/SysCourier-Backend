package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.CambioEstadoDTO;
import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.exceptions.CambioEstadoException;
import co.edu.unbosque.syscourier.services.CambioEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CambioEstadoController {

    private final CambioEstadoService cambioEstadoService;

    @Autowired
    public CambioEstadoController(CambioEstadoService cambioEstadoService) {
        this.cambioEstadoService = cambioEstadoService;
    }

    @PutMapping("/cambioestado")
    public ResponseEntity<?> cambiarEstado(@RequestBody CambioEstadoDTO cambioEstadoDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String correo = authentication.getName();
            try {
                cambioEstadoService.realizarCambioEstado(correo, cambioEstadoDTO);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (CambioEstadoException e) {
                return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.UNAUTHORIZED);
        }
    }

}
