package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.mappers.GuiaInfoMapper;
import co.edu.unbosque.syscourier.models.repositories.GuiaInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuiaInfoService {

    private final GuiaInfoMapper guiaInfoMapper;

    private final GuiaInfoRepository guiaInfoRepository;

    private final TipoService tipoService;

    @Autowired
    public GuiaInfoService(GuiaInfoMapper guiaInfoMapper, GuiaInfoRepository guiaInfoRepository, TipoService tipoService) {
        this.guiaInfoMapper = guiaInfoMapper;
        this.guiaInfoRepository = guiaInfoRepository;
        this.tipoService = tipoService;
    }


    public GuiaInfoDTO getById(Integer id) {
        if (id <= 0) {
            return null;
        }
        return guiaInfoMapper.toGuiaInfoDTO(guiaInfoRepository.findByIdCustom(id).orElse(null), tipoService);
    }
}
