package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CmbgService {

    ArchivoRespuesta obtenerCmbg(String nombre) throws IOException;

    ResponseMessage updateCmbg(int anio, int mes);

}
