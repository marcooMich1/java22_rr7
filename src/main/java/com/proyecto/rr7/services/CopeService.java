package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CopeService {

    ArchivoRespuesta obtenerCope(String nombre) throws IOException;

    ResponseMessage updateCope(int anio, int mes);

}
