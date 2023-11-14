package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.CambioEstadoDTO;
import co.edu.unbosque.syscourier.exceptions.CambioEstadoException;
import co.edu.unbosque.syscourier.models.repositories.CambioEstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona el cambio de estado de guías.
 */
@Service
public class CambioEstadoService {

    /**
     * Atributo privado del repositorio de cambio de estado.
     */
    private final CambioEstadoRepository cambioEstadoRepository;

    /**
     * Constructor de la clase que maneja el repositorio de cambio de estado.
     *
     * @param cambioEstadoRepository Repositorio de cambio de estado.
     * @throws CambioEstadoException Excepción lanzada en caso de errores durante la inicialización.
     */
    @Autowired
    public CambioEstadoService(CambioEstadoRepository cambioEstadoRepository) throws CambioEstadoException {
        this.cambioEstadoRepository = cambioEstadoRepository;
    }

    /**
     * Método que realiza el cambio de estado de una guía.
     *
     * @param correo          Correo electrónico del usuario que realiza el cambio de estado.
     * @param cambioEstadoDTO DTO que contiene la información del cambio de estado.
     * @return true si el cambio de estado se realizó con éxito, false en caso contrario.
     * @throws CambioEstadoException Excepción lanzada en caso de errores durante el cambio de estado.
     */
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
