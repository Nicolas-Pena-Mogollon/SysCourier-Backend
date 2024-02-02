package co.edu.unbosque.syscourier.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDTO {
    private String correo;
    private String password;
}