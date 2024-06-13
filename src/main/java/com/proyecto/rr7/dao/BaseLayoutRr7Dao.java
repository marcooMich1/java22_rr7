package com.proyecto.rr7.dao;

import com.proyecto.rr7.entities.BaseLayoutRr7;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseLayoutRr7Dao extends JpaRepository<BaseLayoutRr7, Integer> {

    @Transactional
    @Query(value = "SELECT IDX, CTA_QTO_NIV_NVO_CAT, TIPO_MONEDA, CTA_CAT_ANT, SUB_CTA_CAT_ANT, RAMO, SUBRAMO, " +
            "SUBSUBRAMO, AFECTO, NIV_FOND_PROP_ADMI_, MONT_MON_CONSOLIDADA, OPERACION, ANIO, MES, CLAVE, SUBCLAVE " +
            "FROM BASE_LAYOUT_RR7 WHERE ANIO = :anio AND MES = :mes ", nativeQuery = true)
    List<BaseLayoutRr7> getInfoBaseLayoutAnioMes(@Param("anio") int anio, @Param("mes") int mes);

}
