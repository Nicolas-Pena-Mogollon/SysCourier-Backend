package co.edu.unbosque.syscourier.mappers;

import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.models.entities.GuiaIntro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Interfaz de mapeo para convertir entidades GuiaIntro a DTOs GuiaIntroDTO.
 */
@Component
@Mapper(componentModel = "spring")
public interface GuiaIntroMapper {

    /**
     * Convierte una entidad GuiaIntro a un DTO GuiaIntroDTO.
     *
     * @param guiaIntro La entidad GuiaIntro a ser convertida.
     * @return Un objeto GuiaIntroDTO mapeado.
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "remitenteNombre", target = "remitenteNombre")
    @Mapping(source = "destinatarioDireccion", target = "destinatarioDireccion")
    GuiaIntroDTO toGuiaIntroDTO (GuiaIntro guiaIntro);

    /**
     * Advises the code generator to apply all the Mapping s from an inverse mapping method to the annotated method as well
     **/
    @InheritInverseConfiguration
    GuiaIntro toGuiaIntro (GuiaIntroDTO guiaIntroDTO);

    /**
     * Convierte una lista de entidades GuiaIntro a una lista de DTOs GuiaIntroDTO.
     *
     * @param guiaIntroList Lista de entidades GuiaIntro.
     * @return Lista de DTOs GuiaIntroDTO mapeados.
     */
    List<GuiaIntroDTO> toGuiaIntroDTOs(List<GuiaIntro> guiaIntroList);

}
