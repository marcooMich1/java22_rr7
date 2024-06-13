package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.AcreDao;
import com.proyecto.rr7.entities.Acre;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.services.AcreService;
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
public class AcreServiceImpl implements AcreService {

    private static final Logger log = LogManager.getLogger(AcreServiceImpl.class);

    AcreDao acreDao;

    @Autowired
    public AcreServiceImpl(AcreDao acreDao) {
        this.acreDao = acreDao;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String claveAcre = "0";
    String subClaveAcre = "0";
    String moneda = "0";
    double saldo = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(claveAcre).concat("|").concat(subClaveAcre)
            .concat("|").concat(saldo + "").concat("|;");

    @Override
    public ArchivoRespuesta obtenerACRE(String nombre) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre) throws IOException {
        String response = "";
        List<Acre> listaAcre = acreDao.findAll();
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(listaAcre.isEmpty())) {
            for (Acre lista : listaAcre) {
                if (lista.getSaldo() != 0 || !(lista.getMoneda().equals("00"))) {
                    nivel1 = lista.getNivel1();
                    nivel2 = lista.getNivel2();
                    nivel3 = lista.getNivel3();
                    nivel4 = lista.getNivel4();
                    moneda = lista.getMoneda();
                    claveAcre = lista.getClaveAcre();
                    subClaveAcre = lista.getSubClaveAcre();
                    saldo = lista.getSaldo();

                    bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                            .concat(nivel4).concat("|").concat(moneda).concat("|").concat(claveAcre)
                            .concat("|").concat(subClaveAcre).concat("|").concat(format.format(saldo))
                            .concat("|;"));
                    bw.newLine();
                    infoWrite = true;
                }
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
    public void updateAcre(int anio, int mes) {
        acreDao.updateValores_0();
        acreDao.updateValoresConBalanza(anio, mes);

    }

}
