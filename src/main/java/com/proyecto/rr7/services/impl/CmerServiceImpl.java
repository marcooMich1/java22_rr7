package com.proyecto.rr7.services.impl;

import com.proyecto.rr7.dao.CmerDao;
import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.services.CmerService;
import com.proyecto.rr7.util.ResponseMessage;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Transactional
@Service
public class CmerServiceImpl implements CmerService {

    private static final Logger log = LogManager.getLogger(CmerServiceImpl.class);

    CmerDao cmerDao;

    @Autowired
    public CmerServiceImpl(CmerDao cmerDao) {
        this.cmerDao = cmerDao;
    }

    @Override
    public ArchivoRespuesta obtenerCmer(String nombre) throws IOException {
        log.info("Entrando al metodo de obtenerCmer");
        return null;
    }

    @Override
    public ResponseMessage updateCmer(int anio, int mes) {
        log.info("Entrando al metodo de updateCmer");
        return null;
    }

}
