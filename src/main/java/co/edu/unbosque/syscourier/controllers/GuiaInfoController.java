package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.services.GuiaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador que maneja la información de guías.
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class GuiaInfoController {

    /**
     * Servicio de información de guias
     */
    private final GuiaInfoService guiaInfoService;


    /**
     * Constructor que maneja la dependencia del servicio de información de guía.
     *
     * @param guiaInfoService Servicio de información de guía.
     */
    @Autowired
    public GuiaInfoController(GuiaInfoService guiaInfoService) {
        this.guiaInfoService = guiaInfoService;
    }

    /**
     * Obtiene información de una guía por su identificador.
     *
     * @param id Identificador de la guía a ser consultada.
     * @return ResponseEntity con la información de la guía o un mensaje de error si no se encuentra.
     */
    @GetMapping("/guiainfo/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            GuiaInfoDTO guiaInfo = guiaInfoService.getById(Integer.parseInt(id));
            if (guiaInfo != null) {
                return new ResponseEntity<>(guiaInfo, HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO("Ocurrió un error al procesar la solicitud intente nuevamente"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new ErrorDTO("No se ha encontrado la guía"), HttpStatus.NOT_FOUND);
    }
}
