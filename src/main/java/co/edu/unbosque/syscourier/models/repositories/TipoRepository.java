package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que maneja el acceso a datos de los tipos de guias.
 *
 */
@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {

    /**
     * Consulta para obtener el valor de un tipo por su ID.
     *
     * @param id ID del tipo.
     * @return Valor del tipo correspondiente al ID especificado.
     */
    @Query("SELECT t.valor FROM tipo t WHERE t.id = :id")
    String getValorByID(Integer id);
}
