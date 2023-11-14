package co.edu.unbosque.syscourier.mappers;

import co.edu.unbosque.syscourier.DTOs.GuiaInfoDTO;
import co.edu.unbosque.syscourier.models.entities.GuiaInfo;
import co.edu.unbosque.syscourier.services.TipoService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

/**
 * Interfaz de mapeo para convertir entidades GuiaInfo a DTOs GuiaInfoDTO.
 */
@Component
@Mapper(componentModel = "spring")
public interface GuiaInfoMapper {

    /**
     * Convierte una entidad GuiaInfo a un DTO GuiaInfoDTO.
     *
     * @param guiaInfo    La entidad GuiaInfo a ser convertida.
     * @param tipoService Instancia de TipoService.
     * @return Un objeto GuiaInfoDTO mapeado.
     */
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


    /**
     * Método de mapeo para el campo 'producto'.
     *
     * @param productoId  ID del producto a ser mapeado.
     * @param tipoService Instancia de TipoService.
     * @return Una cadena que representa el producto mapeado.
     */
    @Named("mapProducto")
    default String mapProducto(Integer productoId, @Context TipoService tipoService) {
        return tipoService.getValorByID(productoId);
    }
}
