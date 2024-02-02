package co.edu.unbosque.syscourier.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.UsuarioDTO;
import co.edu.unbosque.syscourier.mappers.UsuarioMapper;
import co.edu.unbosque.syscourier.models.entities.Usuario;
import co.edu.unbosque.syscourier.models.repositories.UsuarioRepository;
import co.edu.unbosque.syscourier.services.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTests {

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByCorreoAndRol_retornaUsuarioDTO() {
        // Configuración del comportamiento del mock
        when(usuarioRepository.findByCorreo(anyString(), anyString())).thenReturn(Optional.of(new Usuario()));
        when(usuarioMapper.toUsuarioDTO(any())).thenReturn(new UsuarioDTO());

        // Llamada al método que estamos probando
        UsuarioDTO usuarioDTO = usuarioService.getByCorreoAndRol("correo", "rol");

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(usuarioRepository, times(1)).findByCorreo("correo", "rol");

        // Verificación del resultado esperado
        assertNotNull(usuarioDTO);
    }

    @Test
    void getByCorreoAndRol_retornaNull() {
        // Configuración del comportamiento del mock
        when(usuarioRepository.findByCorreo(anyString(), anyString())).thenReturn(Optional.empty());

        // Llamada al método que estamos probando
        UsuarioDTO usuarioDTO = usuarioService.getByCorreoAndRol("correo", "rol");

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(usuarioRepository, times(1)).findByCorreo("correo", "rol");

        // Verificación del resultado esperado
        assertNull(usuarioDTO);
    }

    @Test
    void validarCredenciales_credencialesValidas_retornaTrue() {
        // Configuración del comportamiento del mock
        when(usuarioRepository.findByCorreo(anyString(), anyString())).thenReturn(Optional.of(new Usuario()));
        when(usuarioMapper.toUsuarioDTO(any())).thenReturn(new UsuarioDTO());
        when(usuarioMapper.toUsuarioDTO(isNull())).thenReturn(null);

        // Llamada al método que estamos probando
        boolean resultado = usuarioService.validarCredenciales("correo", "pwd", "rol");

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(usuarioRepository, times(1)).findByCorreo("correo", "rol");

        // Verificación del resultado esperado
        assertTrue(resultado);
    }

    @Test
    void validarCredenciales_credencialesInvalidas_retornaFalse() {
        // Configuración del comportamiento del mock
        when(usuarioRepository.findByCorreo(anyString(), anyString())).thenReturn(Optional.empty());

        // Llamada al método que estamos probando
        boolean resultado = usuarioService.validarCredenciales("correo", "pwd", "rol");

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(usuarioRepository, times(1)).findByCorreo("correo", "rol");

        // Verificación del resultado esperado
        assertFalse(resultado);
    }

    // Puedes agregar más tests para validarCredenciales con diferentes escenarios (contraseña incorrecta, etc.)

    // También puedes agregar tests para hashStringWithMD5 y hashStringWithSHA1 si es necesario.
}
