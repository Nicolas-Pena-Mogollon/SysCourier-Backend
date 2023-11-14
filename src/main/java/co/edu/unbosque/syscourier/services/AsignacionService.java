package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.exceptions.AsignacionException;
import co.edu.unbosque.syscourier.models.repositories.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la asignación de guías a mensajeros.
 *
 */
@Service
public class AsignacionService {

    /**
     * Atributo privado del repositorio de asignación.
     */
    private final AsignacionRepository asignacionRepository;

    /**
     * Constructor de la clase que maneja el repositorio de asignación.
     *
     * @param asignacionRepository Repositorio de asignación.
     */
    @Autowired
    public AsignacionService(AsignacionRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    /**
     * Método que realiza la asignación de una guía a un mensajero.
     *
     * @param guiaId Identificador de la guía a asignar.
     * @param correo Correo electrónico del mensajero al que se asignará la guía.
     * @throws AsignacionException Excepción lanzada en caso de errores durante la asignación.
     */
    public void realizarAsignacion(int guiaId, String correo) throws AsignacionException {
        try {
            asignacionRepository.realizarAsignacion(guiaId, correo);
        } catch (AsignacionException e) {
            throw e;
        } catch (Exception e) {
            throw new AsignacionException("Error durante la asignación: " + e);
        }
    }
}
