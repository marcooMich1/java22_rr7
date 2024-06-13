package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.Csoc;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CsocDao extends JpaRepository<Csoc, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE CSOC SET SALDO = 0", nativeQuery = true)
    void updateSaldo_0();

    @Modifying
    @Transactional
    @Query(value = "UPDATE A SET A.SALDO = B.MONT_MON_CONSOLIDADA FROM CSOC A "
            + "INNER JOIN (SELECT AFECTO, CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, SUM(MONT_MON_CONSOLIDADA) AS MONT_MON_CONSOLIDADA, "
            + "SUBCLAVE, CLAVE, NIV_FOND_PROP_ADMI_ " + "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes "
            + "GROUP BY CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, AFECTO, SUBCLAVE, CLAVE, NIV_FOND_PROP_ADMI_) B ON "
            + "CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = B.CTA_QTO_NIV_NVO_CAT AND A.MONEDA = B.TIPO_MONEDA "
            + "AND A.AFECTACION = B.AFECTO "
            + "AND A.NIVEL_FONDOS = B.NIV_FOND_PROP_ADMI_ AND  A.CLAVE_CAP_SOC = B.CLAVE AND A.SUB_CLAVE_CAP_SOC = B.SUBCLAVE ", nativeQuery = true)
    void updateSaldoConBalanza(@Param("anio") int anio, @Param("mes") int mes);

    @Modifying
    @Transactional
    @Query(value = "UPDATE CSOC SET SALDO = (SELECT SUM(VALORES) AS VALORES_6 "
            + "FROM CMER WHERE OPERACION = '0000' AND RAMO = '000' AND SUBRAMO = '000' AND SUBSUBRAMO = '0000' "
            + "AND (NIVEL1 = '620' OR NIVEL1 = '640') AND NIVEL2 = '00' AND NIVEL3 = '00' AND NIVEL4 = '00') "
            + "WHERE CONCAT(NIVEL1, NIVEL2, NIVEL3, NIVEL4) = '320050000' AND CLAVE_CAP_SOC = '200' AND SUB_CLAVE_CAP_SOC = '01'", nativeQuery = true)
    void updateSaldoConCmer6();

    @Modifying
    @Transactional
    @Query(value = "UPDATE CSOC SET SALDO = SALDO - (SELECT (SUM(VALORES)) AS VALORES_5 FROM CMER "
            + "WHERE OPERACION = '0000' AND RAMO = '000' AND SUBRAMO = '000' AND SUBSUBRAMO = '0000' "
            + "AND (NIVEL1 = '510' OR NIVEL1 = '520' OR NIVEL1 = '540' OR NIVEL1 = '560' OR NIVEL1 = '570') "
            + "AND NIVEL2 = '00' AND NIVEL3 = '00' AND NIVEL4 = '00')"
            + "WHERE CONCAT(NIVEL1, NIVEL2, NIVEL3, NIVEL4) = '320050000' AND CLAVE_CAP_SOC = '200' AND SUB_CLAVE_CAP_SOC = '01'", nativeQuery = true)
    void updateSaldoConCmer5();

    @Modifying
    @Transactional
    @Query(value = "UPDATE A SET A.VALORES = B.SALDO FROM CMBG A "
            + "INNER JOIN CSOC B ON CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) =  CONCAT(B.NIVEL1, B.NIVEL2, B.NIVEL3, B.NIVEL4) "
            + "AND A.CVEMONEDA = B.MONEDA "
            + "WHERE CONCAT(A.NIVEL1, A.NIVEL2, A.NIVEL3, A.NIVEL4) = '320050000'", nativeQuery = true)
    void updateCmbgConCsocCuenta();

}
