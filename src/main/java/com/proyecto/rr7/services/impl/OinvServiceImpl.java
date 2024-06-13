package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.OinvDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Oinv;
import com.proyecto.rr7.services.OinvService;
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
import java.util.Base64;
import java.util.List;

@Transactional
@Service
public class OinvServiceImpl implements OinvService {

    private static final Logger log = LogManager.getLogger(OinvServiceImpl.class);

    OinvDao oinvDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public OinvServiceImpl(OinvDao oinvDao, JdbcTemplate jdbcTemplate) {
        this.oinvDao = oinvDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    double consecutivo = 0;
    String afectacion = "0";
    String tipoActivo = "0";
    double valHistInicial = 0;
    double saldoCierre = 0;
    double numeroLicita = 0;
    String fechaAdqui = "0";
    String persJuridica = "0";
    String razonSocial = "0";
    String primerNombre = "0";
    String segundoNombre = "0";
    String apellidoPaterno = "0";
    String apellidoMaterno = "0";
    String fchVnto = "0";
    double dxint = 0;
    double plazoInt = 0;
    double tazaInt = 0;
    String tipoDeudor = "0";
    String tipoDxc = "0";
    double impDeuFianzas = 0;
    String tipoDeuXResp = "0";
    String tipoDiv = "0";
    String nivelFondos = "0";
    String cvePonderadorContr = "0";
    String cvePonderadorGar = "0";
    String califontcpt = "0";
    String califongaran = "0";
    double valorConverRiesgo = 0;
    double valorGaranCober = 0;
    String tipoGarantiaCobertura = "0";
    String portaSegFlex = "0";
    double tasaGaranti = 0;
    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(String.valueOf(consecutivo)).concat("|")
            .concat(afectacion).concat("|").concat(tipoActivo).concat("|").concat(String.valueOf(valHistInicial))
            .concat("|").concat(String.valueOf(saldoCierre)).concat("|").concat(String.valueOf(numeroLicita))
            .concat("|").concat(fechaAdqui).concat("|").concat(persJuridica).concat("|").concat(razonSocial)
            .concat("|").concat(primerNombre).concat("|").concat(segundoNombre).concat("|")
            .concat(apellidoPaterno).concat("|").concat(apellidoMaterno).concat("|").concat(fchVnto)
            .concat("|").concat(String.valueOf(dxint)).concat("|").concat(String.valueOf(plazoInt))
            .concat("|").concat(String.valueOf(tazaInt)).concat("|").concat(tipoDeudor)
            .concat("|").concat(tipoDxc).concat("|").concat(String.valueOf(impDeuFianzas)).concat("|")
            .concat(tipoDeuXResp).concat("|").concat(tipoDiv).concat("|").concat(nivelFondos).concat("|")
            .concat(cvePonderadorContr).concat("|").concat(cvePonderadorGar).concat("|").concat(califontcpt)
            .concat("|").concat(califongaran).concat("|").concat(String.valueOf(valorConverRiesgo)).concat("|")
            .concat(String.valueOf(valorGaranCober)).concat("|").concat(tipoGarantiaCobertura).concat("|")
            .concat(portaSegFlex).concat("|").concat(String.valueOf(tasaGaranti)).concat("|;");

    @Override
    public ArchivoRespuesta obtenerOinv(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Oinv> list = oinvDao.findByAnioMes(anioMes);

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())){
            for (Oinv lista : list){
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutivo = lista.getConsecutivo();
                afectacion = lista.getAfectacion();
                tipoActivo = lista.getTipoActivo();
                valHistInicial = lista.getValHistInicial();
                saldoCierre = lista.getSaldoCierre();
                numeroLicita = lista.getNumeroLicita();
                fechaAdqui = lista.getFechaAdqui();
                persJuridica = lista.getPersonalidadJuridica();
                razonSocial = lista.getRazonSocial();
                primerNombre = lista.getPrimerNombre();
                segundoNombre = lista.getSegundoNombre();
                apellidoPaterno = lista.getApellidoPaterno();
                apellidoMaterno = lista.getApellidoMaterno();
                fchVnto = lista.getFchVnto();
                dxint = lista.getDXInt();
                plazoInt = lista.getPlazoInt();
                tazaInt = lista.getTasaInt();
                tipoDeudor = lista.getTipoDeudor();
                tipoDxc = lista.getTipoDxc();
                impDeuFianzas = lista.getImpDeuFianzas();
                tipoDeuXResp = lista.getTipoDeuXResp();
                tipoDiv = lista.getTipoDiv();
                nivelFondos = lista.getNivelFondos();
                cvePonderadorContr = lista.getCvePonderadorContraparte();
                cvePonderadorGar = lista.getCvePonderadorGarantia();
                califontcpt = lista.getCalifoncpt();
                califongaran = lista.getCalifongaran();
                valorConverRiesgo = lista.getValorConversionARiesgocpt();
                valorGaranCober = lista.getValorGaranCober();
                tipoGarantiaCobertura = lista.getTipoGarantiaCobertura();
                portaSegFlex = lista.getPortaSegFlex();
                tasaGaranti = lista.getTasaGaranti();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(moneda).concat("|").concat(String.valueOf(consecutivo)).concat("|")
                        .concat(afectacion).concat("|").concat(tipoActivo).concat("|")
                        .concat(String.valueOf(valHistInicial)).concat("|").concat(String.valueOf(saldoCierre))
                        .concat("|").concat(String.valueOf(numeroLicita)).concat("|").concat(fechaAdqui).concat("|")
                        .concat(persJuridica).concat("|").concat(razonSocial).concat("|").concat(primerNombre)
                        .concat("|").concat(segundoNombre).concat("|").concat(apellidoPaterno).concat("|")
                        .concat(apellidoMaterno).concat("|").concat(fchVnto).concat("|")
                        .concat(String.valueOf(dxint)).concat("|").concat(String.valueOf(plazoInt)).concat("|")
                        .concat(String.valueOf(tazaInt)).concat("|").concat(tipoDeudor).concat("|").concat(tipoDxc)
                        .concat("|").concat(String.valueOf(impDeuFianzas)).concat("|").concat(tipoDeuXResp)
                        .concat("|").concat(tipoDiv).concat("|").concat(nivelFondos).concat("|")
                        .concat(cvePonderadorContr).concat("|").concat(cvePonderadorGar).concat("|")
                        .concat(califontcpt).concat("|").concat(califongaran).concat("|")
                        .concat(String.valueOf(valorConverRiesgo)).concat("|")
                        .concat(String.valueOf(valorGaranCober)).concat("|").concat(tipoGarantiaCobertura)
                        .concat("|").concat(portaSegFlex).concat("|").concat(String.valueOf(tasaGaranti))
                        .concat("|;"));
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio OINV");
        }

        if (!infoWrite) {
            log.info("File OINV is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo OINV creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoOinv(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM OINV WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
