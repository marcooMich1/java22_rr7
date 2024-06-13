package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface OpacService {

    ArchivoRespuesta obtenerOpac(String nombre) throws IOException;

    ResponseMessage updateOpac(int anio, int mes);

}
