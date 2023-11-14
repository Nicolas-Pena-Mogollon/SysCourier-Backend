package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * clase para el DTO del cambio de estado
 *
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor genera los constructores y parametros para la clase
 **/
@Data
@AllArgsConstructor
public class CambioEstadoDTO {
    /**
     * Atributos privados de la clase
     **/
    private int guiaId;
    private int codEstado;
    String motivo;
    String observaciones;
}
