package co.edu.unbosque.syscourier.syscourier.services;

import co.edu.unbosque.syscourier.exceptions.AsignacionException;
import co.edu.unbosque.syscourier.models.repositories.AsignacionRepository;
import co.edu.unbosque.syscourier.services.AsignacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AsignacionServiceTests {

    @Mock
    private AsignacionRepository asignacionRepository;

    @InjectMocks
    private AsignacionService asignacionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void realizarAsignacion_exitoso() throws AsignacionException {
        // Configuración del comportamiento del mock
        doNothing().when(asignacionRepository).realizarAsignacion(anyInt(), anyString());

        // Llamada al método que estamos probando
        asignacionService.realizarAsignacion(1, "correo@test.com");

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(asignacionRepository, times(1)).realizarAsignacion(1, "correo@test.com");
    }

    @Test
    void realizarAsignacion_conExcepcionEnRepositorio_lanzaAsignacionException() throws AsignacionException {
        // Configuración del comportamiento del mock para lanzar una excepción
        doThrow(new AsignacionException("Error en el repositorio")).when(asignacionRepository)
                .realizarAsignacion(anyInt(), anyString());

        // Llamada al método que estamos probando y esperamos que lance la excepción específica
        AsignacionException excepcion = org.junit.jupiter.api.Assertions.assertThrows(AsignacionException.class,
                () -> asignacionService.realizarAsignacion(1, "correo@test.com"));

        // Verificación de que la excepción tiene el mensaje esperado
        org.junit.jupiter.api.Assertions.assertEquals("Error en el repositorio", excepcion.getMessage());

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(asignacionRepository, times(1)).realizarAsignacion(1, "correo@test.com");
    }

    @Test
    void realizarAsignacion_conOtraExcepcion_lanzaAsignacionException() throws AsignacionException {
        // Configuración del comportamiento del mock para lanzar una excepción genérica
        doThrow(new RuntimeException("Error genérico")).when(asignacionRepository)
                .realizarAsignacion(anyInt(), anyString());

        // Llamada al método que estamos probando y esperamos que lance la excepción específica
        AsignacionException excepcion = org.junit.jupiter.api.Assertions.assertThrows(AsignacionException.class,
                () -> asignacionService.realizarAsignacion(1, "correo@test.com"));

        // Verificación de que el mensaje contiene la información esperada
        org.junit.jupiter.api.Assertions.assertTrue(excepcion.getMessage().contains("Error genérico"));

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(asignacionRepository, times(1)).realizarAsignacion(1, "correo@test.com");
    }
}
