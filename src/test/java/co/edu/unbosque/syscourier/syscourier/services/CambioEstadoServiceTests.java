package co.edu.unbosque.syscourier.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.CambioEstadoDTO;
import co.edu.unbosque.syscourier.exceptions.CambioEstadoException;
import co.edu.unbosque.syscourier.models.repositories.CambioEstadoRepository;
import co.edu.unbosque.syscourier.services.CambioEstadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CambioEstadoServiceTests {

    @Mock
    private CambioEstadoRepository cambioEstadoRepository;

    @InjectMocks
    private CambioEstadoService cambioEstadoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarCambioEstado_exitoso() throws CambioEstadoException {
        // Configuración del comportamiento del mock
        when(cambioEstadoRepository.cambiarEstado(anyString(), anyInt(), anyInt(), anyString(), anyString())).thenReturn(true);

        // Llamada al método que estamos probando
        CambioEstadoDTO cambioEstadoDTO = new CambioEstadoDTO(1, 2, "Motivo", "Observaciones");
        boolean resultado = cambioEstadoService.realizarCambioEstado("correo@test.com", cambioEstadoDTO);

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(cambioEstadoRepository, times(1)).cambiarEstado("correo@test.com", 1, 2, "Motivo", "Observaciones");

        // Verificación del resultado esperado
        assert (resultado);
    }

    @Test
    void realizarCambioEstado_conExcepcionEnRepositorio_lanzaCambioEstadoException() throws CambioEstadoException {
        // Configuración del comportamiento del mock para lanzar una excepción
        when(cambioEstadoRepository.cambiarEstado(anyString(), anyInt(), anyInt(), anyString(), anyString()))
                .thenThrow(new CambioEstadoException("Error en el repositorio"));

        // Llamada al método que estamos probando y esperamos que lance la excepción específica
        CambioEstadoDTO cambioEstadoDTO = new CambioEstadoDTO(1, 2, "Motivo", "Observaciones");
        CambioEstadoException excepcion = org.junit.jupiter.api.Assertions.assertThrows(CambioEstadoException.class,
                () -> cambioEstadoService.realizarCambioEstado("correo@test.com", cambioEstadoDTO));

        // Verificación de que la excepción tiene el mensaje esperado
        org.junit.jupiter.api.Assertions.assertEquals("Error en el repositorio", excepcion.getMessage());

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(cambioEstadoRepository, times(1)).cambiarEstado("correo@test.com", 1, 2, "Motivo", "Observaciones");
    }

    @Test
    void realizarCambioEstado_conOtraExcepcion_lanzaCambioEstadoException() throws CambioEstadoException {
        // Configuración del comportamiento del mock para lanzar una excepción genérica
        when(cambioEstadoRepository.cambiarEstado(anyString(), anyInt(), anyInt(), anyString(), anyString()))
                .thenThrow(new RuntimeException("Error genérico"));

        // Llamada al método que estamos probando y esperamos que lance la excepción específica
        CambioEstadoDTO cambioEstadoDTO = new CambioEstadoDTO(1, 2, "Motivo", "Observaciones");
        CambioEstadoException excepcion = org.junit.jupiter.api.Assertions.assertThrows(CambioEstadoException.class,
                () -> cambioEstadoService.realizarCambioEstado("correo@test.com", cambioEstadoDTO));

        // Verificación de que el mensaje contiene la información esperada
        org.junit.jupiter.api.Assertions.assertTrue(excepcion.getMessage().contains("Error genérico"));

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(cambioEstadoRepository, times(1)).cambiarEstado("correo@test.com", 1, 2, "Motivo", "Observaciones");
    }
}
