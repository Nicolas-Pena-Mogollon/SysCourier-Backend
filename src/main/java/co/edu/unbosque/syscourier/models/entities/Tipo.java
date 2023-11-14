package co.edu.unbosque.syscourier.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa la entidad Tipo en la base de datos.
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
@Entity(name = "tipo")
public class Tipo {
    @Id
    private Integer id;

    @Column(name = "idr")
    private Integer idr;

    @Column(name = "valor")
    private String valor;

    @Column(name = "valor1")
    private String valor1;

    @Column(name = "valor2")
    private String valor2;
}
