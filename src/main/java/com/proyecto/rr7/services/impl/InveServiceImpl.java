package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.InveDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Inve;
import com.proyecto.rr7.services.InveService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.List;

public class InveServiceImpl implements InveService {

    private static final Logger log = LogManager.getLogger(InveServiceImpl.class);

    InveDao inveDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public InveServiceImpl(InveDao inveDao, JdbcTemplate jdbcTemplate) {
        this.inveDao = inveDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    double consecutivo = 0;
    String clasificacion = "0";
    String tipoOrg = "0";
    String cveOrg = "0";
    String contrato = "0";
    double afectacion = 0;
    String isin = "0";
    String parametriz = "0";
    String cveEmisor = "0";
    String serie = "0";
    String tipoValor = "0";
    String descripc = "0";
    String liquidez = "0";
    String fchEmi = "0";
    String fchAdq = "0";
    String fchVto = "0";
    double valorNom = 0;
    double titulos = 0;
    double ctoAdq = 0;
    double valCot = 0;
    double precioUni = 0;
    double tasaVal = 0;
    double incDecValua = 0;
    double periodAmort = 0;
    double tasaAmort = 0;
    double premio = 0;
    double plazo = 0;
    String mdaBase = "0";
    String calif = "0";
    double intXdev = 0;
    double plazoCupon = 0;
    double tasaCupon = 0;
    String cveTasRefe = "0";
    double deterioro = 0;
    double dXint = 0;
    double totalAct = 0;
    String tipoEmisora = "0";
    String califoncpt = "0";
    String reglaCupon = "0";
    double titAmpDer = 0;
    double tasaPactDer = 0;
    double divXcob = 0;
    String nivelFondos = "0";
    String intLpCubreCp = "0";
    String notaEstructurada = "0";
    String tipoNotaEstructurada = "0";
    String portasegflex = "0";
    double tasagaranti = 0;
    String negociable = "0";
    String tipogarantiacobertura = "0";
    String cveponderadorcontraparte = "0";
    String califongaran = "0";
    String cveponderadorgarantia = "0";
    double valorconversionriesgocpt = 0;
    double valorgarancober = 0;
    String calce = "0";
    double incxvaluaCBI = 0;
    double incxvaluaRCS = 0;
    double incxvaluaOP = 0;

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(String.valueOf(moneda)).concat("|").concat(String.valueOf(consecutivo))
            .concat("|").concat(clasificacion).concat("|").concat(tipoOrg).concat("|").concat(cveOrg)
            .concat("|").concat(contrato).concat("|").concat("" + afectacion).concat("|").concat(isin)
            .concat("|").concat(parametriz).concat("|").concat(cveEmisor).concat("|").concat(serie)
            .concat("|").concat(tipoValor).concat("|").concat(descripc).concat("|").concat(liquidez)
            .concat("|").concat(fchEmi).concat("|").concat(fchAdq).concat("|").concat(fchVto)
            .concat("|").concat("" + valorNom).concat("|").concat("" + titulos).concat("|")
            .concat("" + ctoAdq).concat("|").concat("" + valCot).concat("|").concat("" + precioUni)
            .concat("|").concat("" + tasaVal).concat("|").concat("" + incDecValua).concat("|")
            .concat("" + periodAmort).concat("|").concat("" + tasaAmort).concat("|")
            .concat("" + premio).concat("|").concat("" + plazo).concat("|").concat(mdaBase)
            .concat("|").concat(calif).concat("|").concat("" + intXdev).concat("|")
            .concat("" + plazoCupon).concat("|").concat("" + tasaCupon).concat("|").concat(cveTasRefe)
            .concat("|").concat("" + deterioro).concat("|").concat("" + dXint).concat("|")
            .concat("" + totalAct).concat("|").concat(tipoEmisora).concat("|").concat(califoncpt)
            .concat("|").concat(reglaCupon).concat("|").concat("" + titAmpDer).concat("|")
            .concat("" + tasaPactDer).concat("|").concat("" + divXcob).concat("|").concat(nivelFondos)
            .concat("|").concat(intLpCubreCp).concat("|").concat(notaEstructurada)
            .concat("|").concat(tipoNotaEstructurada).concat("|").concat(portasegflex).concat("|")
            .concat("" + tasagaranti).concat("|").concat(negociable).concat("|").concat(tipogarantiacobertura)
            .concat("|").concat(cveponderadorcontraparte).concat("|").concat(califongaran).concat("|")
            .concat(cveponderadorgarantia).concat("|").concat("" + valorconversionriesgocpt).concat("|")
            .concat("" + valorgarancober).concat("|").concat(calce).concat("|").concat("" + incxvaluaCBI)
            .concat("" + incxvaluaRCS).concat("|").concat("" + incxvaluaOP).concat("|;");


    @Override
    public ArchivoRespuesta obtenerInve(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Inve> list = inveDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        int conse = 1;
        if (!(list.isEmpty())) {
            for (Inve lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutivo = conse;
                clasificacion = lista.getClasificacion();
                tipoOrg = lista.getTipoOrg();
                cveOrg = lista.getCveOrg();
                contrato = lista.getContrato();
                afectacion = lista.getAfectacion();
                isin = lista.getIsin();
                parametriz = lista.getParamtriz();
                cveEmisor = lista.getCveEmisor();
                serie = lista.getSerie();
                tipoValor = lista.getTipoValor();
                descripc = lista.getDescrip();
                liquidez = lista.getLiquidez();
                fchEmi = lista.getFchEmi();
                fchAdq = lista.getFchAdq();
                fchVto = lista.getFchVto();
                valorNom = lista.getValorNom();
                titulos = lista.getTitulos();
                ctoAdq = lista.getCtoAdq();
                valCot = lista.getValMercado();
                precioUni = lista.getPrecioUni();
                tasaVal = lista.getTasaVal();
                incDecValua = lista.getIncDelVal();
                periodAmort = lista.getPeriodAmort();
                tasaAmort = lista.getTasaAmort();
                premio = lista.getPremio();
                plazo = lista.getPlazo();
                mdaBase = lista.getMdaBase();
                calif = lista.getCalif();
                intXdev = lista.getInXDev();
                plazoCupon = lista.getPlazoCupon();
                tasaCupon = lista.getTasaCupo();
                cveTasRefe = lista.getCveTasRef();
                deterioro = lista.getDeterioro();
                dXint = lista.getDXInt();
                totalAct = lista.getTotalAct();
                tipoEmisora = lista.getTipoEmisora();
                califoncpt = lista.getCalifoncpt();
                reglaCupon = lista.getReglaCupon();
                titAmpDer = lista.getTiiAmpDer();
                tasaPactDer = lista.getTasaPactDer();
                divXcob = lista.getDivXCob();
                nivelFondos = lista.getNivelFondos();
                intLpCubreCp = lista.getIntLpCubreCp();
                notaEstructurada = lista.getNotaEstructurada();
                tipoNotaEstructurada = lista.getTipoNotaEstructurada();
                portasegflex = lista.getPortaSegFlex();
                tasagaranti = lista.getTasaGaranti();
                negociable = lista.getNegociable();
                tipogarantiacobertura = lista.getTipoGarantiaCobertura();
                cveponderadorcontraparte = lista.getCvePonderadorContraparte();
                califongaran = lista.getCalifongaran();
                cveponderadorgarantia = lista.getVePonderadorGarantia();
                valorconversionriesgocpt = lista.getValorConversionARiesgocpt();
                valorgarancober = lista.getValorGaranCober();
                calce = lista.getCalce();
                incxvaluaCBI = lista.getIncXValuaCbi();
                incxvaluaRCS = lista.getIncXValuaRcs();
                incxvaluaOP = lista.getIncXValuaOp();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(String.valueOf(moneda)).concat("|").concat(String.valueOf(consecutivo))
                        .concat("|").concat(clasificacion).concat("|").concat(tipoOrg).concat("|").concat(cveOrg)
                        .concat("|").concat(contrato).concat("|").concat("" + afectacion).concat("|").concat(isin)
                        .concat("|").concat(parametriz).concat("|").concat(cveEmisor).concat("|").concat(serie)
                        .concat("|").concat(tipoValor).concat("|").concat(descripc).concat("|").concat(liquidez)
                        .concat("|").concat(fchEmi).concat("|").concat(fchAdq).concat("|").concat(fchVto)
                        .concat("|").concat(format.format(valorNom)).concat("|").concat("" + titulos).concat("|")
                        .concat("" + ctoAdq).concat("|").concat("" + valCot).concat("|")
                        .concat(format.format(precioUni)).concat("|").concat(format.format(tasaVal)).concat("|")
                        .concat(format.format(incDecValua)).concat("|").concat("" + periodAmort).concat("|")
                        .concat(format.format(tasaAmort)).concat("|").concat(format.format(premio)).concat("|")
                        .concat("" + plazo).concat("|").concat(mdaBase).concat("|").concat(calif).concat("|")
                        .concat("" + intXdev).concat("|").concat("" + plazoCupon).concat("|")
                        .concat("" + tasaCupon).concat("|").concat(cveTasRefe).concat("|").concat("" + deterioro)
                        .concat("|").concat("" + dXint).concat("|").concat("" + totalAct).concat("|")
                        .concat(tipoEmisora).concat("|").concat(califoncpt).concat("|").concat(reglaCupon)
                        .concat("|").concat("" + titAmpDer).concat("|").concat("" + tasaPactDer).concat("|")
                        .concat("" + divXcob).concat("|").concat(nivelFondos).concat("|").concat(intLpCubreCp)
                        .concat("|").concat(notaEstructurada).concat("|").concat(tipoNotaEstructurada)
                        .concat("|").concat(portasegflex).concat("|").concat("" + tasagaranti).concat("|")
                        .concat(negociable).concat("|").concat(tipogarantiacobertura).concat("|")
                        .concat(cveponderadorcontraparte).concat("|").concat(califongaran).concat("|")
                        .concat(cveponderadorgarantia).concat("|").concat("" + valorconversionriesgocpt)
                        .concat("|").concat("" + valorgarancober).concat("|").concat(calce).concat("|")
                        .concat(format.format(incxvaluaCBI)).concat(format.format(incxvaluaRCS)).concat("|")
                        .concat(format.format(incxvaluaOP)).concat("|;"));// 64
                bw.newLine();
                infoWrite = true;
                conse++;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio INVE");
        }

        if (!infoWrite) {
            log.info("File INVE is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo INVE creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoInve(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM INVE WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
