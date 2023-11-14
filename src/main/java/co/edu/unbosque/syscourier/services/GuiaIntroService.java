package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.mappers.GuiaIntroMapper;
import co.edu.unbosque.syscourier.models.repositories.GuiaIntroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Servicio que gestiona la información de  introducción de las guías.
 */
@Service
public class GuiaIntroService {

    /**
     * Atributo privado del mapper de la información de  introducción de guías.
     */
    private final GuiaIntroMapper guiaIntroMapper;

    private final GuiaIntroRepository guiaIntroRepository;

    /**
     * Constructor de la clase que maneja el mapper y el repositorio.
     *
     * @param guiaIntroMapper     mapper de información introductoria de guías.
     * @param guiaIntroRepository Repositorio de información introductoria de guías.
     */
    @Autowired
    public GuiaIntroService(GuiaIntroMapper guiaIntroMapper, GuiaIntroRepository guiaIntroRepository) {
        this.guiaIntroMapper = guiaIntroMapper;
        this.guiaIntroRepository = guiaIntroRepository;
    }

    /**
     * Método que obtiene todas las guías introductorias de un usuario según su estado.
     *
     * @param correo       Correo del usuario.
     * @param codigoEstado Código del estado de la guía.
     * @return Lista de DTOs que representan la información introductoria de las guías.
     */
    public List<GuiaIntroDTO> obtenerTodoPorUsuarioYEstado(String correo, String codigoEstado) {
        // 6 -> ENTREGA, 7 -> DEVOLUCIÓN
        if (Objects.equals(codigoEstado, "6") || Objects.equals(codigoEstado, "7")) {
            return guiaIntroMapper.toGuiaIntroDTOs(guiaIntroRepository.findGuiasIntroByCorreoAndCodigoEstadoconFecha(correo, codigoEstado).orElse(null));
        }
        return guiaIntroMapper.toGuiaIntroDTOs(guiaIntroRepository.findGuiasIntroByCorreoAndCodigoEstado(correo, codigoEstado).orElse(null));
    }
}
