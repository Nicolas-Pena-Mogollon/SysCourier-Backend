package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.models.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que maneja los tipos de guías.
 *
 */
@Service
public class TipoService {
    /**
     * Atributo privado, repositorio de tipos de guías.
     */
    @Autowired
    private TipoRepository tipoRepository;

    /**
     * Método que obtiene el valor de un tipo de guía por su ID.
     *
     * @param id ID del tipo de guía.
     * @return Valor del tipo de guía.
     */
    public String getValorByID(int id) {
        return tipoRepository.getValorByID(id);
    }
}
