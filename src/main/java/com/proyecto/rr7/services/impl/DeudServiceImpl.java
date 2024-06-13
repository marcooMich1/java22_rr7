package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.DeudDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Deud;
import com.proyecto.rr7.services.DeudService;
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
public class DeudServiceImpl implements DeudService {

    private static final Logger log = LogManager.getLogger(DeudServiceImpl.class);

    DeudDao deudDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DeudServiceImpl(DeudDao deudDao, JdbcTemplate jdbcTemplate) {
        this.deudDao = deudDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String operacion = "0";
    String cveRamo = "0";
    String plazo = "0";
    String afectacion = "0";
    double primasPorCobrarTotal = 0;
    double recargos = 0;
    double impuestos = 0;
    double derechosPoliza = 0;
    double consecutivo = 0;
    double recargosDev = 0;
    double derechosPolizaDev = 0;
    double comiXDev = 0;
    double primasPorCobrarAfecto = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(String.valueOf(consecutivo)).concat("|")
            .concat(operacion).concat("|").concat(cveRamo).concat("|").concat(plazo).concat("|")
            .concat(afectacion).concat("|").concat(String.valueOf(primasPorCobrarTotal)).concat("|")
            .concat(String.valueOf(recargos)).concat("|").concat(String.valueOf(impuestos)).concat("|")
            .concat(String.valueOf(derechosPoliza)).concat("|").concat(String.valueOf(recargosDev)).concat("|")
            .concat(String.valueOf(derechosPolizaDev)).concat("|").concat(String.valueOf(comiXDev)).concat("|")
            .concat(String.valueOf(primasPorCobrarAfecto)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerDeud(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Deud> list = deudDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Deud lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutivo = lista.getConsecutivo();
                int cons = (int) Math.round(consecutivo);
                operacion = lista.getOperacion();
                cveRamo = lista.getCveRamo();
                plazo = lista.getPlazo();
                afectacion = lista.getAfectacion();
                primasPorCobrarTotal = lista.getPrimasPorCobrarTotal();
                String ppc = format.format(primasPorCobrarTotal);
                recargos = lista.getRecargos();
                String rec = format.format(recargos);
                impuestos = lista.getImpuestos();
                String imp = format.format(impuestos);
                derechosPoliza = lista.getDerechosPoliza();
                String dp = format.format(derechosPoliza);
                recargosDev = lista.getRecargosDev();
                String rd = format.format(recargosDev);
                derechosPolizaDev = lista.getDerechosPolizaDev();
                String dpd = format.format(derechosPolizaDev);
                comiXDev = lista.getComiXDev();
                String cxd = format.format(comiXDev);
                primasPorCobrarAfecto = lista.getPrimasPorCobrarAfecto();
                String ppca = format.format(primasPorCobrarAfecto);

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|")
                        .concat(nivel4).concat("|").concat(moneda).concat("|").concat(String.valueOf(cons))
                        .concat("|").concat(operacion).concat("|").concat(cveRamo).concat("|").concat(plazo)
                        .concat("|").concat(afectacion).concat("|").concat((ppc)).concat("|")
                        .concat((rec)).concat("|").concat((imp)).concat("|").concat((dp)).concat("|")
                        .concat((rd)).concat("|").concat((dpd)).concat("|").concat((cxd)).concat("|")
                        .concat((ppca)).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio DEUD");
        }

        if (!infoWrite) {
            log.info("File DEUD is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo DEUD creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoDeud(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM DEUD WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
