package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Cord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CordDao extends JpaRepository<Cord, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE CORD SET SALDO = 0", nativeQuery = true)
    void updateSaldo_0();

    @Modifying
    @Transactional
    @Query(value = "UPDATE A SET A.SALDO = B.MONT_MON_CONSOLIDADA FROM CORD A "
            + "INNER JOIN (SELECT CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUM(MONT_MON_CONSOLIDADA) AS MONT_MON_CONSOLIDADA, SUBCLAVE, CLAVE "
            + "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes AND CTA_CAT_ANT LIKE '7%' "
            + "GROUP BY CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUBCLAVE, CLAVE) B ON "
            + "CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = B.CTA_QTO_NIV_NVO_CAT AND A.MONEDA = B.TIPO_MONEDA AND "
            + "A.CLAVE_CTAS_ORDEN = B.CLAVE AND A.SUB_CLAVE_CTAS_ORDEN = B.SUBCLAVE", nativeQuery = true)
    void updateSaldoConBalanza(@Param("anio") int anio, @Param("mes") int mes);

}
