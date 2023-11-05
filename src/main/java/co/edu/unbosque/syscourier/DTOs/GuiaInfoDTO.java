package co.edu.unbosque.syscourier.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class GuiaInfoDTO {

    private Integer id;
    private String producto;
    private String remitenteIdentificacion;
    private String remitenteNombre;
    private String remitenteDireccion;
    private String remitenteTelefono;
    private String destinatarioIdentificacion;
    private String destinatarioNombre;
    private String destinatarioDireccion;
    private String destinatarioTelefono;
    private Double totalFlete;

}
