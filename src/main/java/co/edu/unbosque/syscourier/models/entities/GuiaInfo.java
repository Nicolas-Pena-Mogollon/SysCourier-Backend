package co.edu.unbosque.syscourier.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "guia_informacion")
public class GuiaInfo {
    @Id
    private Integer id;

    @Column(name = "producto")
    private Integer producto;

    @Column(name = "remitente_identificacion")
    private String remitenteIdentificacion;

    @Column(name = "remitente_nombre")
    private String remitenteNombre;

    @Column(name = "remitente_direccion")
    private String remitenteDireccion;

    @Column(name = "remitente_telefono")
    private String remitenteTelefono;

    @Column(name = "destinatario_identificacion")
    private String destinatarioIdentificacion;

    @Column(name = "destinatario_nombre")
    private String destinatarioNombre;

    @Column(name = "destinatario_direccion")
    private String destinatarioDireccion;

    @Column(name = "destinatario_telefono")
    private String destinatarioTelefono;

    @Column(name = "total_flete")
    private Double totalFlete;

}
