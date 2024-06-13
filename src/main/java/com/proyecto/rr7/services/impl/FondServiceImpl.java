package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.FondDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Fond;
import com.proyecto.rr7.services.FondService;
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
public class FondServiceImpl implements FondService {

    private static final Logger log = LogManager.getLogger(FondServiceImpl.class);

    FondDao fondDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public FondServiceImpl(FondDao fondDao, JdbcTemplate jdbcTemplate) {
        this.fondDao = fondDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String cveEmisorFond = "0";
    String serieFond = "0";
    String tipoValorFo = "0";
    String cveEmisorInst = "0";
    String serieInst = "0";
    String tipoValorInst = "0";
    String clasificacion = "0";
    String afectacion = "0";
    String isin = "0";
    String parametriz = "0";
    String descripc = "0";
    String liquidez = "0";
    String moneda = "0";
    String fchEmiInst = "0";
    String calif = "0";
    String nivelFondos = "0";
    double consecutivoInve = 0;
    double consecutivoInst = 0;
    double porcParticInst = 0;
    double ctoAdqInst = 0;
    double valCotInst = 0;
    double incDecValua = 0;
    double mdaBase = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(String.valueOf(consecutivoInve)).concat("|").concat(cveEmisorFond)
            .concat("|").concat(serieFond).concat("|").concat(tipoValorFo).concat("|").concat(cveEmisorInst)
            .concat("|").concat(serieInst).concat("|").concat(tipoValorInst).concat("|")
            .concat(clasificacion).concat("|").concat(afectacion).concat("|").concat(isin).concat("|")
            .concat(parametriz).concat("|").concat(String.valueOf(consecutivoInst)).concat("|").concat(descripc)
            .concat("|").concat(liquidez).concat("|").concat(moneda).concat("|").concat(fchEmiInst)
            .concat("|").concat(String.valueOf(porcParticInst)).concat("|").concat(String.valueOf(ctoAdqInst))
            .concat("|").concat(String.valueOf(valCotInst)).concat("|").concat(String.valueOf(incDecValua))
            .concat("|").concat(calif).concat("|").concat(String.valueOf(mdaBase)).concat("|")
            .concat(nivelFondos).concat("|;");

    @Override
    public ArchivoRespuesta obtenerFond(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Fond> list = fondDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Fond lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                consecutivoInve = lista.getConsecutivoInve();
                cveEmisorFond = lista.getCveEmisorFond();
                serieFond = lista.getSerieFond();
                tipoValorFo = lista.getTipoValorFo();
                cveEmisorInst = lista.getCveEmisorInst();
                serieInst = lista.getSerieInst();
                tipoValorInst = lista.getTipoValorInst();
                clasificacion = lista.getClasificacion();
                afectacion = lista.getAfectacion();
                isin = lista.getIsin();
                parametriz = lista.getParametriz();
                consecutivoInst = lista.getConsecutivoInst();
                descripc = lista.getDescripc();
                liquidez = lista.getLiquidez();
                moneda = lista.getMoneda();
                fchEmiInst = lista.getFchEmiInst();
                porcParticInst = lista.getPorcParticInst();
                ctoAdqInst = lista.getCtoAdqInst();
                valCotInst = lista.getValCotInst();
                incDecValua = lista.getIncDecValua();
                calif = lista.getCalif();
                mdaBase = lista.getMdaBase();
                nivelFondos = lista.getNivelFondos();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(String.valueOf(consecutivoInve)).concat("|").concat(cveEmisorFond)
                        .concat("|").concat(serieFond).concat("|").concat(tipoValorFo).concat("|")
                        .concat(cveEmisorInst).concat("|").concat(serieInst).concat("|").concat(tipoValorInst)
                        .concat("|").concat(clasificacion).concat("|").concat(afectacion).concat("|").concat(isin)
                        .concat("|").concat(parametriz).concat("|").concat(String.valueOf(consecutivoInst))
                        .concat("|").concat(descripc).concat("|").concat(liquidez).concat("|").concat(moneda)
                        .concat("|").concat(fchEmiInst).concat("|").concat(String.valueOf(porcParticInst))
                        .concat("|").concat(format.format(ctoAdqInst)).concat("|")
                        .concat(format.format(valCotInst)).concat("|").concat(format.format(incDecValua))
                        .concat("|").concat(calif).concat("|").concat(String.valueOf(mdaBase)).concat("|")
                        .concat(nivelFondos).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio FOND");
        }

        if (!infoWrite) {
            log.info("File FOND is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo FOND creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoFond(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM FOND WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
