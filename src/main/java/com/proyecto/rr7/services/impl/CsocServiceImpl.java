package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CsocDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Csoc;
import com.proyecto.rr7.services.CsocService;
import com.proyecto.rr7.util.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CsocServiceImpl implements CsocService {

    private static final Logger log = LogManager.getLogger(CsocServiceImpl.class);

    CsocDao csocDao;

    @Autowired
    public CsocServiceImpl(CsocDao csocDao) {
        this.csocDao = csocDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String claveCapSoc = "0";
    String subClaveCapSoc = "0";
    String afectacion = "0";
    String nivelFondos = "0";
    String moneda = "0";
    double saldo = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4).concat("|")
            .concat(moneda).concat("|").concat(claveCapSoc).concat("|").concat(subClaveCapSoc).concat("|")
            .concat(String.valueOf(saldo)).concat("|").concat(afectacion).concat("|").concat(nivelFondos).concat("|;");

    @Override
    public ArchivoRespuesta obtenerCsoc(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Csoc> list = csocDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Csoc lista : list) {
                if (lista.getSaldo() != 0 && !(String.valueOf(lista.getMoneda()).equals("00"))) {
                    nivel1 = lista.getNivel1();
                    nivel2 = lista.getNivel2();
                    nivel3 = lista.getNivel3();
                    nivel4 = lista.getNivel4();
                    moneda = lista.getMoneda();
                    claveCapSoc = lista.getClaveCapSoc();
                    subClaveCapSoc = lista.getSubClaveCapSoc();
                    saldo = lista.getSaldo();
                    afectacion = lista.getAfectacion();
                    nivelFondos = lista.getNivelFondos();

                    bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                            .concat(nivel4).concat("|").concat(moneda).concat("|").concat(claveCapSoc)
                            .concat("|").concat(subClaveCapSoc).concat("|").concat(format.format(saldo))
                            .concat("|").concat(afectacion).concat("|").concat(nivelFondos).concat("|;"));
                    bw.newLine();
                    infoWrite = true;
                }
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio CSOC");
        }

        if (!infoWrite) {
            log.info("File CSOC is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo CSOC creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public void updateCsoc(int anio, int mes) {
        csocDao.updateSaldo_0();
        csocDao.updateSaldoConBalanza(anio, mes);
        csocDao.updateSaldoConCmer5();
        csocDao.updateSaldoConCmer6();
        csocDao.updateCmbgConCsocCuenta();

    }

}
