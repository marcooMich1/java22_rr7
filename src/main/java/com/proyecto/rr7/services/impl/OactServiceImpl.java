package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.OactDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Oact;
import com.proyecto.rr7.services.OactService;
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
public class OactServiceImpl implements OactService {

    private static final Logger log = LogManager.getLogger(OactServiceImpl.class);

    OactDao oactDao;

    @Autowired
    public OactServiceImpl(OactDao oactDao) {
        this.oactDao = oactDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String cveOtrosActivos = "0";
    String subCveOtrosActivos = "0";
    String afectacion = "0";
    String nivfonprop = "0";
    double valores = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(cveOtrosActivos).concat("|")
            .concat(subCveOtrosActivos).concat("|").concat(afectacion).concat("|").concat(nivfonprop)
            .concat("|").concat(String.valueOf(valores)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerOact(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Oact> list = oactDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())){
            for (Oact lista : list){
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                cveOtrosActivos = lista.getCveOtrosActivos();
                subCveOtrosActivos = lista.getSubCveOtrosActivos();
                afectacion = lista.getAfectacion();
                nivfonprop = lista.getNivFondProp();
                valores = lista.getValores();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(moneda).concat("|").concat(cveOtrosActivos).concat("|")
                        .concat(subCveOtrosActivos).concat("|").concat(format.format(valores).concat("|")
                                .concat(afectacion).concat("|").concat(nivfonprop).concat("|;")));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio OACT");
        }

        if (!infoWrite) {
            log.info("File OACT is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo OACT creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public void updateOact(int anio, int mes) {
        oactDao.updateValores_0();
        oactDao.updateValoresConBalanza(anio, mes);
    }

}
