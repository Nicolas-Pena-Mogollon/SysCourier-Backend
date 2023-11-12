package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.exceptions.AsignacionException;
import co.edu.unbosque.syscourier.models.repositories.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {
    private final AsignacionRepository asignacionRepository;

    @Autowired
    public AsignacionService(AsignacionRepository asignacionRepository) {
        this.asignacionRepository = asignacionRepository;
    }

    public boolean realizarAsignacion(int guiaId, String correo) throws AsignacionException {
        try {
            return asignacionRepository.
                    realizarAsignacion(guiaId, correo);
        } catch (AsignacionException e) {
            throw e;
        } catch (Exception e) {
            throw new AsignacionException("Error durante la asignación: " + e);
        }
    }
}
