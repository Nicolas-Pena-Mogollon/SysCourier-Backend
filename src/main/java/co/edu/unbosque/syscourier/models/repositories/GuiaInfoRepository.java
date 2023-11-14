package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.GuiaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio que maneja el acceso a datos de las guías de información.
 */

@Repository
public interface GuiaInfoRepository extends JpaRepository<GuiaInfo, Integer> {


    /**
     * Consulta para buscar una guía de información por su ID.
     *
     * @param id ID de la guía de información.
     * @return Objeto Optional que contiene la guía de información si se encuentra, o vacío si no.
     */
    @Query("select g from guia_informacion g where g.id = :id")
    Optional<GuiaInfo> findByIdCustom(@Param("id") Integer id);
}
