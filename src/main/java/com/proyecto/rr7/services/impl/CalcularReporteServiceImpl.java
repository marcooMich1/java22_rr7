package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.BaseLayoutRr7Dao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.entities.Rr7ArchivoHistorico;
import com.proyecto.rr7.services.*;
import com.proyecto.rr7.util.Constantes;
import com.proyecto.rr7.util.ResponseMessage;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Transactional
@Service
public class CalcularReporteServiceImpl implements CalcularReporteService {

    private static final Logger log = LogManager.getLogger(CalcularReporteServiceImpl.class);

    JdbcTemplate jdbcTemplate;
    RR7ArchivoHistoricoService rr7Historico;
    BaseLayoutRr7Dao baseLayoutRr7Dao;
    AcreService acreService;
    BaseService baseService;
    CadqService cadqService;
    CcmpService ccmpService;
    CmbgService cmbgService;
    CmerService cmerService;
    CopeService copeService;
    CordService cordService;
    CredService credService;
    CsinService csinService;
    CsocService csocService;
    DeudService deudService;
    FondService fondService;
    FopaService fopaService;
    IndeService indeService;
    InmuService inmuService;
    InveService inveService;
    IrreService irreService;
    OactService oactService;
    OinvService oinvService;
    OpacService opacService;
    OpasService opasService;
    PrimService primService;
    RifiService rifiService;

    @Autowired
    public CalcularReporteServiceImpl(JdbcTemplate jdbcTemplate, RR7ArchivoHistoricoService rr7Historico,
                                      BaseLayoutRr7Dao baseLayoutRr7Dao, CmerService cmerService,
                                      AcreService acreService, BaseService baseService, CadqService cadqService,
                                      CcmpService ccmpService, CmbgService cmbgService, CopeService copeService,
                                      CordService cordService, CredService credService, CsinService csinService,
                                      CsocService csocService, DeudService deudService, FondService fondService,
                                      FopaService fopaService, IndeService indeService, InmuService inmuService,
                                      InveService inveService, IrreService irreService, OactService oactService,
                                      OinvService oinvService, OpacService opacService, OpasService opasService,
                                      PrimService primService, RifiService rifiService) {
        this.jdbcTemplate = jdbcTemplate;
        this.rr7Historico = rr7Historico;
        this.baseLayoutRr7Dao = baseLayoutRr7Dao;
        this.acreService = acreService;
        this.baseService = baseService;
        this.cadqService = cadqService;
        this.ccmpService = ccmpService;
        this.cmbgService = cmbgService;
        this.cmerService = cmerService;
        this.copeService = copeService;
        this.cordService = cordService;
        this.credService = credService;
        this.csinService = csinService;
        this.csocService = csocService;
        this.deudService = deudService;
        this.fondService = fondService;
        this.fopaService = fopaService;
        this.indeService = indeService;
        this.inmuService = inmuService;
        this.inveService = inveService;
        this.irreService = irreService;
        this.oactService = oactService;
        this.oinvService = oinvService;
        this.opacService = opacService;
        this.opasService = opasService;
        this.primService = primService;
        this.rifiService = rifiService;
    }

    @Override
    public ResponseMessage procesarRr7(int[] idsArchivos, String anioMes, String clvCompania) throws IOException {
        ResponseMessage respuesta = null;

        String[] anioMesArray = anioMes.split("-");
        String anio = anioMesArray[0];
        String mes = anioMesArray[1];
        log.info("Entro a procesar RR7 y a validar información.");
        respuesta = validaReporte(Integer.parseInt(anio), mes, anioMes, idsArchivos);

        if (respuesta.getDataExtra().toString().equals("1")) {
            if (getExistReporteHistorico(Integer.parseInt(anio), mes, idsArchivos) == 0) {
                respuesta = calcularReporte(idsArchivos, Integer.parseInt(anio), Integer.parseInt(mes), clvCompania, anioMes);
            } else
                respuesta = new ResponseMessage("ERROR", "Al menos uno de los archivos seleccionados " + "ya existe. ¿Desea recalcular ?", 2);
        } else {
            log.info("Algo salio mal al momento de validar en procesarRr7...");
            return respuesta;
        }

        return respuesta;
    }

    public ResponseMessage validaReporte(int anio, String mes, String anioMes, int[] idsArchivos) {
        ResponseMessage respuesta = new ResponseMessage();
        // TODO - REVISAR LA CONDICION CON LOS IF
        for (int i = 0; i < idsArchivos.length; i++) {
            if ((idsArchivos[i] >= 1 && idsArchivos[i] <= 2) || (idsArchivos[i] >= 10 && idsArchivos[i] <= 20)) {
                respuesta = crossReporteWithBalanza(anio, mes);
            } else if ((idsArchivos[i] >= 3 && idsArchivos[i] <= 9) || (idsArchivos[i] >= 21 && idsArchivos[i] <= 24)) {
                respuesta = crossReporteWithDataTable(idsArchivos, anioMes);
                if (respuesta.getDataExtra().toString().equals("1")) {
                    return respuesta;
                }
            }
        }
        return respuesta;
    }

    public ResponseMessage crossReporteWithBalanza(int anio, String mes) {
        ResponseMessage respuesta = null;

        if (rr7Historico.existInfoBalanza(anio, mes) == 1) {
            respuesta = new ResponseMessage("OK", "La balanza contiene datos para la fecha " + anio + "-" + mes + " seleccionada.", 1);
        } else
            respuesta = new ResponseMessage("ERROR", "La balanza no contiene datos para la fecha " + anio + "-" + mes + " seleccionada.", -1);

        return respuesta;
    }

    public ResponseMessage crossReporteWithDataTable(int[] idsArchivos, String anioMes) {
        ResponseMessage respuesta = null;
        String existe = "";
        String archivo = "";
        Map<String, Object> map;
        for (int idsArchivo : idsArchivos) {
            map = validarInfo(idsArchivo, anioMes);
            existe = (String) map.get(Constantes.EXISTE);
            archivo = (String) map.get(Constantes.ARCHIVO);
            if (Integer.parseInt(existe) != 0) {
                respuesta = new ResponseMessage(Constantes.OK, "Todo bien con " + archivo, 1);
            } else {
                respuesta = new ResponseMessage(Constantes.ERROR,
                        "No existen datos acorde a la fecha " + anioMes + " para " + archivo, -1);
                break;
            }
        }

        return respuesta;
    }

    public Map<String, Object> validarInfo(int id, String anioMes) {
        int existe = 0;
        Map<String, Object> map = new HashMap<>();
        switch (id) {
            case 3:
                existe = inveService.getExistInfoInve(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "INVE");
                break;
            case 4:
                existe = indeService.getExistInfoInde(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "INDE");
                break;
            case 5:
                existe = inmuService.getExistInfoInmu(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "INMU");
                break;
            case 6:
                existe = credService.getExistInfoCred(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "CRED");
                break;
            case 7:
                existe = deudService.getExistInfoDeud(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "DEUD");
                break;
            case 8:
                existe = oinvService.getExistInfoOinv(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "OINV");
                break;
            case 9:
                existe = irreService.getExistInfoIrre(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "IRRE");
                break;
            case 21:
                existe = baseService.getExistInfoBase(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "BASE");
                break;
            case 22:
                existe = ccmpService.getExistInfoCcmp(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "CCMP");
                break;
            case 23:
                existe = fopaService.getExistInfoFopa(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "FOPA");
                break;
            case 24:
                existe = fondService.getExistInfoFond(anioMes);
                map.put(Constantes.EXISTE, existe);
                map.put(Constantes.ARCHIVO, "FOND");
                break;
            default:
                log.info("La validacion de info de archivos semi automaticos, no encontro coincidencias.");
        }
        return map;
    }

    public int getExistReporteHistorico(int anio, String mes, int[] idsArchivos) {
        int existe = 0;
        for (int idsArchivo : idsArchivos) {
            existe = rr7Historico.getExistReporteHistorico(anio, mes, idsArchivo);
            if (existe == 1) {
                break;
            }
        }
        return existe;
    }


    @Override
    public ResponseMessage updateRR7(int[] idsArchivos, String anioMes, String clvCompania) throws IOException {
        ResponseMessage respuesta = null;

        String[] anioMesArray = anioMes.split("-");
        String anio = anioMesArray[0];
        String mes = anioMesArray[1];
        log.info("Entro a UPDATE RR7 y a validar información.");
        respuesta = validaReporte(Integer.parseInt(anio), mes, anioMes, idsArchivos);

        if (respuesta.getDataExtra().toString().equals("1")) {
            respuesta = calcularReporte(idsArchivos, Integer.parseInt(anio), Integer.parseInt(mes), clvCompania, anioMes);
        } else {
            log.info("Algo salio mal al momento de validar en updateRR7...");
            return respuesta;
        }

        return respuesta;
    }


    private final List<ArchivoRespuesta> listReponse = new ArrayList<>();
    private final List<String> listNoArchivos = new ArrayList<>();

    public ResponseMessage calcularReporte(int[] idsArchivos, int anio, int mes, String clvCompania, String anioMes) throws IOException {
        log.info("Entro a calcular reportes.");
        ResponseMessage respuesta = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar fech = Calendar.getInstance();
        fech.set(Calendar.MONTH, mes - 1);
        fech.set(Calendar.YEAR, anio);
        fech.set(Calendar.DAY_OF_MONTH, 1);
        fech.set(Calendar.DATE, fech.getActualMaximum(Calendar.DATE));

        String nombre = "";
        String claveRR7 = "RR7EFITR";
        String complemento = clvCompania + sdf.format(fech.getTime()) + ".txt";

        for (int idsArchivo : idsArchivos) {
            switch (idsArchivo) {
                case 1:
                    break;
                case 2:
                    nombre = claveRR7 + "CMER" + complemento;
                    respuesta = listas(2, anio, mes, "CMER", nombre, anioMes);
                    break;
                case 11:
                    nombre = claveRR7 + "ACRE" + complemento;
                    respuesta = listas(2, anio, mes, "ACRE", nombre, anioMes);
                    break;
                default:
                    log.info("NO HAY COINCIDENCIAS AL MOMENTO DE CALCULAR REPORTE.");
                    break;
            }
        }

        if (!(listReponse.isEmpty()) || !(listNoArchivos.isEmpty())) {
            Map<String, Object> map = new HashMap<>();
            map.put("Archivos", listReponse);
            map.put("ArchivosNoGen", listNoArchivos);
            respuesta = new ResponseMessage("OK", "1", map);
        } else {
            respuesta = new ResponseMessage("OK", "1", respuesta);
        }

        return respuesta;
    }

    public ResponseMessage listas(int id, int anio, int mes, String archivo, String nombre, String anioMes) throws IOException {
        ResponseMessage respuesta = null;
        String imprimir = "CREANDO --> " + archivo;
        log.info(imprimir);

        ArchivoRespuesta ar;
        Rr7ArchivoHistorico ah;
        int crear = 0;

        crear = updateAndCalculate(id, anio, mes, nombre, anioMes);
        if (crear == 1) {
            ar = new ArchivoRespuesta();
            ah = rr7Historico.findByIdArchivo(id, anio, mes, archivo);
            if (ah != null) {
                ar.setBaseArchivo(ah.getBase64Rr7());
                ar.setNombreArchivo(nombre);
                listReponse.add(ar);
            } else {
                listNoArchivos.add(archivo);
            }

            respuesta = new ResponseMessage("OK", "Bien", 1);
        } else {
            respuesta = new ResponseMessage("Error", "Algo salio mal al momento de crear " + archivo, -1);
        }

        return respuesta;
    }

    public int updateAndCalculate(int idArchivo, int anio, int mes, String nombre, String anioMes) throws IOException {
        int archivoActualizado = 0;
        ResponseMessage respuesta = null;

        ArchivoRespuesta ar;
        Rr7ArchivoHistorico getRr7;
        LocalDate date = LocalDate.now();
        String fecha = "" + date;

        if (idArchivo == 1) {
            log.info("Ejecutando cálculos para el archivo CMBG");
            respuesta = cmbgService.updateCmbg(anio, mes);
            if (respuesta.getMensaje().equalsIgnoreCase("OK")) {
                ar = cmbgService.obtenerCmbg(nombre);
                getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "CMBG");
                accion(ar, getRr7, idArchivo, fecha, anio, mes);
                archivoActualizado = 1;
            }
        } else if (idArchivo == 2) {
            log.info("Ejecutando cálculos para el archivo CMER");
            respuesta = cmerService.updateCmer(anio, mes);
            if (respuesta.getMensaje().equalsIgnoreCase("OK")) {
                ar = cmerService.obtenerCmer(nombre);
                getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "CMER");
                accion(ar, getRr7, idArchivo, fecha, anio, mes);
                archivoActualizado = 1;
            }
        } else if (idArchivo == 3) {
            log.info("Ejecutando cálculos para el archivo INVE");
            ar = inveService.obtenerInve(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "INVE");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 4) {
            log.info("Ejecutando cálculos para el archivo INDE");
            ar = indeService.obtenerInde(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "INDE");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 5) {
            log.info("Ejecutando cálculos para el archivo INMU");
            ar = inmuService.obtenerInmu(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "INMU");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 6) {
            log.info("Ejecutando cálculos para el archivo CRED");
            ar = credService.obtenerCred(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "CRED");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 7) {
            log.info("Ejecutando cálculos para el archivo DEUD");
            ar = deudService.obtenerDeud(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "DEUD");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 8) {
            log.info("Ejecutando cálculos para el archivo OINV");
            ar = oinvService.obtenerOinv(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "OINV");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 9) {
            log.info("Ejecutando cálculos para el archivo INMU");
            ar = irreService.obtenerIrre(nombre, anioMes);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "INMU");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 10) {
            log.info("Ejecutando cálculos para el archivo OACT");
            oactService.updateOact(anio, mes);
            ar = oactService.obtenerOact(nombre);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "OACT");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 11) {
            log.info("Ejecutando cálculos para el archivo OACT");
            acreService.updateAcre(anio, mes);
            ar = acreService.obtenerACRE(nombre);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "OACT");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 12) {
            log.info("Ejecutando cálculos para el archivo OPAS");
            opasService.updateOpas(anio, mes);
            ar = opasService.obtenerOpas(nombre);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "OPAS");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        } else if (idArchivo == 13) {
            log.info("Ejecutando cálculos para el archivo CSOC");
            csocService.updateCsoc(anio, mes);
            ar = csocService.obtenerCsoc(nombre);
            getRr7 = rr7Historico.findByIdArchivo(idArchivo, anio, mes, "CSOC");
            accion(ar, getRr7, idArchivo, fecha, anio, mes);
            archivoActualizado = 1;
        }

        return archivoActualizado;
    }

    public void accion(ArchivoRespuesta ar, Rr7ArchivoHistorico getRr7, int id, String fecha, int anio, int mes) {
        Rr7ArchivoHistorico newRr7 = new Rr7ArchivoHistorico();

        if (!(ar.getBaseArchivo().isEmpty())) {
            if (getRr7 != null) {
                log.info("EL ARCHIVO BASE 64 YA EXISTE EN LA TABLA HISTORICA, POR LO QUE SE VA A ACTUALIZAR.");
                newRr7.setFechaRegistro(fecha);
                newRr7.setBase64Rr7(ar.getBaseArchivo());
                newRr7.setAnio(anio);
                newRr7.setIdArchivo(id);
                newRr7.setMes(mes);
                update(newRr7);
            } else {
                log.info("EL ARCHIVO BASE 64 ES NUEVO, SE PROCEDE A INSERTAR.");
                newRr7.setFechaRegistro(fecha);
                newRr7.setBase64Rr7(ar.getBaseArchivo());
                newRr7.setAnio(anio);
                newRr7.setIdArchivo(id);
                newRr7.setMes(mes);
                insert(newRr7);
            }
        }

    }

    public void insert(Rr7ArchivoHistorico ah) {
        jdbcTemplate.update("INSERT INTO RR7ARCHIVOHISTORICO (FECHAREGISTRO, BASE64RR7,ANIO, MES, " + "IDARCHIVO) VALUES (?, ?, ?, ?, ?)", ah.getFechaRegistro(), ah.getBase64Rr7(), ah.getAnio(), ah.getMes(), ah.getIdArchivo());
        log.info("REGISTRO DEL ARCHIVO BASE 64 ACTUALIZADO CORRECTAMENTE.");
    }

    public void update(Rr7ArchivoHistorico ah) {
        String sql = "Update RR7ARCHIVOHISTORICO Set FECHAREGISTRO = ? ,BASE64RR7 = ? ,ANIO = ? ,MES = ? " + "WHERE IDARCHIVO = ?";
        jdbcTemplate.update(sql, ah.getFechaRegistro(), ah.getBase64Rr7(), ah.getAnio(), ah.getMes(), ah.getIdArchivo());
    }

}
