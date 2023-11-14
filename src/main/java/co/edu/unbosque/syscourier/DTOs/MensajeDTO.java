package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Objeto (DTO) para manejar los mensajes.
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor  genera los constructores y parametros para la clase
 **/
@Data
@AllArgsConstructor
public class MensajeDTO {
    /**
     * Atributos privados de la clase
     **/
    private String message;
}
