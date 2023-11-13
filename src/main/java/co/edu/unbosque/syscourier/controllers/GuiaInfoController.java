package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.services.GuiaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GuiaInfoController {

    private final GuiaInfoService guiaInfoService;

    @Autowired
    public GuiaInfoController(GuiaInfoService guiaInfoService) {
        this.guiaInfoService = guiaInfoService;
    }

    @GetMapping("/guiainfo/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {

        GuiaInfoDTO guiaInfo = guiaInfoService.getById(Integer.parseInt(id));
        if (guiaInfo!=null){
            return new ResponseEntity<>(guiaInfo, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new ErrorDTO("No se ha encontrado la guía"), HttpStatus.NOT_FOUND);
    }
}
