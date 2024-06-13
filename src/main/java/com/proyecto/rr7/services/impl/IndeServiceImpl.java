package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.IndeDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Inde;
import com.proyecto.rr7.services.IndeService;
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
public class IndeServiceImpl implements IndeService {

    private static final Logger log = LogManager.getLogger(IndeServiceImpl.class);

    IndeDao indeDao;
    JdbcTemplate jdbcTemplate;

    @Autowired
    public IndeServiceImpl(IndeDao indeDao, JdbcTemplate jdbcTemplate) {
        this.indeDao = indeDao;
        this.jdbcTemplate = jdbcTemplate;
    }

    String nivel1 = "0";
    String nivel2 = "0";
    String nivel3 = "0";
    String nivel4 = "0";
    String moneda = "0";
    String consecutiv = "0";
    String afectacion = "0";
    String mdaBase = "0";
    String fchAdq = "0";
    String liquidez = "0";
    String isin = "0";
    String emisor = "0";
    String descripc = "0";
    String parametriz = "0";
    String serie = "0";
    String tipoValor = "0";
    String numContrs = "0";
    String fchEmi = "0";
    String fchVto = "0";
    String tasaRdto = "0";
    String calific = "0";
    String tipoMod = "0";
    String tipoOrg = "0";
    String contrato = "0";
    String claveOrg = "0";
    String precioEj = "0";
    String indEfect = "0";
    String ctoAdqPosicionActiva = "0";
    String ctoAdqPosicionPasiva = "0";
    String netoAdquisicion = "0";
    String cotizacionPosicionActiva = "0";
    String cotizacionPosicionPasiv = "0";
    String netoCotizacion = "0";
    String primaPagadaOpciones = "0";
    String primaEvaluadaOpciones = "0";
    String incrementoValuacion = "0";
    String aportGarantDerivados = "0";
    String consecDv = "0";
    String montoEfecto = "0";
    String nivelFondos = "0";
    String titulos = "0";
    String tipoEmisora = "0";
    String precioEjPas = "0";
    String monedaAct = "0";
    String monedaPas = "0";
    String pzoPagoAct = "0";
    String pzoPagoPas = "0";
    String tipoModPas = "0";
    String portaSegFlex = "0";
    String tasaGaranti = "0";
    String calce = "0";
    String tasaPactadaSwapAct = "0";
    String tasaPactadaSwapPas = "0";

    String cadena = nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
            .concat("|").concat(moneda).concat("|").concat(consecutiv).concat("|").concat(afectacion)
            .concat("|").concat(mdaBase).concat("|").concat(fchAdq).concat("|").concat(liquidez)
            .concat("|").concat(isin).concat("|").concat(emisor).concat("|").concat(descripc)
            .concat("|").concat(parametriz).concat("|").concat(serie).concat("|").concat(tipoValor)
            .concat("|").concat(numContrs).concat("|").concat(fchEmi).concat("|").concat(fchVto)
            .concat("|").concat(tasaRdto).concat("|").concat(calific).concat("|").concat(tipoMod)
            .concat("|").concat(tipoOrg).concat("|").concat(contrato).concat("|").concat(claveOrg)
            .concat("|").concat(precioEj).concat("|").concat(indEfect).concat("|")
            .concat(ctoAdqPosicionActiva).concat("|").concat(ctoAdqPosicionPasiva).concat("|")
            .concat(netoAdquisicion).concat("|").concat(cotizacionPosicionActiva).concat("|")
            .concat(cotizacionPosicionPasiv).concat("|").concat(netoCotizacion).concat("|")
            .concat(primaPagadaOpciones).concat("|").concat(primaEvaluadaOpciones).concat("|")
            .concat(incrementoValuacion).concat("|").concat(aportGarantDerivados).concat("|")
            .concat(consecDv).concat("|").concat(montoEfecto).concat("|").concat(nivelFondos).concat("|")
            .concat(titulos).concat("|").concat(tipoEmisora).concat("|").concat(precioEjPas).concat("|")
            .concat(monedaAct).concat("|").concat(monedaPas).concat("|").concat(pzoPagoAct).concat("|")
            .concat(pzoPagoPas).concat("|").concat(tipoModPas).concat("|").concat(portaSegFlex).concat("|")
            .concat(tasaGaranti).concat("|").concat(calce).concat("|").concat(tasaPactadaSwapAct).concat("|")
            .concat(tasaPactadaSwapPas).concat("|;");

    @Override
    public ArchivoRespuesta obtenerInde(String nombre, String anioMes) throws IOException {
        ArchivoRespuesta ar = new ArchivoRespuesta();
        ar.setBaseArchivo(generarTxt(nombre, anioMes));
        ar.setNombreArchivo(nombre);
        return ar;
    }

    public String generarTxt(String nombre, String anioMes) throws IOException {
        String response = "";
        List<Inde> list = indeDao.findByAnioMes(anioMes);

        File archivo = new File("D:\\TempSesa\\" + nombre);
        Files.delete(archivo.toPath());

        archivo.createNewFile();
        FileWriter fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        boolean infoWrite = false;
        if (!(list.isEmpty())) {
            for (Inde lista : list) {
                nivel1 = lista.getNivel1();
                nivel2 = lista.getNivel2();
                nivel3 = lista.getNivel3();
                nivel4 = lista.getNivel4();
                moneda = lista.getMoneda();
                consecutiv = lista.getConsecutiv();
                afectacion = lista.getAfectacion();
                mdaBase = lista.getMdaBase();
                fchAdq = lista.getFchAdq();
                String fa = fchAdq.replace("/", "");
                liquidez = lista.getLiquidez();
                isin = lista.getIsin();
                emisor = lista.getEmisor();
                descripc = lista.getDescripc();
                parametriz = lista.getParametriz();
                serie = lista.getSerie();
                tipoValor = lista.getTipoValor();
                numContrs = lista.getNumContrs();
                fchEmi = lista.getFchEmi();
                String fe = fchEmi.replace("/", "");
                fchVto = lista.getFchVto();
                String fv = fchVto.replace("/", "");
                tasaRdto = lista.getTasaRdto();
                calific = lista.getCalific();
                tipoMod = lista.getTipoMod();
                tipoOrg = lista.getTipoOrg();
                contrato = lista.getContrato();
                claveOrg = lista.getClaveOrg();
                precioEj = lista.getPrecioEj();
                indEfect = lista.getIndEfect();
                ctoAdqPosicionActiva = lista.getCtoAdqPosicionActiva();
                ctoAdqPosicionPasiva = lista.getCtoAdqPosicionPasiva();
                netoAdquisicion = lista.getNetoAdquisicion();
                cotizacionPosicionActiva = lista.getCotizacionPosicionActiva();
                cotizacionPosicionPasiv = lista.getCotizacionPosicionPasiv();
                netoCotizacion = lista.getNetoCotizacion();
                primaPagadaOpciones = lista.getPrimaPagadaOpciones();
                primaEvaluadaOpciones = lista.getPrimaEvaluadaOpciones();
                incrementoValuacion = lista.getIncrementoValuacion();
                aportGarantDerivados = lista.getAportGarantDerivados();
                consecDv = lista.getConsecDv();
                montoEfecto = lista.getMontoEfecto();
                nivelFondos = lista.getNivelFondos();
                titulos = lista.getTitulos();
                tipoEmisora = lista.getTipoEmisora();
                precioEjPas = lista.getPrecioEjPas();
                monedaAct = lista.getMonedaAct();
                monedaPas = lista.getMonedaPas();
                pzoPagoAct = lista.getPzoPagoAct();
                pzoPagoPas = lista.getPzoPagoPas();
                tipoModPas = lista.getTipoModPas();
                portaSegFlex = lista.getPortaSegFlex();
                tasaGaranti = lista.getTasaGaranti();
                calce = lista.getCalce();
                tasaPactadaSwapAct = lista.getTasaPactadaSwapAct();
                tasaPactadaSwapPas = lista.getTasaPactadaSwapPas();

                bw.write(nivel1.concat("|").concat(nivel2).concat("|").concat(nivel3).concat("|").concat(nivel4)
                        .concat("|").concat(moneda).concat("|").concat(consecutiv).concat("|").concat(afectacion)
                        .concat("|").concat(mdaBase).concat("|").concat(fa).concat("|").concat(liquidez).concat("|")
                        .concat(isin).concat("|").concat(emisor).concat("|").concat(descripc).concat("|")
                        .concat(parametriz).concat("|").concat(serie).concat("|").concat(tipoValor).concat("|")
                        .concat(numContrs).concat("|").concat(fe).concat("|").concat(fv).concat("|")
                        .concat(tasaRdto).concat("|").concat(calific).concat("|").concat(tipoMod).concat("|")
                        .concat(tipoOrg).concat("|").concat(contrato).concat("|").concat(claveOrg).concat("|")
                        .concat((precioEj)).concat("|").concat((indEfect)).concat("|")
                        .concat((ctoAdqPosicionActiva)).concat("|").concat((ctoAdqPosicionPasiva)).concat("|")
                        .concat((netoAdquisicion)).concat("|").concat((cotizacionPosicionActiva)).concat("|")
                        .concat((cotizacionPosicionPasiv)).concat("|").concat((netoCotizacion)).concat("|")
                        .concat((primaPagadaOpciones)).concat("|").concat((primaEvaluadaOpciones)).concat("|")
                        .concat((incrementoValuacion)).concat("|").concat((aportGarantDerivados)).concat("|")
                        .concat(consecDv).concat("|").concat((montoEfecto)).concat("|").concat(nivelFondos)
                        .concat("|").concat(titulos).concat("|").concat(tipoEmisora).concat("|")
                        .concat((precioEjPas)).concat("|").concat(monedaAct).concat("|").concat(monedaPas)
                        .concat("|").concat(pzoPagoAct).concat("|").concat(pzoPagoPas).concat("|")
                        .concat(tipoModPas).concat("|").concat(portaSegFlex).concat("|").concat(tasaGaranti)
                        .concat("|").concat(calce).concat("|").concat(tasaPactadaSwapAct).concat("|")
                        .concat(tasaPactadaSwapPas).concat("|;"));
                bw.newLine();
                infoWrite = true;
            }
        } else {
            bw.write(cadena);
            bw.newLine();
            infoWrite = true;
            log.info("Nulo o Vacio INDE");
        }

        if (!infoWrite) {
            log.info("File INDE is empty");
            bw.write(cadena);
        }
        bw.close();
        fw.close();
        log.info("Archivo INDE creado correctamente");

        byte[] inputFile;
        byte[] encodeBytes;
        inputFile = Files.readAllBytes(Paths.get(archivo.getAbsolutePath()));
        encodeBytes = Base64.getEncoder().encode(inputFile);
        response = new String(encodeBytes);

        return response;
    }

    @Override
    public int getExistInfoInde(String anioMes) {
        int existe = 0;
        String query = "SELECT IIF (COUNT(*) >= 1, 1, 0) AS INFO FROM INDE WHERE ANIOMES = '" + anioMes + "'";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query);
        while (rowSet.next()) {
            existe = rowSet.getInt("INFO");
        }

        return existe;
    }

}
