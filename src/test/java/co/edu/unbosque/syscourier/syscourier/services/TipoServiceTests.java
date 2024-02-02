package co.edu.unbosque.syscourier.syscourier.services;

import co.edu.unbosque.syscourier.models.repositories.TipoRepository;
import co.edu.unbosque.syscourier.services.TipoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TipoServiceTests {

    @Mock
    private TipoRepository tipoRepository;

    @InjectMocks
    private TipoService tipoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getValorByID_retornaValorCorrecto() {
        // Configuración del comportamiento del mock
        when(tipoRepository.getValorByID(anyInt())).thenReturn("ValorEsperado");

        // Llamada al método que estamos probando
        String valor = tipoService.getValorByID(1);

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(tipoRepository, times(1)).getValorByID(1);

        // Verificación del resultado esperado
        assert (valor.equals("ValorEsperado"));
    }

    @Test
    void getValorByID_retornaNull() {
        // Configuración del comportamiento del mock
        when(tipoRepository.getValorByID(anyInt())).thenReturn(null);

        // Llamada al método que estamos probando
        String valor = tipoService.getValorByID(1);

        // Verificación de que el método del repositorio fue llamado con los argumentos esperados
        verify(tipoRepository, times(1)).getValorByID(1);

        // Verificación del resultado esperado
        assert (valor == null);
    }
}
