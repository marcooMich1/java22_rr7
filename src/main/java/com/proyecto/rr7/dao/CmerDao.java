package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Cmer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CmerDao extends JpaRepository<Cmer, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE CMER SET VALORES = (SELECT SUM(VALORES) FROM CMER  WHERE  NIVEL1 = '700' AND NIVEL2 = '00' AND NIVEL3 ='00' AND NIVEL4 = '00' AND MONEDA ='10' AND OPERACION = '3000'AND RAMO ='030' AND SUBRAMO='332' AND SUBSUBRAMO <>'0000' GROUP BY NIVEL1, NIVEL2, NIVEL3, NIVEL4, MONEDA, OPERACION, RAMO, SUBRAMO) WHERE  NIVEL1 = '700' AND NIVEL2 = '00' AND NIVEL3 ='00' AND NIVEL4 = '00' AND MONEDA ='10' AND OPERACION = '3000'AND RAMO ='030' AND SUBRAMO='332' AND SUBSUBRAMO ='0000'", nativeQuery = true)
    int update_700_1();

}
