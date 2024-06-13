package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.IrreDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Irre;
import com.proyecto.rr7.services.IrreService;
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
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class IrreServiceImpl implements IrreService {

    private static final Logger log = LogManager.getLogger(IrreServiceImpl.class);

    IrreDao irreDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public IrreServiceImpl(IrreDao irreDao, JdbcTemplate jdbcTemplate) {
        this.irreDao = irreDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String afectacion = "0";
    String nivelfondos = "0";
    String operacion = "0";
    String claveramo = "0";
    double saldo = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(afectacion).concat("|").concat(nivelfondos)
            .concat("|").concat(operacion).concat("|").concat(claveramo).concat("|")
            .concat(String.valueOf(saldo)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerIrre(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Irre> list = irreDao.findByAnioMes(anioMes);

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Irre lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                afectacion = lista.getAfectacion();
                nivelfondos = lista.getNivelFondos();
                operacion = lista.getOperacion();
                claveramo = lista.getCveRamo();
                saldo = lista.getSaldo();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                        .concat(nivel4).concat("|").concat(moneda).concat("|").concat(afectacion)
                        .concat("|").concat(nivelfondos).concat("|").concat(operacion).concat("|")
                        .concat(claveramo).concat("|").concat(String.valueOf(saldo)).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio ACRE");
        }

        if (!infoWrite) {
            log.info("File ACRE is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo ACRE creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoIrre(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM IRRE WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
