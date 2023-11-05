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
