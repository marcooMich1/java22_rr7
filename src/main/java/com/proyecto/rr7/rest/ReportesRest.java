package com.proyecto.rr7.rest;

import com.proyecto.rr7.entities.CCArchivosRR7;
import com.proyecto.rr7.services.CalcularReporteService;
import com.proyecto.rr7.services.RR7ArchivoHistoricoService;
import com.proyecto.rr7.util.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("reportes/")
public class ReportesRest {

    public static final Logger logger = LoggerFactory.getLogger(ReportesRest.class);

    RR7ArchivoHistoricoService historico;
    CalcularReporteService calcularReporteService;

    @Autowired
    public ReportesRest(RR7ArchivoHistoricoService historico, CalcularReporteService calcularReporteService) {
        this.historico = historico;
        this.calcularReporteService = calcularReporteService;
    }

    @PostMapping("consultarRR7/{anioMes}")
    public ResponseMessage consultarRR7(@PathVariable("anioMes") String anioMes) {
        ResponseMessage respuesta = null;
        List<CCArchivosRR7> lista = null;

        try {
            lista = historico.consultarRR7(anioMes);
            if (!(lista.isEmpty())) {
                respuesta = new ResponseMessage("0", "OK", lista);
            } else {
                respuesta = new ResponseMessage("1", "ERROR", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            respuesta = new ResponseMessage("-1", "ERROR", null);
        }
        return respuesta;
    }

    @PostMapping("procesarRR7/{idsArchivos}/{anioMes}/{clvCompania}")
    public ResponseMessage processarRR7(@PathVariable("idsArchivos") int[] idsArchivos,
                                        @PathVariable("anioMes") String anioMes,
                                        @PathVariable("clvCompania") String clvCompania) throws IOException {
        ResponseMessage respuesta = null;
        if (idsArchivos.length <= 0 || idsArchivos.length > 29) {
            respuesta = new ResponseMessage("-1", "Cantidad de archivos no valido", null);
        } else {
            logger.info("Va a ir a procesar....");
            respuesta = calcularReporteService.procesarRr7(idsArchivos, anioMes, clvCompania);
        }

        return respuesta;
    }

    @PostMapping("updateRR7/{idsArchivos}/{anioMes}/{clvCompania}")
    public ResponseMessage updateRR7(@PathVariable("idsArchivos") int[] idsArchivos,
                                     @PathVariable("anioMes") String anioMes,
                                     @PathVariable("clvCompania") String clvCompania) throws IOException {
        ResponseMessage respuesta = null;
        if (idsArchivos.length <= 0 || idsArchivos.length > 29) {
            respuesta = new ResponseMessage("-1", "Cantidad de archivos no valido", null);
        } else {
            logger.info("Va a ir a actualizar....");
            respuesta = calcularReporteService.updateRR7(idsArchivos, anioMes, clvCompania);
        }

        return respuesta;
    }

    @PostMapping("procesarValidaciones/{anioMes}")
    public ResponseMessage procesarValidaciones(@PathVariable("anioMes") String anioMes) {
        return null;
    }

}
