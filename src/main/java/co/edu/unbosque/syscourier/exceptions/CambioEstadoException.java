package co.edu.unbosque.syscourier.exceptions;

/**
 * Excepción en caso de error durante el proceso de cambio de estado
 */
public class CambioEstadoException extends Exception {

    /**
     * Constructor que crea una excepción recibiendo un mensaje como parametro.
     *
     * @param msj Mensaje descriptivo de la excepción.
     */
    public CambioEstadoException(String msj) {
        super(msj);
    }
}
