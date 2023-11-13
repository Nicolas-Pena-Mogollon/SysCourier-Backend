package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.GuiaIntro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuiaIntroRepository extends JpaRepository<GuiaIntro, Integer> {


    @Query(nativeQuery = true,
            value = "SELECT gi.* " +
                    "FROM guia_introduccion gi " +
                    "INNER JOIN guia g ON gi.id = g.id " +
                    "INNER JOIN guia_estado ge ON g.id = ge.guia " +
                    "INNER JOIN guia_estado_usuario geu ON ge.id = geu.guia_estado_id " +
                    "INNER JOIN usuario u ON u.id_usuario = geu.usuario_id " +
                    "WHERE u.correo = :correo " +
                    "AND ge.estado = :codigoEstado")
    Optional<List<GuiaIntro>> findGuiasIntroByCorreoAndCodigoEstado(
            @Param("correo") String correo,
            @Param("codigoEstado") String codigoEstado);

    @Query(nativeQuery = true,
            value = "SELECT gi.* " +
                    "FROM guia_introduccion gi " +
                    "INNER JOIN guia g ON gi.id = g.id " +
                    "INNER JOIN guia_estado ge ON g.id = ge.guia " +
                    "INNER JOIN guia_estado_usuario geu ON ge.id = geu.guia_estado_id " +
                    "INNER JOIN usuario u ON u.id_usuario = geu.usuario_id " +
                    "WHERE u.correo = :correo " +
                    "AND ge.estado = :codigoEstado " +
                    "AND ge.fecha BETWEEN DATE_SUB(CURDATE(), INTERVAL 2 DAY) AND CURDATE()")
    Optional<List<GuiaIntro>> findGuiasIntroByCorreoAndCodigoEstadoconFecha(
            @Param("correo") String correo,
            @Param("codigoEstado") String codigoEstado);
}
