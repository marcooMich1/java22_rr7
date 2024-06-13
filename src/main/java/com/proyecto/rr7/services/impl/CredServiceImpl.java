package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CredDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Cred;
import com.proyecto.rr7.services.CredService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CredServiceImpl implements CredService {

    private static final Logger log = LoggerFactory.getLogger(CredServiceImpl.class);

    CredDao credDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public CredServiceImpl(CredDao credDao, JdbcTemplate jdbcTemplate) {
        this.credDao = credDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    double consecutiv = 0;
    String afectacion = "0";
    String tipoCredito = "0";
    double valHistInicial = 0;
    double saldoCierre = 0;
    String fechaAdqui = "0";
    String personalidadJuridica = "0";
    String razonSocial = "0";
    String primerNombre = "0";
    String segundoNombre = "0";
    String apellidoPaterno = "0";
    String apellidoMaterno = "0";
    String fechaVencimiento = "0";
    double estimCastigos = 0;
    double dXI = 0;
    double pagoCapital = 0;
    double carteraVencida = 0;
    double plazoInt = 0;
    double tasaInt = 0;
    String garantia = "0";
    double valorGaran = 0;
    double avaluoFis = 0;
    double reservaPreventiva = 0;
    String nivelFondos = "0";
    String numeroPolizaVida = "0";
    String numeroPolizaDanios = "0";
    String tipoGarantiaCobertura = "0";
    String califoncpt = "0";
    String cvePonderadorContraparte = "0";
    String califongaran = "0";
    String cvePonderadorGarantia = "0";
    double valorConversionARiesgocpt = 0;
    double valorGaranCober = 0;
    String portaSegFlex = "0";
    double tasaGaranti = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(String.valueOf(moneda)).concat("|").concat(String.valueOf(consecutiv)).concat("|")
            .concat(afectacion).concat("|").concat(tipoCredito).concat("|").concat(String.valueOf(valHistInicial))
            .concat("|").concat(String.valueOf(saldoCierre)).concat("|").concat(fechaAdqui).concat("|")
            .concat(personalidadJuridica).concat("|").concat(razonSocial).concat("|").concat(primerNombre).concat("|")
            .concat(segundoNombre).concat("|").concat(apellidoPaterno).concat("|").concat(apellidoMaterno).concat("|")
            .concat(fechaVencimiento).concat("|").concat(String.valueOf(estimCastigos)).concat("|")
            .concat(String.valueOf(dXI)).concat("|").concat(String.valueOf(pagoCapital)).concat("|")
            .concat(String.valueOf(carteraVencida)).concat("|").concat(String.valueOf(plazoInt)).concat("|")
            .concat(String.valueOf(tasaInt)).concat("|").concat(garantia).concat("|").concat(String.valueOf(valorGaran))
            .concat("|").concat(String.valueOf(avaluoFis)).concat("|").concat(String.valueOf(reservaPreventiva))
            .concat("|").concat(nivelFondos).concat("|").concat(numeroPolizaVida).concat("|").concat(numeroPolizaDanios)
            .concat("|").concat(tipoGarantiaCobertura).concat("|").concat(califoncpt).concat("|")
            .concat(cvePonderadorContraparte).concat("|").concat(califongaran).concat("|").concat(cvePonderadorGarantia)
            .concat("|").concat(String.valueOf(valorConversionARiesgocpt)).concat("|")
            .concat(String.valueOf(valorGaranCober)).concat("|").concat(portaSegFlex).concat("|")
            .concat(String.valueOf(tasaGaranti)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerCred(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Cred> list = credDao.findByAnioMes(anioMes);
        DecimalFormat format = new DecimalFormat("#.##");

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Cred lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutiv = lista.getConsecutiv();
                int consecutivo = (int) Math.round(consecutiv);
                afectacion = lista.getAfectacion();
                tipoCredito = lista.getTipoCredito();
                valHistInicial = lista.getValHistInicial();
                saldoCierre = lista.getSaldoCierre();
                fechaAdqui = lista.getFechaAdqui();
                personalidadJuridica = lista.getPersonalidadJuridica();
                razonSocial = lista.getRazonSocial();
                primerNombre = lista.getPrimerNombre();
                segundoNombre = lista.getSegundoNombre();
                apellidoPaterno = lista.getApellidoPaterno();
                apellidoMaterno = lista.getApellidoMaterno();
                fechaVencimiento = lista.getFechaVencimiento();
                estimCastigos = lista.getEstimCastigos();
                dXI = lista.getDXI();
                pagoCapital = lista.getPagoCapital();
                carteraVencida = lista.getCarteraVencida();
                plazoInt = lista.getPlazoInt();
                tasaInt = lista.getTasaInt();
                garantia = lista.getGarantia();
                valorGaran = lista.getValorGaran();
                avaluoFis = lista.getAvaluoFis();
                reservaPreventiva = lista.getReservaPreventiva();
                nivelFondos = lista.getNivelFondos();
                numeroPolizaVida = lista.getNumeroPolizaVida();
                numeroPolizaDanios = lista.getNumeroPolizaDanios();
                tipoGarantiaCobertura = lista.getTipoGarantiaCobertura();
                califoncpt = lista.getCalifoncpt();
                cvePonderadorContraparte = lista.getCvePonderadorContraparte();
                califongaran = lista.getCalifongaran();
                cvePonderadorGarantia = lista.getCvePonderadorGarantia();
                valorConversionARiesgocpt = lista.getValorConversionARiesgocpt();
                valorGaranCober = lista.getValorGaranCober();
                portaSegFlex = lista.getPortaSegFlex();
                tasaGaranti = lista.getTasaGaranti();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(moneda).concat("|").concat(String.valueOf(consecutivo))
                        .concat("|").concat(afectacion).concat("|").concat(tipoCredito).concat("|")
                        .concat(format.format(valHistInicial)).concat("|").concat(format.format(saldoCierre))
                        .concat("|").concat(fechaAdqui).concat("|").concat(personalidadJuridica).concat("|")
                        .concat(razonSocial).concat("|").concat(primerNombre).concat("|").concat(segundoNombre)
                        .concat("|").concat(apellidoPaterno).concat("|").concat(apellidoMaterno).concat("|")
                        .concat(fechaVencimiento).concat("|").concat(format.format(estimCastigos)).concat("|")
                        .concat(format.format(dXI)).concat("|").concat(format.format(pagoCapital)).concat("|")
                        .concat(format.format(carteraVencida)).concat("|").concat(format.format(plazoInt))
                        .concat("|").concat(format.format(tasaInt)).concat("|").concat(garantia).concat("|")
                        .concat(format.format(valorGaran)).concat("|").concat(format.format(avaluoFis))
                        .concat("|").concat(format.format(reservaPreventiva)).concat("|").concat(nivelFondos)
                        .concat("|").concat(numeroPolizaVida).concat("|").concat(numeroPolizaDanios).concat("|")
                        .concat(tipoGarantiaCobertura).concat("|").concat(califoncpt).concat("|")
                        .concat(cvePonderadorContraparte).concat("|").concat(califongaran).concat("|")
                        .concat(cvePonderadorGarantia).concat("|").concat(format.format(valorConversionARiesgocpt))
                        .concat("|").concat(format.format(valorGaranCober)).concat("|").concat(portaSegFlex)
                        .concat("|").concat(format.format(tasaGaranti)).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio CRED");
        }

        if (!infoWrite) {
            log.info("File CRED is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo CRED creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoCred(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM CRED WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
