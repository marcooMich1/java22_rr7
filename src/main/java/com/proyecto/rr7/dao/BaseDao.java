package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Base;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDao extends JpaRepository<Base, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM BASE WHERE ANIOMES = :anioMes ORDER BY NIVEL1, NIVEL2, NIVEL3, NIVEL4, MONEDA",
            nativeQuery = true)
    List<Base> findByAnioMes(@Param("anioMes") String anioMes);

}
