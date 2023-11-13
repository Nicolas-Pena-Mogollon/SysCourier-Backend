package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.mappers.GuiaIntroMapper;
import co.edu.unbosque.syscourier.models.repositories.GuiaIntroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class GuiaIntroService {

    private final GuiaIntroMapper guiaIntroMapper;

    private final GuiaIntroRepository guiaIntroRepository;

    @Autowired
    public GuiaIntroService(GuiaIntroMapper guiaIntroMapper, GuiaIntroRepository guiaIntroRepository) {
        this.guiaIntroMapper = guiaIntroMapper;
        this.guiaIntroRepository = guiaIntroRepository;
    }

    public List<GuiaIntroDTO> obtenerTodoPorUsuarioYEstado(String correo, String codigoEstado) {
        // 6 -> ENTREGA, 7 -> DEVOLUCIÓN
        if (Objects.equals(codigoEstado, "6") || Objects.equals(codigoEstado, "7")) {
            return guiaIntroMapper.toGuiaIntroDTOs(guiaIntroRepository.findGuiasIntroByCorreoAndCodigoEstadoconFecha(correo, codigoEstado).orElse(null));
        }
        return guiaIntroMapper.toGuiaIntroDTOs(guiaIntroRepository.findGuiasIntroByCorreoAndCodigoEstado(correo, codigoEstado).orElse(null));
    }
}
