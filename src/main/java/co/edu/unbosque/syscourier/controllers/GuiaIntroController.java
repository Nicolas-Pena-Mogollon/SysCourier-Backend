package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
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

    @Autowired
    private GuiaIntroService guiaIntroService;

    @GetMapping("/guiasIntro/{estado}")
    public ResponseEntity<?> getAllByUser(@PathVariable("estado") String estado) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            String correo = authentication.getName();
            return new ResponseEntity<>(guiaIntroService.obtenerTodoPorUsuario(correo, estado), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("No se ha ingresado correctamente", HttpStatus.OK);
        }
        // Resto del código de tu controlador
    }


    @GetMapping("/guiaintro/{id}")
    public ResponseEntity<? extends Object> getById(@PathVariable("id") Integer id) {
        try {
            return new ResponseEntity<GuiaIntroDTO>(guiaIntroService.getById(id), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getCause());
            return new ResponseEntity<ErrorDTO>(new ErrorDTO(illegalArgumentException.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return new ResponseEntity<ErrorDTO>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
