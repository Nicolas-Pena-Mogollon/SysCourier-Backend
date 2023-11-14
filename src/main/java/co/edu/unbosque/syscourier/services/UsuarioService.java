package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.UsuarioDTO;
import co.edu.unbosque.syscourier.mappers.UsuarioMapper;
import co.edu.unbosque.syscourier.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja las operaciones de los usuarios.
 *
 */
@Service
public class UsuarioService {

    /**
     * Atributo privado del mapper de usuarios.
     */
    private final UsuarioMapper usuarioMapper;

    /**
     * Atributo privado del repositorio de usuarios.
     */
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor de la clase que recibe el mapper y el repositorio como parámetros.
     *
     * @param usuarioMapper      mapper de usuarios.
     * @param usuarioRepository  Repositorio de usuarios.
     */
    @Autowired
    public UsuarioService(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método que obtiene un usuario por su correo y rol, y lo convierte a un DTO de usuario.
     *
     * @param correo Correo del usuario.
     * @param rol    Rol del usuario.
     * @return DTO de usuario.
     */
    public UsuarioDTO getByCorreoAndRol(String correo, String rol) {
        return usuarioMapper.toUsuarioDTO(usuarioRepository.findByCorreo(correo, rol).orElse(null));
    }

    /**
     * Método que valida las credenciales de un usuario.
     *
     * @param correo Correo del usuario.
     * @param pwd    Contraseña proporcionada.
     * @param rol    Rol del usuario.
     * @return true si las credenciales son válidas, false en caso contrario.
     */
    public boolean validarCredenciales(String correo, String pwd, String rol) {
        UsuarioDTO usuarioDTO = getByCorreoAndRol(correo, rol);
        return usuarioDTO != null && usuarioDTO.getContrasena().equals(pwd);
    }

}
