package co.edu.unbosque.syscourier.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CambioEstadoDTO {

    private int guiaId;
    private int codEstado;
    String motivo;
    String observaciones;
}
