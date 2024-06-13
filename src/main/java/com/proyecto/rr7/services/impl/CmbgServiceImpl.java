package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CmbgDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.services.CmbgService;
import com.proyecto.rr7.util.ResponseMessage;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Transactional
@Service
public class CmbgServiceImpl implements CmbgService {

    private static final Logger log = LogManager.getLogger(CmbgServiceImpl.class);

    CmbgDao cmbgDao;

    @Autowired
    public CmbgServiceImpl(CmbgDao cmbgDao) {
        this.cmbgDao = cmbgDao;
    }

    @Override
    public ArchivoRespuesta obtenerCmbg(String nombre) throws IOException {
        log.info("Entrando al metodo de obtenerCmbg");
        return null;
    }

    @Override
    public ResponseMessage updateCmbg(int anio, int mes) {
        log.info("Entrando al metodo de updateCmbg");
        return null;
    }

}
