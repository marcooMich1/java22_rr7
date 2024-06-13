package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Acre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcreDao extends JpaRepository<Acre, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE ACRE SET SALDO = 0", nativeQuery = true)
    void updateValores_0();

    @Transactional
    @Modifying
    @Query(value = "UPDATE A SET A.SALDO = B.MONT_MON_CONSOLIDADA FROM ACRE A INNER JOIN " +
            "(SELECT CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUM(MONT_MON_CONSOLIDADA) AS MONT_MON_CONSOLIDADA, SUBCLAVE " +
            "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes GROUP BY CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, " +
            "SUBCLAVE) B ON CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = B.CTA_QTO_NIV_NVO_CAT AND A.MONEDA = " +
            "B.TIPO_MONEDA AND A.SUB_CLAVE_ACRE = B.SUBCLAVE", nativeQuery = true)
    void updateValoresConBalanza(@Param("anio") int anio, @Param("mes") int mes);

}
