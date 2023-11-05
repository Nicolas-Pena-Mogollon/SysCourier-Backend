package co.edu.unbosque.syscourier.models.repositories;

import co.edu.unbosque.syscourier.models.entities.GuiaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuiaInfoRepository extends JpaRepository<GuiaInfo, Integer> {

    @Query("select g from guia_informacion g where g.id = :id")
    GuiaInfo findByIdCustom(@Param("id") Integer id);
}
