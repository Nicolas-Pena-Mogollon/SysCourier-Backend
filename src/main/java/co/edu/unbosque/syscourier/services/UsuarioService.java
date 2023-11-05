package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.UsuarioDTO;
import co.edu.unbosque.syscourier.mappers.UsuarioMapper;
import co.edu.unbosque.syscourier.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public UsuarioDTO getByCorreoAndRol(String correo, String rol) {
        return usuarioMapper.toUsuarioDTO(usuarioRepository.findByCorreo(correo, rol).orElse(null));
    }

    public boolean validarCredenciales(String correo, String pwd, String rol) {
        UsuarioDTO usuarioDTO = getByCorreoAndRol(correo, rol);
        return usuarioDTO != null && usuarioDTO.getContrasena().equals(pwd);
    }

}
