package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface BaseService {

    ArchivoRespuesta obtenerBase(String nombre, String anioMes) throws IOException;

    int getExistInfoBase(String anioMes);

}
