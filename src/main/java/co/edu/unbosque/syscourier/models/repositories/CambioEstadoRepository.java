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

@Repository
public class CambioEstadoRepository {

    private final DataSource dataSource;

    @Autowired
    public CambioEstadoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

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
