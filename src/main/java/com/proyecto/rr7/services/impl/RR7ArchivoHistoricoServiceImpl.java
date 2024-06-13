package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.entities.CCArchivosRR7;
import com.proyecto.rr7.entities.Rr7ArchivoHistorico;
import com.proyecto.rr7.services.RR7ArchivoHistoricoService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class RR7ArchivoHistoricoServiceImpl implements RR7ArchivoHistoricoService {

    private static final Logger log = LogManager.getLogger(RR7ArchivoHistoricoServiceImpl.class);

    private JdbcTemplate jdcbTemplate;

    @Autowired
    public RR7ArchivoHistoricoServiceImpl(JdbcTemplate jdcbTemplate) {
        this.jdcbTemplate = jdcbTemplate;
    }

    @Override
    public List<CCArchivosRR7> consultarRR7(String anioMes) {
        List<CCArchivosRR7> lst = new ArrayList<>();
        CCArchivosRR7 archivo;

        String query = "SELECT CC.ID, CC.NOMBRESIMPLIFICADO, CC.NOMBREEXTENDIDO, INFOCARGADA = CASE " +
                "WHEN CC.ID = 3 THEN IIF((SELECT TOP 1 NIVEL1 FROM INVE WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 4 THEN IIF((SELECT TOP 1 NIVEL1 FROM INDE WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 5 THEN IIF((SELECT TOP 1 NIVEL1 FROM INMU WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 6 THEN IIF((SELECT TOP 1 NIVEL1 FROM CRED WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 7 THEN IIF((SELECT TOP 1 NIVEL1 FROM DEUD WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 8 THEN IIF((SELECT TOP 1 NIVEL1 FROM OINV WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 9 THEN IIF((SELECT TOP 1 NIVEL1 FROM IRRE WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 21 THEN IIF((SELECT TOP 1 NIVEL1 FROM BASE WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 22 THEN IIF((SELECT TOP 1 NIVEL1 FROM CCMP WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 23 THEN IIF((SELECT TOP 1 NIVEL1 FROM FOPA WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "WHEN CC.ID = 24 THEN IIF((SELECT TOP 1 NIVEL1 FROM FOND WHERE ANIOMES = '" + anioMes + "') IS NULL, 0, 1 ) " +
                "END FROM CCARCHIVOSRR7 CC WHERE ID < 25";

        List<Map<String, Object>> lstDatos = jdcbTemplate.queryForList(query);

        for (Iterator iterator = lstDatos.iterator(); iterator.hasNext(); ) {
            Map<String, Object> map = (Map<String, Object>) iterator.next();
            archivo = new CCArchivosRR7();
            archivo.setId(Integer.parseInt(map.get("ID").toString()));
            archivo.setNombreSimplificado(map.get("NOMBRESIMPLIFICADO") != null ? map.get("NOMBRESIMPLIFICADO").toString() : "");
            archivo.setNombreExtendido(map.get("NOMBREEXTENDIDO") != null ? map.get("NOMBREEXTENDIDO").toString() : "");
            archivo.setInfoCargada(map.get("INFOCARGADA") != null ? map.get("INFOCARGADA").toString() : "");
            lst.add(archivo);
        }

        return lst;
    }

    @Override
    public int getExistReporteHistorico(int anio, String mes, int idArchivo) {
        int existe = 0;

        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS EXISTEN_REGISTROS FROM RR7ARCHIVOHISTORICO WHERE ANIO = " +
                anio + " AND MES = " + mes + " AND IDARCHIVO = " + idArchivo;
        SqlRowSet rowSet = jdcbTemplate.queryForRowSet(query);

        while (rowSet.next()) {
            existe = rowSet.getInt("EXISTEN_REGISTROS");
        }

        return existe;
    }

    @Override
    public int existInfoBalanza(int anio, String mes) {
        int existen = 0;

        String query = " SELECT IIF (COUNT(*) >= 1, 1, 0) AS EXISTEN_REGISTROS FROM BALANZACONTABLE WHERE ANIO = " +
                anio + "  AND MES = '" + mes + "' ";

        log.info(query);
        SqlRowSet rowSet = jdcbTemplate.queryForRowSet(query);

        while (rowSet.next()) {
            existen = rowSet.getInt("EXISTEN_REGISTROS");
        }
        return existen;
    }

    @Override
    public Rr7ArchivoHistorico findByIdArchivo(int id, int anio, int mes, String archivo) {
        Rr7ArchivoHistorico result = null;
        log.info("Buscando (findByIdArchivo) registro de archivo en el HISTORICO: " + archivo);

        String query = "SELECT * from RR7ARCHIVOHISTORICO where IDARCHIVO = " + id + " and ANIO = " + anio
                + " AND MES = " + mes;
        List<Map<String, Object>> rowDat = jdcbTemplate.queryForList(query);
        if (!(rowDat.isEmpty())) {
            for (Iterator iterator = rowDat.iterator(); iterator.hasNext(); ) {
                Map<String, Object> map = (Map<String, Object>) iterator.next();
                result = new Rr7ArchivoHistorico();
                result.setId(Integer.parseInt(map.get("ID").toString()));
                result.setFechaRegistro(map.get("FECHAREGISTRO").toString());
                result.setBase64Rr7(map.get("BASE64RR7").toString());
                result.setIdArchivo(Integer.parseInt(map.get("IDARCHIVO").toString()));
            }
        }

        return result;
    }


}
