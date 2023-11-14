package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.mappers.GuiaInfoMapper;
import co.edu.unbosque.syscourier.models.repositories.GuiaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona la información de guías.
 */
@Service
public class GuiaInfoService {

    /**
     * Atributo privado del mapper de la información de guías.
     */
    private final GuiaInfoMapper guiaInfoMapper;

    /**
     * Atributo privado del repositorio de información de las guias
     */
    private final GuiaInfoRepository guiaInfoRepository;

    /**
     * Atributo privado del servicio TipoService.
     */
    private final TipoService tipoService;

    /**
     * Constructor de la clase que maneja el mapper, el repositorio y el servicio de tipo.
     *
     * @param guiaInfoMapper     Mapeador de información de guías.
     * @param guiaInfoRepository Repositorio de información de guías.
     * @param tipoService        Servicio de tipo.
     */
    @Autowired
    public GuiaInfoService(GuiaInfoMapper guiaInfoMapper, GuiaInfoRepository guiaInfoRepository, TipoService tipoService) {
        this.guiaInfoMapper = guiaInfoMapper;
        this.guiaInfoRepository = guiaInfoRepository;
        this.tipoService = tipoService;
    }

    /**
     * Método que obtiene la información de una guía por su identificador.
     *
     * @param id Identificador de la guía.
     * @return DTO que representa la información de la guía o null si no se encuentra.
     */
    public GuiaInfoDTO getById(Integer id) {
        if (id <= 0) {
            return null;
        }
        return guiaInfoMapper.toGuiaInfoDTO(guiaInfoRepository.findByIdCustom(id).orElse(null), tipoService);
    }
}
