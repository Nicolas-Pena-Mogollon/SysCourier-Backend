package co.edu.unbosque.syscourier.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa la entidad usuario en la base de datos.
 *
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor genera los constructores y parametros para la clase
 * @NoArgsConstructor genera constructores sin argumentos
 * @Getter genera los getters de la clase
 * @Setter genera los setters de la clase
 *
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "sede")
    private Integer sede;

    @Column(name = "empresa")
    private Integer empresa;

    @Column(name = "cantidad_guias")
    private Integer cantidadGuias;
}
