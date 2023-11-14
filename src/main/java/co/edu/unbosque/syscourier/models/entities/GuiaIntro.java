package co.edu.unbosque.syscourier.models.entities;



import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa la entidad GuiaIntro en la base de datos.
 *
 * @Data permite el uso de la información y la persistencia
 * @AllArgsConstructor genera los constructores y parametros para la clase
 * @NoArgsConstructor genera constructores sin argumentos
 * @Getter genera los getters de la clase
 * @Setter genera los setters de la clase
 *
 **/
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
