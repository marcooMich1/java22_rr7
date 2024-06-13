package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CmerService {

    ArchivoRespuesta obtenerCmer(String nombre) throws IOException;

    ResponseMessage updateCmer(int anio, int mes);

}
