package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.InmuDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Inmu;
import com.proyecto.rr7.services.InmuService;
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

@Service
@Transactional
public class InmuServiceImpl implements InmuService {

    private static final Logger log = LogManager.getLogger(InmuServiceImpl.class);

    InmuDao inmuDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public InmuServiceImpl(InmuDao inmuDao, JdbcTemplate jdbcTemplate) {
        this.inmuDao = inmuDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String afectacion = "0";
    String nivelFondos = "0";
    String calle = "0";
    String numero = "0";
    String colonia = "0";
    String cp = "0";
    String entidad = "0";
    String tipoInm = "0";
    String clasifInm = "0";
    String fchAdq = "0";
    String fchUltVal = "0";
    String fchCapSv = "0";
    String fchIniArren = "0";
    String fchVenArren = "0";
    String estatusPR = "0";
    String fchIniCons = "0";
    String portaSegFlex = "0";
    int moneda = 0;
    double ctoHist = 0;
    double depreciHi = 0;
    double incrVal = 0;
    double depreInc = 0;
    double totalInmueble = 0;
    double superavitAfecto = 0;
    double capSuperavit = 0;
    double gastosAn = 0;
    double rentasAn = 0;
    double porcentajeAfect = 0;
    double importeAfectoTotal = 0;
    double tasaRdto = 0;
    double valorFis = 0;
    double valorRent = 0;
    double valorComer = 0;
    double tasaGaranti = 0;
    double superavitAfectoRcs = 0;
    double superavitAfectoOp = 0;
    double consecutiv = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4).concat("|")
            .concat(String.valueOf(moneda)).concat("|").concat(String.valueOf(consecutiv)).concat("|")
            .concat(afectacion).concat("|").concat(nivelFondos).concat("|").concat(calle).concat("|").concat(numero)
            .concat("|").concat(colonia).concat("|").concat(cp).concat("|").concat(entidad).concat("|").concat(tipoInm)
            .concat("|").concat(clasifInm).concat("|").concat(fchAdq).concat("|").concat(fchUltVal).concat("|")
            .concat(fchCapSv).concat("|").concat(fchIniArren).concat("|").concat(fchVenArren).concat("|")
            .concat(estatusPR).concat("|").concat(fchIniCons).concat("|").concat(String.valueOf(ctoHist)).concat("|")
            .concat(String.valueOf(depreciHi)).concat("|").concat(String.valueOf(incrVal)).concat("|")
            .concat(String.valueOf(depreInc)).concat("|").concat(String.valueOf(totalInmueble)).concat("|")
            .concat(String.valueOf(superavitAfecto)).concat("|").concat(String.valueOf(gastosAn)).concat("|")
            .concat(String.valueOf(rentasAn)).concat("|").concat(String.valueOf(porcentajeAfect)).concat("|")
            .concat(String.valueOf(importeAfectoTotal)).concat("|").concat(String.valueOf(tasaRdto)).concat("|")
            .concat(String.valueOf(valorFis)).concat("|").concat(String.valueOf(valorRent)).concat("|")
            .concat(String.valueOf(valorComer)).concat("|").concat(portaSegFlex).concat("|")
            .concat(String.valueOf(tasaGaranti)).concat("|").concat(String.valueOf(superavitAfectoRcs)).concat("|")
            .concat(String.valueOf(superavitAfectoOp)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerInmu(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Inmu> list = inmuDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Inmu lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutiv = lista.getConsecutiv();
                int consecutivo = (int) Math.round(consecutiv);
                afectacion = lista.getAfectacion();
                nivelFondos = lista.getNivelFondos();
                calle = lista.getCalle();
                numero = lista.getNumero();
                colonia = lista.getColonia();
                cp = lista.getCp();
                entidad = lista.getEntidad();
                tipoInm = lista.getTipoInm();
                clasifInm = lista.getClasifInm();
                fchAdq = lista.getFchAdq();
                fchUltVal = lista.getFchUltVal();
                fchCapSv = lista.getFchCapSv();
                fchIniArren = lista.getFchIniArren();
                String fia = fchIniArren.replace("/", "");
                fchVenArren = lista.getFchVenArren();
                String fva = fchVenArren.replace("/", "");
                estatusPR = lista.getEstatusPR();
                fchIniCons = lista.getFchIniCons();
                String fic = fchIniCons.replace("/", "");
                ctoHist = lista.getCtoHist();
                depreciHi = lista.getDepreciHi();
                incrVal = lista.getIncrVal();
                depreInc = lista.getDepreInc();
                totalInmueble = lista.getTotalInmueble();
                superavitAfecto = lista.getSuperavitAfecto();
                capSuperavit = lista.getCapSuperavit();
                gastosAn = lista.getGastosAn();
                rentasAn = lista.getRentasAn();
                porcentajeAfect = lista.getPorcentajeAfect();
                importeAfectoTotal = lista.getImporteAfectoTotal();
                tasaRdto = lista.getTasaRdto();
                valorFis = lista.getValorFis();
                valorRent = lista.getValorRent();
                valorComer = lista.getValorComer();
                portaSegFlex = lista.getPortaSegFlex();
                tasaGaranti = lista.getTasaGaranti();
                superavitAfectoRcs = lista.getSuperavitAfectoRcs();
                superavitAfectoOp = lista.getSuperavitAfectoOp();
                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(String.valueOf(moneda)).concat("|").concat(String.valueOf(consecutivo))
                        .concat("|").concat(afectacion).concat("|").concat(nivelFondos).concat("|").concat(calle)
                        .concat("|").concat(numero).concat("|").concat(colonia).concat("|").concat(cp).concat("|")
                        .concat(entidad).concat("|").concat(tipoInm).concat("|").concat(clasifInm).concat("|")
                        .concat(fchAdq).concat("|").concat(fchUltVal).concat("|").concat(fchCapSv).concat("|")
                        .concat(fia).concat("|").concat(fva).concat("|").concat(estatusPR).concat("|").concat(fic)
                        .concat("|").concat((format.format(ctoHist))).concat("|").concat(String.valueOf(depreciHi))
                        .concat("|").concat(format.format(incrVal)).concat("|").concat(String.valueOf(depreInc))
                        .concat("|").concat(format.format(totalInmueble)).concat("|")
                        .concat(format.format(superavitAfecto)).concat("|").concat(format.format(capSuperavit))
                        .concat("|").concat(String.valueOf(gastosAn)).concat("|").concat(String.valueOf(rentasAn))
                        .concat("|").concat(String.valueOf(porcentajeAfect)).concat("|")
                        .concat(format.format(importeAfectoTotal)).concat("|").concat(String.valueOf(tasaRdto))
                        .concat("|").concat(format.format(valorFis)).concat("|").concat(format.format(valorRent))
                        .concat("|").concat(format.format(valorComer)).concat("|").concat(portaSegFlex).concat("|")
                        .concat(String.valueOf(tasaGaranti)).concat("|").concat(format.format(superavitAfectoRcs))
                        .concat("|").concat(format.format(superavitAfectoOp)).concat("|;"));
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
    public int getExistInfoInmu(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM INMU WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
