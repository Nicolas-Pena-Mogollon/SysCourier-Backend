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
@Entity(name = "guia_introduccion")
public class GuiaIntro {
    @Id
    private Integer id;
    @Column(name = "remitente_nombre")
    private String remitenteNombre;
    @Column(name = "destinatario_direccion")
    private String destinatarioDireccion;
}
