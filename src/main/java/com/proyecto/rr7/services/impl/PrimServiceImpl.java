package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.PrimDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Prim;
import com.proyecto.rr7.services.PrimService;
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
public class PrimServiceImpl implements PrimService {

    private static final Logger log = LogManager.getLogger(PrimServiceImpl.class);

    PrimDao primDao;

    @Autowired
    public PrimServiceImpl(PrimDao primDao) {
        this.primDao = primDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String clavePrimas = "0";
    String subCvePrimas = "0";
    String operacion = "0";
    String claveRamo = "0";
    String claveSubRamo = "0";
    String claveSubSubRamo = "0";
    double saldo = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|")
            .concat(moneda).concat("|").concat(clavePrimas).concat("|").concat(subCvePrimas).concat("|")
            .concat(operacion).concat("|").concat(claveRamo).concat("|").concat(claveSubRamo).concat("|")
            .concat(claveSubSubRamo).concat(String.valueOf(saldo)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerPrim(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Prim> list = primDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Prim lista : list) {
                if (lista.getSaldo() != 0 && !(lista.getMoneda().equals("00"))) {
                    nivel1 = lista.getNivel1();
                    nivel2 = lista.getNivel2();
                    nivel3 = lista.getNivel3();
                    nivel4 = lista.getNivel4();
                    moneda = lista.getMoneda();
                    clavePrimas = lista.getClavePrimas();
                    subCvePrimas = lista.getSubClavePrimas();
                    operacion = lista.getOperacion();
                    claveRamo = lista.getRamo();
                    claveSubRamo = lista.getSubram();
                    claveSubSubRamo = lista.getSubsubram();
                    saldo = lista.getSaldo();

                    bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                            .concat(nivel4)
                            .concat("|").concat(moneda).concat("|").concat(clavePrimas).concat("|")
                            .concat(subCvePrimas).concat("|").concat(operacion).concat("|").concat(claveRamo)
                            .concat("|").concat(claveSubRamo).concat("|").concat(claveSubSubRamo)
                            .concat("|").concat((format.format(saldo)).concat("|;")));
                    bw.newLine();
                    infoWrite = true;
                }
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio PRIM");
        }

        if (!infoWrite) {
            log.info("File PRIM is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo PRIM creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public ResponseMessage updatePrim(int anio, int mes) {
        ResponseMessage respuesta = null;
        primDao.updateSaldo_0();
        primDao.updateSaldoConBalanza(anio, mes);
        respuesta = new ResponseMessage("OK", "PRIM Actualizado", 1);

        return respuesta;
    }

}
