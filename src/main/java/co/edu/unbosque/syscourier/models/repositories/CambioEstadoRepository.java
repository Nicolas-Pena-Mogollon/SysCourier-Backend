package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.exceptions.CambioEstadoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Gestión de los cambios de estado de guías en la base de datos.
 *
 */
@Repository
public class CambioEstadoRepository {

    /**
     * Atributo privado que muestra el origen de los datos
     */
    private final DataSource dataSource;

    /**
     * Constructor de la clase que maneja el origen de datos.
     *
     * @param dataSource Origen de datos.
     */
    @Autowired
    public CambioEstadoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Método que realiza el cambio de estado de una guía.
     *
     * @param correo        Correo electrónico del usuario que realiza el cambio de estado.
     * @param guiaId        Identificador de la guía a la que se cambiará el estado.
     * @param codEstado     Nuevo código de estado de la guía.
     * @param motivo        Motivo asociado al cambio de estado.
     * @param observaciones Observaciones adicionales al cambio de estado.
     * @return true si el cambio de estado se realizó correctamente, false en caso contrario.
     * @throws CambioEstadoException Excepción lanzada en caso de errores durante el cambio de estado.
     */
    @Transactional
    public boolean cambiarEstado(String correo, int guiaId, int codEstado, String motivo, String observaciones) throws CambioEstadoException {
        try {
            Connection connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall("{call cambiarEstado(?, ?, ?, ?, ?, ?)}");
            statement.setString(1, correo);
            statement.setInt(2, guiaId);
            statement.setInt(3, codEstado);
            statement.setString(4, motivo);
            statement.setString(5, observaciones);
            statement.registerOutParameter(6, Types.INTEGER);  // Parámetro de salida para el resultado
            statement.execute();
            int resultado = statement.getInt(6);
            statement.close();

            if (resultado == 0) {
                throw new CambioEstadoException("No se ha logrado cambiar el estado");
            }
        } catch (SQLException e) {
            throw new CambioEstadoException(e.getMessage());
        } catch (DataAccessException e) {
            throw new CambioEstadoException("Error en el acceso a datos durante el cambio de estado");
        } catch (Exception e) {
            throw new CambioEstadoException("Error durante el cambio de estado: " + e.getMessage());
        }
        return true;
    }
}
