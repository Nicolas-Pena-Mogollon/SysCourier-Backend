package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */
import lombok.*;

/**
 * Objeto (DTO) para manejar cada usuario.
 *
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor genera los constructores y parametros para la clase
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UsuarioDTO {

    /**
     * Atributos privados de la clase
     **/
    private Integer idUsuario;
    private String contrasena;
    private String nombre;
    private String correo;
    private Integer idRol;
    private Integer sede;
    private Integer empresa;
    private Integer cantidadGuias;
}
