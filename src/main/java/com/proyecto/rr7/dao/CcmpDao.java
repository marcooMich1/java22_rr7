package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Ccmp;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CcmpDao extends JpaRepository<Ccmp, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM CCMP WHERE ANIOMES = :anioMes ORDER BY NIVEL1, NIVEL2, NIVEL3, NIVEL4, MONEDA"
            , nativeQuery = true)
    List<Ccmp> findByAnioMes(@Param("anioMes") String anioMes);

}
