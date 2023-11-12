package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.CambioEstadoDTO;
import co.edu.unbosque.syscourier.exceptions.CambioEstadoException;
import co.edu.unbosque.syscourier.models.repositories.CambioEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CambioEstadoService {
    private final CambioEstadoRepository cambioEstadoRepository;

    @Autowired
    public CambioEstadoService(CambioEstadoRepository cambioEstadoRepository) throws CambioEstadoException {
        this.cambioEstadoRepository = cambioEstadoRepository;
    }

    public boolean realizarCambioEstado(String correo, CambioEstadoDTO cambioEstadoDTO) throws CambioEstadoException {
        try {
            return cambioEstadoRepository.
                    cambiarEstado(correo, cambioEstadoDTO.getGuiaId(), cambioEstadoDTO.getCodEstado(), cambioEstadoDTO.getMotivo(), cambioEstadoDTO.getObservaciones());
        } catch (CambioEstadoException e) {
            throw e;
        } catch (Exception e) {
            throw new CambioEstadoException("Error durante la asignación: " + e);
        }
    }
}
