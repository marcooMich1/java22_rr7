package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Inve;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InveDao extends JpaRepository<Inve, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM INVE WHERE ANIOMES = :anioMes ORDER BY CUENTABANCARIA,nivel1,nivel2,nivel3,nivel4,moneda "
            , nativeQuery = true)
    List<Inve> findByAnioMes(@Param("anioMes") String anioMes);

}
