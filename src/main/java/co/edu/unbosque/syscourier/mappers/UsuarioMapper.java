package co.edu.unbosque.syscourier.mappers;

import co.edu.unbosque.syscourier.DTOs.UsuarioDTO;
import co.edu.unbosque.syscourier.models.entities.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "idUsuario", target = "idUsuario")
    @Mapping(source = "contrasena", target = "contrasena")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "correo", target = "correo")
    @Mapping(source = "idRol", target = "idRol")
    @Mapping(source = "sede", target = "sede")
    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "cantidadGuias", target = "cantidadGuias")
    UsuarioDTO toUsuarioDTO(Usuario usuario);

    @InheritInverseConfiguration
    Usuario toUsuario (UsuarioDTO usuarioDTO);

}
