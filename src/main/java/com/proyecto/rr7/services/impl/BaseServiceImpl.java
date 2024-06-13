package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.BaseDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Base;
import com.proyecto.rr7.services.BaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class BaseServiceImpl implements BaseService {

    private static final Logger log = LogManager.getLogger(BaseServiceImpl.class);

    BaseDao baseDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public BaseServiceImpl(BaseDao baseDao, JdbcTemplate jdbcTemplate) {
        this.baseDao = baseDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String operacion = "0";
    String cveRamo = "0";
    String cveSubram = "0";
    String cveSubsubramo = "0";
    int moneda = 0;
    double rsvaTot = 0;
    double rsvaRet = 0;
    double rsvaCed = 0;
    double porCp = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4).concat("|").concat(String.valueOf(moneda)).concat("|").concat(operacion).concat("|").concat(cveRamo).concat("|").concat(cveSubram).concat("|").concat(cveSubsubramo).concat("|").concat(String.valueOf(rsvaTot)).concat("|").concat(String.valueOf(rsvaRet)).concat("|").concat(String.valueOf(rsvaCed)).concat("|").concat(String.valueOf(porCp)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerBase(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Base> listaBase = baseDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;

        if (!(listaBase.isEmpty())) {
            for (Base lista : listaBase) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                operacion = lista.getOperacion();
                cveRamo = lista.getRamo();
                cveSubram = lista.getSubramo();
                cveSubsubramo = lista.getSubsubramo();
                rsvaTot = lista.getRsvaTot();
                rsvaRet = lista.getRsvaRet();
                rsvaCed = lista.getRsvaCed();
                porCp = lista.getPorCp();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4).concat("|").concat(String.valueOf(moneda)).concat("|").concat(operacion).concat("|").concat(cveRamo).concat("|").concat(cveSubram).concat("|").concat(cveSubsubramo).concat("|").concat(format.format(rsvaTot)).concat("|").concat(format.format(rsvaRet)).concat("|").concat(format.format(rsvaCed)).concat("|").concat(format.format(porCp)).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("nulo o vacio BASE");
        }

        if (!infoWrite) {
            log.info("File BASE is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo BASE creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoBase(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM BASE WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
