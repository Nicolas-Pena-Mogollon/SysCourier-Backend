package co.edu.unbosque.syscourier.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GuiaIntroDTO {

    private Integer id;
    private String remitenteNombre;
    private String destinatarioDireccion;

}
