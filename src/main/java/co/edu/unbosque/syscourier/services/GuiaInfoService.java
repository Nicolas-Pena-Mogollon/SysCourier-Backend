package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.mappers.GuiaInfoMapper;
import co.edu.unbosque.syscourier.models.entities.GuiaInfo;
import co.edu.unbosque.syscourier.models.repositories.GuiaInfoRepository;
import co.edu.unbosque.syscourier.models.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuiaInfoService {

    @Autowired
    private GuiaInfoMapper guiaInfoMapper;

    @Autowired
    private GuiaInfoRepository guiaInfoRepository;

    @Autowired
    private TipoService tipoService;


    public GuiaInfoDTO getById(Integer id) {
        // Validar el ID antes de la consulta
        if (id <= 0) {
            // Manejo de ID no válido, podrías lanzar una excepción personalizada aquí
            // Ejemplo: throw new EntityNotFoundException("ID no válido");
            return null;
        }

        // Consultar la entidad por ID
        return guiaInfoMapper.toGuiaInfoDTO(guiaInfoRepository.findByIdCustom(id), tipoService);
    }
}
