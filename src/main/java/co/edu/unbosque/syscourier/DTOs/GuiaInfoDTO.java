package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Objeto (DTO) para manejar la información de las guias.
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor  genera los constructores y parametros para la clase
 **/
@Data
@AllArgsConstructor

public class GuiaInfoDTO {
    /**
     * Atributos privados de la clase
     **/
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
