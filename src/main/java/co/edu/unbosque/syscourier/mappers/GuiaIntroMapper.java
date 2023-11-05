package co.edu.unbosque.syscourier.mappers;

import co.edu.unbosque.syscourier.DTOs.GuiaIntroDTO;
import co.edu.unbosque.syscourier.models.entities.GuiaIntro;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper(componentModel = "spring")
public interface GuiaIntroMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "remitenteNombre", target = "remitenteNombre")
    @Mapping(source = "destinatarioDireccion", target = "destinatarioDireccion")
    GuiaIntroDTO toGuiaIntroDTO (GuiaIntro guiaIntro);
    /**
     * Advises the code generator to apply all the Mapping s from an inverse mapping method to the annotated method as well
     **/
    @InheritInverseConfiguration
    GuiaIntro toGuiaIntro (GuiaIntroDTO guiaIntroDTO);

    List<GuiaIntroDTO> toGuiaIntroDTOs(List<GuiaIntro> guiaIntroList);

}
