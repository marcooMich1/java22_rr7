package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Cmbg;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CmbgDao extends JpaRepository<Cmbg, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update CMBG set valores = (SELECT sum(VALORES) FROM CMBG WHERE NIVEL1 = '110' AND NIVEL2 = '04' and nivel3 = '01' GROUP BY NIVEL3) where NIVEL1 = '110' and CVEMONEDA = '00' AND NIVEL2 = '04' and NIVEL3 <> '00' and NIVEL3 = '01' ", nativeQuery = true)
    int update_100_1();

}
