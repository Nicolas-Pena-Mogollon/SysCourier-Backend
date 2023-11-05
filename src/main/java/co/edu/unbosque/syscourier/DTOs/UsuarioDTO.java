package co.edu.unbosque.syscourier.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UsuarioDTO {

    private Integer idUsuario;
    private String contrasena;
    private String nombre;
    private String correo;

    private Integer idRol;
    private Integer sede;
    private Integer empresa;
    private Integer cantidadGuias;
}
