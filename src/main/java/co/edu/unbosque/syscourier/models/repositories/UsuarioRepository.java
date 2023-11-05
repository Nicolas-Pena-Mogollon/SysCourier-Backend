package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM usuario u WHERE u.correo = :correo and u.idRol = (SELECT r.idRol from rol r WHERE r.nombre = :rol)")
    Optional<Usuario> findByCorreo(@Param("correo") String correo, @Param("rol") String rol);
}
