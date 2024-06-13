package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CadqDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.BaseLayoutRr7;
import com.proyecto.rr7.entities.Cadq;
import com.proyecto.rr7.services.CadqService;
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
public class CadqServiceImpl implements CadqService {

    private static final Logger log = LogManager.getLogger(CadqServiceImpl.class);

    CadqDao cadqDao;

    @Autowired
    public CadqServiceImpl(CadqDao cadqDao) {
        this.cadqDao = cadqDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String claveCtoAdq = "0";
    String subclaveCtoAdq = "0";
    String operacion = "0";
    String ramo = "0";
    String subRamo = "0";
    String subSubRamo = "0";
    double saldo = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(claveCtoAdq).concat("|").concat(subclaveCtoAdq)
            .concat("|").concat(operacion).concat("|").concat(ramo).concat("|").concat(subRamo)
            .concat("|").concat(subSubRamo).concat("|").concat("" + saldo).concat("|;");

    @Override
    public ArchivoRespuesta obtenerCadq(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Cadq> listCadq = cadqDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(listCadq.isEmpty())) {
            for (Cadq list : listCadq) {
                if (list.getSaldo() != 0 && !(list.getMoneda().equals("00"))) {
                    nivel1 = list.getNivel1();
                    nivel2 = list.getNivel2();
                    nivel3 = list.getNivel3();
                    nivel4 = list.getNivel4();
                    moneda = list.getMoneda();
                    claveCtoAdq = list.getClaveCtoAdq();
                    subclaveCtoAdq = list.getSubClaveCtoAdq();
                    operacion = list.getOperacion();
                    ramo = list.getRamo();
                    subRamo = list.getSubram();
                    subSubRamo = list.getSubsubramo();
                    saldo = list.getSaldo();

                    bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                            .concat(nivel4).concat("|").concat(moneda).concat("|").concat(claveCtoAdq)
                            .concat("|").concat(subclaveCtoAdq).concat("|").concat(operacion).concat("|")
                            .concat(ramo).concat("|").concat(subRamo).concat("|").concat(subSubRamo).concat("|")
                            .concat(format.format(saldo)).concat("|;"));
                    bw.newLine();
                    infoWrite = true;
                }
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio CADQ");
        }

        if (!infoWrite) {
            log.info("File CADQ is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo CADQ creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public ResponseMessage updateCadq(List<BaseLayoutRr7> getBase, int anio, int mes) {
        ResponseMessage respuesta = null;
        cadqDao.updateSaldo_0();
        cadqDao.updateSaldoConBalanza(anio, mes);
        respuesta = new ResponseMessage("OK", "CADQ Actualizado", 1);

        return respuesta;
    }

}
