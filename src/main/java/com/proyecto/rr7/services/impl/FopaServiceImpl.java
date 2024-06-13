package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.FopaDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Fopa;
import com.proyecto.rr7.services.FopaService;
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
public class FopaServiceImpl implements FopaService {

    private static final Logger log = LogManager.getLogger(FopaServiceImpl.class);

    FopaDao fopaDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public FopaServiceImpl(FopaDao fopaDao, JdbcTemplate jdbcTemplate) {
        this.fopaDao = fopaDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String afectacion = "0";
    String nivelFondos = "0";
    double saldo = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
            .concat(nivel4).concat("|").concat(moneda).concat("|").concat(afectacion).concat("|")
            .concat(nivelFondos).concat("|").concat(String.valueOf(saldo)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerFopa(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Fopa> list = fopaDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Fopa lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                afectacion = lista.getAfectacion();
                nivelFondos = lista.getNivelfondos();
                saldo = lista.getSaldo();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                        .concat(nivel4).concat("|").concat(moneda).concat("|").concat(afectacion)
                        .concat("|").concat(nivelFondos).concat("|").concat(format.format(saldo))
                        .concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio FOPA");
        }

        if (!infoWrite) {
            log.info("File FOPA is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo FOPA creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoFopa(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM FOPA WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
