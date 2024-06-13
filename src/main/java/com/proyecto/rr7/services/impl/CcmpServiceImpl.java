package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CcmpDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Ccmp;
import com.proyecto.rr7.services.CcmpService;
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
public class CcmpServiceImpl implements CcmpService {

    private static final Logger log = LogManager.getLogger(CcmpServiceImpl.class);

    CcmpDao ccmpDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CcmpServiceImpl(CcmpDao ccmpDao, JdbcTemplate jdbcTemplate) {
        this.ccmpDao = ccmpDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    double capitalCont = 0;
    double ixvInmNeto = 0;
    double utilValCapital = 0;
    double incCapInm = 0;
    double capitalPagad = 0;
    double ixvInmNetoRt = 0;
    double ixvInmNetoSus = 0;
    double cmp = 0;
    double cmpExigido = 0;
    double sobrante = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4).concat("|")
            .concat(moneda).concat("|").concat(String.valueOf(capitalCont)).concat("|")
            .concat(String.valueOf(ixvInmNeto)).concat("|").concat(String.valueOf(utilValCapital)).concat("|")
            .concat(String.valueOf(incCapInm)).concat("|").concat(String.valueOf(capitalPagad)).concat("|")
            .concat(String.valueOf(ixvInmNetoRt)).concat("|").concat(String.valueOf(ixvInmNetoSus)).concat("|")
            .concat(String.valueOf(cmp)).concat("|").concat(String.valueOf(cmpExigido)).concat("|")
            .concat(String.valueOf(sobrante)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerCcmp(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxtCcmp(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxtCcmp(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Ccmp> listCcmp = ccmpDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(listCcmp.isEmpty())) {
            for (Ccmp lista : listCcmp) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                capitalCont = lista.getCapitalContable();
                ixvInmNeto = lista.getIxvInmNeto();
                utilValCapital = lista.getUtilValCapital();
                incCapInm = lista.getIncCapInm();
                capitalPagad = lista.getCapitalPagado();
                ixvInmNetoRt = lista.getIxvInmNetoRt();
                ixvInmNetoSus = lista.getIxvInmNetoRtSuscept();
                cmp = lista.getCmp();
                cmpExigido = lista.getCmpExigido();
                sobrante = lista.getSobrante();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(moneda).concat("|").concat(format.format(capitalCont)).concat("|")
                        .concat(format.format(ixvInmNeto)).concat("|").concat(format.format(utilValCapital))
                        .concat("|").concat(format.format(incCapInm)).concat("|")
                        .concat(format.format(capitalPagad)).concat("|").concat(format.format(ixvInmNetoRt))
                        .concat("|").concat(format.format(ixvInmNetoSus)).concat("|").concat(format.format(cmp))
                        .concat("|").concat(format.format(cmpExigido)).concat("|").concat(format.format(sobrante))
                        .concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio CCMP");
        }

        if (!infoWrite) {
            log.info("File CCMP is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo CCMP creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoCcmp(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM CCMP WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
