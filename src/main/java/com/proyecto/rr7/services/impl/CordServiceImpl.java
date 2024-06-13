package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CordDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Cord;
import com.proyecto.rr7.services.CordService;
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
public class CordServiceImpl implements CordService {

    private static final Logger log = LogManager.getLogger(CordServiceImpl.class);

    CordDao cordDao;

    @Autowired
    public CordServiceImpl(CordDao cordDao) {
        this.cordDao = cordDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String cveCtasOr = "0";
    String subCveCtasOr = "0";
    double saldo = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(cveCtasOr).concat("|").concat(subCveCtasOr)
            .concat("|").concat(String.valueOf(saldo)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerCord(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Cord> list = cordDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Cord lista : list) {
                if (lista.getSaldo() != 0 && !(lista.getMoneda().equals("00"))) {
                    nivel1 = lista.getNivel1();
                    nivel2 = lista.getNivel2();
                    nivel3 = lista.getNivel3();
                    nivel4 = lista.getNivel4();
                    moneda = lista.getMoneda();
                    cveCtasOr = lista.getClaveCtasOrden();
                    subCveCtasOr = lista.getSubClaveCtasOrden();
                    saldo = lista.getSaldo();

                    bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                            .concat(nivel4).concat("|").concat(moneda).concat("|").concat(cveCtasOr)
                            .concat("|").concat(subCveCtasOr).concat("|").concat(format.format(saldo))
                            .concat("|;"));
                    bw.newLine();
                    infoWrite = true;
                }
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio CORD");
        }

        if (!infoWrite) {
            log.info("File CORD is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo CORD creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public ResponseMessage updateCord(int anio, int mes) {
        ResponseMessage respuesta = null;
        cordDao.updateSaldo_0();
        cordDao.updateSaldoConBalanza(anio, mes);
        respuesta = new ResponseMessage("OK", "CORD Actualizado", 1);

        return respuesta;
    }

}
