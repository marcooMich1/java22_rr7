package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;
import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface RifiService {

    ArchivoRespuesta obtenerRifi(String nombre) throws IOException;

    ResponseMessage updateRifi(int anio, int mes);

}
