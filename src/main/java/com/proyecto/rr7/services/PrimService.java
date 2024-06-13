package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface PrimService {

    ArchivoRespuesta obtenerPrim(String nombre) throws IOException;

    ResponseMessage updatePrim(int anio, int mes);

}
