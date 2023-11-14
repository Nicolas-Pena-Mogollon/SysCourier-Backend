package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.exceptions.AsignacionException;
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
 * Gestión de  las asignaciones de guías en la base de datos.
 */
@Repository
public class AsignacionRepository {

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
    public AsignacionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Método que realiza la asignación de una guía a un mensajero.
     *
     * @param guiaId Identificador de la guía a asignar.
     * @param correo Correo electrónico del mensajero al que se asignará la guía.
     * @throws AsignacionException Excepción lanzada en caso de errores durante la asignación.
     */
    @Transactional
    public void realizarAsignacion(int guiaId, String correo) throws AsignacionException {
        try {
            Connection connection = dataSource.getConnection();
            CallableStatement statement = connection.prepareCall("{call realizarAsignacion(?, ?, ?)}");
            statement.setString(1, correo);
            statement.setInt(2, guiaId);
            statement.registerOutParameter(3, Types.INTEGER);  // Parámetro de salida para el resultado
            statement.execute();
            int resultado = statement.getInt(3);
            statement.close();

            if (resultado == 0) {
                throw new AsignacionException("No se ha logrado realizar la asignación");
            }
        } catch (SQLException e) {
            throw new AsignacionException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AsignacionException("Error en el acceso a datos durante la asignación");
        } catch (Exception e) {
            throw new AsignacionException("Error durante la asignación: " + e.getMessage());
        }
    }
}
