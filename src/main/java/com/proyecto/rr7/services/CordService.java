package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CordService {

    ArchivoRespuesta obtenerCord(String nombre) throws IOException;

    ResponseMessage updateCord(int anio, int mes);

}
