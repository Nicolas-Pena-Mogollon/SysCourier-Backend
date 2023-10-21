package co.edu.unbosque.syscourier.DTOs;
/**
 * Imports of lombok
 */
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * Public class for dto error
 * @Data allows the use of db data and persistence
 * @AllArgsConstructor annotation automatically generates a constructor with a parameter for each field in your class
 **/
@Data
@AllArgsConstructor
public class ErrorDTO {
    /**
     * Private attribute of type String of name message
     **/
    private String message;

}
