package co.edu.unbosque.syscourier.controllers;

import co.edu.unbosque.syscourier.DTOs.ErrorDTO;
import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.services.GuiaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class GuiaInfoController {

    @Autowired
    private GuiaInfoService guiaInfoService;

    @GetMapping("/guiainfo/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(guiaInfoService.getById(Integer.parseInt(id)), HttpStatus.ACCEPTED);
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getCause());
            return new ResponseEntity<ErrorDTO>(new ErrorDTO(illegalArgumentException.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getCause());
            return new ResponseEntity<ErrorDTO>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
