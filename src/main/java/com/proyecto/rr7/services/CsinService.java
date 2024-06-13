package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CsinService {

    ArchivoRespuesta obtenerCsin(String nombre) throws IOException;

    ResponseMessage updateCsin(int anio, int mes);

}
