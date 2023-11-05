package co.edu.unbosque.syscourier.mappers;

import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.models.entities.GuiaInfo;
import co.edu.unbosque.syscourier.services.TipoService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface GuiaInfoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "producto", target = "producto", qualifiedByName = "mapProducto")
    @Mapping(source = "remitenteIdentificacion", target = "remitenteIdentificacion")
    @Mapping(source = "remitenteNombre", target = "remitenteNombre")
    @Mapping(source = "remitenteDireccion", target = "remitenteDireccion")
    @Mapping(source = "remitenteTelefono", target = "remitenteTelefono")
    @Mapping(source = "destinatarioIdentificacion", target = "destinatarioIdentificacion")
    @Mapping(source = "destinatarioNombre", target = "destinatarioNombre")
    @Mapping(source = "destinatarioDireccion", target = "destinatarioDireccion")
    @Mapping(source = "destinatarioTelefono", target = "destinatarioTelefono")
    @Mapping(source = "totalFlete", target = "totalFlete")
    GuiaInfoDTO toGuiaInfoDTO(GuiaInfo guiaInfo, @Context TipoService tipoService);

    @Named("mapProducto")
    default String mapProducto(Integer productoId, @Context TipoService tipoService) {
        return tipoService.getValorByID(productoId);
    }
}
