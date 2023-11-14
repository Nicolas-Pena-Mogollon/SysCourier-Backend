package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio que maneja el acceso a datos de los usuarios.
 *
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Consulta para buscar un usuario por correo y rol.
     *
     * @param correo Correo del usuario.
     * @param rol    Rol del usuario.
     * @return Un Optional que puede contener un Usuario que coincida con el correo y rol especificados.
     */
    @Query("SELECT u FROM usuario u WHERE u.correo = :correo and u.idRol = (SELECT r.idRol from rol r WHERE r.nombre = :rol)")
    Optional<Usuario> findByCorreo(@Param("correo") String correo, @Param("rol") String rol);
}
