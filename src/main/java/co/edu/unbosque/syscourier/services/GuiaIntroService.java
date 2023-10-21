package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.mappers.GuiaIntroMapper;
import co.edu.unbosque.syscourier.models.repositories.GuiaIntroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuiaIntroService {

    @Autowired
    private GuiaIntroMapper guiaIntroMapper;

    @Autowired
    private GuiaIntroRepository guiaIntroRepository;

    public GuiaIntroDTO getById(Integer id) {
        // Validar el ID antes de la consulta
        if (id <= 0) {
            // Manejo de ID no válido, podrías lanzar una excepción personalizada aquí
            // Ejemplo: throw new EntityNotFoundException("ID no válido");
            return null;
        }

        // Consultar la entidad por ID
        return guiaIntroMapper.toGuiaIntroDTO(guiaIntroRepository.findById(id).orElse(null));
    }
}
