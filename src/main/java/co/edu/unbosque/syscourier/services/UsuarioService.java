package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.UsuarioDTO;
import co.edu.unbosque.syscourier.mappers.UsuarioMapper;
import co.edu.unbosque.syscourier.models.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Servicio que maneja las operaciones de los usuarios.
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
     * @param usuarioMapper     mapper de usuarios.
     * @param usuarioRepository Repositorio de usuarios.
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
        String sha1 = this.hashStringWithSHA1(pwd);
        if (sha1.isEmpty()) {
            return false;
        }
        String md = this.hashStringWithMD5(sha1);
        if (md.isEmpty()) {
            return false;
        }
        UsuarioDTO usuarioDTO = getByCorreoAndRol(correo, rol);
        return usuarioDTO != null && usuarioDTO.getContrasena().equals(md);
    }

    public String hashStringWithMD5(String input) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] hashedBytes = md5.digest(input.getBytes());

            BigInteger bigInt = new BigInteger(1, hashedBytes);
            StringBuilder md5Hash = new StringBuilder(bigInt.toString(16));

            while (md5Hash.length() < 32) {
                md5Hash.insert(0, "0");
            }

            return md5Hash.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public String hashStringWithSHA1(String input) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha1.digest(input.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
