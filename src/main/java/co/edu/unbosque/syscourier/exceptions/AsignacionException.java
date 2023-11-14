package co.edu.unbosque.syscourier.exceptions;

/**
 * Excepción en caso de error durante el proceso de asignación de guias
 */
public class AsignacionException extends Exception {

    /**
     * Constructor que crea una excepción recibiendo un mensaje como parametro.
     *
     * @param msj Mensaje descriptivo de la excepción.
     */
    public AsignacionException(String msj) {
        super(msj);
    }
}
