package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.services.GuiaIntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja la información de la introducción de guías
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class GuiaIntroController {

    /**
     * Servicio de Introducción  de las guias
     */
    private final GuiaIntroService guiaIntroService;

    /**
     * Constructor que maneja la dependencia del servicio de introducción de guías.
     *
     * @param guiaIntroService Servicio de introducción de guías.
     */
    @Autowired
    public GuiaIntroController(GuiaIntroService guiaIntroService) {
        this.guiaIntroService = guiaIntroService;
    }


    /**
     * Obtiene todas las guías de un usuario según su estado.
     *
     * @param estado Estado de las guías a ser consultadas.
     * @return ResponseEntity con la lista de guías o un mensaje de error si no se encuentra el usuario.
     */
    @GetMapping("/guiasIntro/{estado}")
    public ResponseEntity<?> getAllByUser(@PathVariable("estado") String estado) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            try {
                String correo = authentication.getName();
                return new ResponseEntity<>(guiaIntroService.obtenerTodoPorUsuarioYEstado(correo, estado), HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>(new ErrorDTO("Ocurrió un error al procesar la solicitud intente nuevamente"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new ErrorDTO("No se ha ingresado correctamente"), HttpStatus.UNAUTHORIZED);
        }
    }

}
