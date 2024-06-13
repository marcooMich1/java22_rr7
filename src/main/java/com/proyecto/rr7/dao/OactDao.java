package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Oact;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OactDao extends JpaRepository<Oact, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE OACT SET VALORES = 0", nativeQuery = true)
    void updateValores_0();

    @Modifying
    @Transactional
    @Query(value = "UPDATE A SET A.VALORES = B.MONT_MON_CONSOLIDADA FROM OACT A "
            + "INNER JOIN (SELECT CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUM(MONT_MON_CONSOLIDADA) AS MONT_MON_CONSOLIDADA, SUBCLAVE, "
            + "NIV_FOND_PROP_ADMI_, CLAVE, AFECTO " + "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes "
            + "GROUP BY CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUBCLAVE, NIV_FOND_PROP_ADMI_, CLAVE, AFECTO) B ON "
            + "CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = B.CTA_QTO_NIV_NVO_CAT AND A.MONEDA = B.TIPO_MONEDA AND A.AFECTACION = B.AFECTO "
            + "AND A.CVE_OTROS_ACTIVOS = B.CLAVE AND A.SUB_CVE_OTROS_ACTIVOS = B.SUBCLAVE AND "
            + "A.NIVEL_FONDOS = B.NIV_FOND_PROP_ADMI_", nativeQuery = true)
    void updateValoresConBalanza(@Param("anio") int anio, @Param("mes") int mes);

}
