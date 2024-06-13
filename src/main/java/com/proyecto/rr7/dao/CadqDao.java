package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Cadq;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CadqDao extends JpaRepository<Cadq, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE CADQ SET SALDO = 0", nativeQuery = true)
    void updateSaldo_0();

    @Modifying
    @Transactional
    @Query(value = "UPDATE A SET A.SALDO = B.MONT_MON_CONSOLIDADA FROM CADQ A INNER JOIN "
            + "(SELECT CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, OPERACION, RIGHT('000' + CAST(RAMO AS VARCHAR), 3) AS RAMO, "
            + "SUBRAMO, SUBSUBRAMO, SUM(MONT_MON_CONSOLIDADA) AS MONT_MON_CONSOLIDADA, CLAVE, SUBCLAVE "
            + "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes GROUP BY CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, "
            + "OPERACION, RAMO, SUBRAMO, SUBSUBRAMO, CLAVE, SUBCLAVE) B ON "
            + "CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = B.CTA_QTO_NIV_NVO_CAT AND A.MONEDA = B.TIPO_MONEDA AND "
            + "A.OPERACION = B.OPERACION AND A.RAMO = B.RAMO AND A.SUBRAMO = B.SUBRAMO AND "
            + "A.SUBSUBRAMO = B.SUBSUBRAMO AND A.CLAVE_CTO_ADQ = B.CLAVE AND A.SUB_CLAVE_CTO_ADQ = B.SUBCLAVE", nativeQuery = true)
    void updateSaldoConBalanza(@Param("anio") int anio, @Param("mes") int mes);

}
