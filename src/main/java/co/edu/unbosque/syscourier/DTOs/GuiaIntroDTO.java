package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Objeto (DTO) para manejar la información introductoria de las guias.
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor  genera los constructores y parametros para la clase
 **/
@Data
@AllArgsConstructor
public class GuiaIntroDTO {
    /**
     * Atributos privados de la clase
     **/
    private Integer id;
    private String remitenteNombre;
    private String destinatarioDireccion;

}
